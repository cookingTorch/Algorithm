import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	private static final char FIRST = '1';
	
	private static final class Node {
		int idx;
		Node next;
		
		Node(int idx, Node next) {
			this.idx = idx;
			this.next = next;
		}
	}
	
	private static int logN;
	private static int[] ct;
	private static int[] depth;
	private static int[] subSize;
	private static int[] minDist;
	private static int[][] dp;
	private static boolean[] visited;
	private static Node[] adj;
	
	private static final void dfs(int curr, int parent) {
		Node child;
		
		dp[0][curr] = parent;
		depth[curr] = depth[parent] + 1;
		for (child = adj[curr]; child != null; child = child.next) {
			if (child.idx == parent) {
				continue;
			}
			dfs(child.idx, curr);
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
	
	private static final int getDist(int u, int v) {
		return depth[u] + depth[v] - (depth[lca(u, v)] << 1);
	}
	
	private static final void update(int v) {
		int i;
		int dist;
		int distCnt;
		
		for (i = v; i != 0; i = ct[i]) {
			minDist[i] = Math.min(minDist[i], getDist(i, v));
		}
	}
	
	private static final int query(int v) {
		int i;
		int ret;
		
		ret = INF;
		for (i = v; i != 0; i = ct[i]) {
			if (minDist[i] != INF) {
				ret = Math.min(ret, getDist(i, v) + minDist[i]);
			}
		}
		return ret == INF ? -1 : ret;
	}
	
	public static void main(String[] args) throws IOException {
		int n;
		int q;
		int a;
		int i;
		int j;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
		adj = new Node[n + 1];
        st = new StringTokenizer(br.readLine());
		for (i = 2; i <= n; i++) {
			a = Integer.parseInt(st.nextToken());
			adj[a] = new Node(i, adj[a]);
			adj[i] = new Node(a, adj[i]);
		}
		logN = (int) Math.ceil(Math.log(n) / Math.log(2));
		dp = new int[logN + 1][n + 1];
		depth = new int[n + 1];
		dfs(1, 0);
		for (i = 1; i <= logN; i++) {
			for (j = 1; j <= n; j++) {
				dp[i][j] = dp[i - 1][dp[i - 1][j]];
			}
		}
		ct = new int[n + 1];
		subSize = new int[n + 1];
		visited = new boolean[n + 1];
		minDist = new int[n + 1];
		buildCentroidTree(1, 0);
		sb = new StringBuilder();
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
