import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	private static final HashMap<Long, Integer> NIL = new HashMap<>();
	
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
		HashMap<Long, Integer> xor;
		HashMap<Integer, HashMap<Long, Integer>> xors;
		
		Result() {
			xor = new HashMap<>();
			xors = new HashMap<>();
		}
		
		final void add(int idx, int child, long dist) {
			xor.put(dist, xor.getOrDefault(dist, 0) ^ idx);
			if (xors.get(child) == null) {
				xors.put(child, new HashMap<>());
			}
			xors.get(child).put(dist, xors.get(child).getOrDefault(dist, 0) ^ idx);
		}
		
		final long calc(int child, long dist) {
			return xor.getOrDefault(dist, 0) ^ xors.getOrDefault(child, NIL).getOrDefault(dist, 0);
		}
	}
	
	private static int logN;
	private static int[] ct;
	private static int[] depth;
	private static int[] subSize;
	private static int[][] dp;
	private static long[] distance;
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
		
		results[v].add(v, v, 0L);
		for (prev = v, i = ct[v]; i != 0; prev = i, i = ct[i]) {
			results[i].add(v, prev, getDist(i, v));
		}
	}
	
	private static final int query(int v, long d) {
		int i;
		int ret;
		int prev;
		long dist;
		
		ret = 0;
		for (prev = 0, i = v; i != 0; prev = i, i = ct[i]) {
			dist = getDist(i, v);
			ret ^= results[i].calc(prev, d - dist);
		}
		return ret;
	}
	
	public static void main(String[] args) throws IOException {
		int n;
		int q;
		int u;
		int v;
		int i;
		int j;
		long w;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		adj = new Node[n + 1];
		for (i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Long.parseLong(st.nextToken());
			adj[u] = new Node(v, w, adj[u]);
			adj[v] = new Node(u, w, adj[v]);
		}
		logN = (int) Math.ceil(Math.log(n) / Math.log(2.0));
		dp = new int[logN + 1][n + 1];
		depth = new int[n + 1];
		distance = new long[n + 1];
		dfs(1, 0, 0L);
		for (i = 1; i <= logN; i++) {
			for (j = 1; j <= n; j++) {
				dp[i][j] = dp[i - 1][dp[i - 1][j]];
			}
		}
		ct = new int[n + 1];
		subSize = new int[n + 1];
		visited = new boolean[n + 1];
		buildCentroidTree(1, 0);
		results = new Result[n + 1];
		results[0] = null;
		for (i = 1; i <= n; i++) {
			results[i] = new Result();
		}
		for (i = 1; i <= n; i++) {
			update(i);
		}
		sb = new StringBuilder();
		for (i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			sb.append(query(Integer.parseInt(st.nextToken()), Long.parseLong(st.nextToken()))).append('\n');
		}
		System.out.print(sb);
	}
}
