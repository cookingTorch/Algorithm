import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	private static final long INF = Long.MAX_VALUE;
	
	private static final class Node {
		int idx;
		long weight;
		Node next;
		
		Node(int idx, long weight, Node next) {
			this.idx = idx;
			this.weight = weight;
			this.next = next;
		}
	}
	
	private static int n;
	private static int logN;
	private static int[] ct;
	private static int[] depth;
	private static int[] subSize;
	private static int[][] dp;
	private static long[] minDist;
	private static long[] distance;
	private static boolean[] visited;
	private static Node[] adj;
	private static ArrayDeque<Integer> updated;
	
	private static final void dfs(int curr, int parent, long weight) {
		Node child;
		
		dp[0][curr] = parent;
		depth[curr] = depth[parent] + 1;
		distance[curr] = distance[parent] + weight;
		for (child = adj[curr]; child != null; child = child.next) {
			if (child.idx == parent) {
				continue;
			}
			dfs(child.idx, curr, child.weight);
		}
	}
	
	private static int getSize(int curr, int parent) {
		Node child;
		
		subSize[curr] = 1;
		for (child = adj[curr]; child != null; child = child.next) {
			if (visited[child.idx] || child.idx == parent) {
				continue;
			}
			subSize[curr] += getSize(child.idx, curr);
		}
		return subSize[curr];
	}
	
	private static final int getCentroid(int curr, int parent, int threshold) {
		Node child;
		
		for (child = adj[curr]; child != null; child = child.next) {
			if (visited[child.idx] || child.idx == parent) {
				continue;
			}
			if (subSize[child.idx] > threshold) {
				return getCentroid(child.idx, curr, threshold);
			}
		}
		return curr;
	}
	
	private static final void buildCentroidTree(int curr, int parent) {
		int centroid;
		Node child;
		
		centroid = getCentroid(curr, -1, getSize(curr, -1) >> 1);
		ct[centroid] = parent;
		visited[centroid] = true;
		minDist[centroid] = INF;
		for (child = adj[centroid]; child != null; child = child.next) {
			if (visited[child.idx]) {
				continue;
			}
			buildCentroidTree(child.idx, centroid);
		}
	}
	
	private static final int lca(int u, int v) {
		int i;
		int temp;
		int diff;
		
		if (depth[u] < depth[v]) {
			temp = u;
			u = v;
			v = temp;
		}
		for (diff = depth[u] - depth[v], i = 0; diff != 0; diff >>= 1, i++) {
			if ((diff & 1) == 1) {
				u = dp[i][u];
			}
		}
		if (u == v) {
			return u;
		}
		for (i = logN; i >= 0; i--) {
			if (dp[i][u] != dp[i][v]) {
				u = dp[i][u];
				v = dp[i][v];
			}
		}
		return dp[0][u];
	}
	
	private static final long getDist(int u, int v) {
		return distance[u] + distance[v] - (distance[lca(u, v)] << 1);
	}
	
	private static final void update(int v) {
		int i;
		long dist;
		
		for (i = v; i != n; i = ct[i]) {
			dist = getDist(i, v);
			if (dist < minDist[i]) {
				minDist[i] = dist;
				updated.addFirst(i);
			}
		}
	}
	
	private static final long query(int v) {
		int i;
		long ret;
		
		ret = INF;
		for (i = v; i != n; i = ct[i]) {
			if (minDist[i] != INF) {
				ret = Math.min(ret, getDist(i, v) + minDist[i]);
			}
		}
		return ret == INF ? -1L : ret;
	}
	
	public static void main(String[] args) throws IOException {
		int q;
		int a;
		int b;
		int d;
		int s;
		int t;
		int i;
		int j;
		long min;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
		adj = new Node[n];
		for (i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			adj[a] = new Node(b, d, adj[a]);
			adj[b] = new Node(a, d, adj[b]);
		}
		logN = (int) Math.ceil(Math.log(n) / Math.log(2));
		dp = new int[logN + 1][n + 1];
		depth = new int[n + 1];
		distance = new long[n + 1];
		dfs(0, n, 0L);
		for (i = 1; i <= logN; i++) {
			for (j = 0; j < n; j++) {
				dp[i][j] = dp[i - 1][dp[i - 1][j]];
			}
		}
		ct = new int[n];
		subSize = new int[n];
		visited = new boolean[n];
		minDist = new long[n];
		buildCentroidTree(0, n);
		updated = new ArrayDeque<>(n * logN);
		sb = new StringBuilder();
		for (i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			while (!updated.isEmpty()) {
				minDist[updated.pollFirst()] = INF;
			}
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < s; j++) {
				update(Integer.parseInt(st.nextToken()));
			}
			min = INF;
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < t; j++) {
				min = Math.min(min, query(Integer.parseInt(st.nextToken())));
			}
			sb.append(min).append('\n');
		}
		System.out.print(sb);
	}
}
