import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	private static final char FIRST = '1';
	
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
	
	private static final class Result {
		int cnt;
		long sum;
		HashMap<Integer, Integer> cnts;
		HashMap<Integer, Long> sums;
		
		Result() {
			cnts = new HashMap<>();
			sums = new HashMap<>();
		}
		
		void add(int idx, long dist) {
			cnt++;
			sum += dist;
			cnts.put(idx, cnts.getOrDefault(idx, 0) + 1);
			sums.put(idx, sums.getOrDefault(idx, 0L) + dist);
		}
		
		long calc(int idx, long dist) {
			return (cnt - cnts.getOrDefault(idx, 0)) * dist + sum - sums.getOrDefault(idx, 0L);
		}
	}
	
	private static int n;
	private static int logN;
	private static int[] ct;
	private static int[] depth;
	private static int[] subSize;
	private static int[][] dp;
	private static long[] distance;
	private static boolean[] blue;
	private static boolean[] visited;
	private static Node[] adj;
	private static Result[] results;
	
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
		int prev;
		
		if (blue[v]) {
			return;
		}
		blue[v] = true;
		results[v].add(v, 0L);
		for (prev = v, i = ct[v]; i != n; prev = i, i = ct[i]) {
			results[i].add(prev, getDist(i, v));
		}
	}
	
	private static final long query(int v) {
		int i;
		int prev;
		long ret;
		long dist;
		
		ret = 0L;
		for (prev = n, i = v; i != n; prev = i, i = ct[i]) {
			dist = getDist(i, v);
			ret += results[i].calc(prev, dist);
		}
		return ret;
	}
	
	public static void main(String[] args) throws IOException {
		int q;
		int a;
		int i;
		int j;
		long b;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		adj = new Node[n + 1];
		for (i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Long.parseLong(st.nextToken());
			adj[i] = new Node(a, b, adj[i]);
			adj[a] = new Node(i, b, adj[a]);
		}
		logN = (int) Math.ceil(Math.log(n) / Math.log(2.0));
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
		buildCentroidTree(0, n);
		blue = new boolean[n];
		results = new Result[n];
		for (i = 0; i < n; i++) {
			results[i] = new Result();
		}
		sb = new StringBuilder();
		q = Integer.parseInt(br.readLine());
		for (i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			if (st.nextToken().charAt(0) == FIRST) {
				update(Integer.parseInt(st.nextToken()));
			} else {
				sb.append(query(Integer.parseInt(st.nextToken()))).append('\n');
			}
		}
		System.out.print(sb);
	}
}
