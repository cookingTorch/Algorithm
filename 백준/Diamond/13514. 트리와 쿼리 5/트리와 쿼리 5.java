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
	private static int[][] dp;
	private static boolean[] white;
	private static boolean[] visited;
	private static Node[] adj;
	private static ArrayList<TreeMap<Integer, Integer>> multiset;
	
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
		multiset.add(new TreeMap<>());
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
		
		white[v] ^= true;
		for (i = v; i != 0; i = ct[i]) {
			dist = getDist(i, v);
			if (white[v]) {
			    multiset.get(i).put(dist, multiset.get(i).getOrDefault(dist, 0) + 1);
			} else {
				distCnt = multiset.get(i).getOrDefault(dist, 0);
			    if (distCnt == 1) {
			        multiset.get(i).remove(dist);
			    } else {
			        multiset.get(i).put(dist, distCnt - 1);
			    }
			}
		}
	}
	
	private static final int query(int v) {
		int i;
		int ret;
		
		ret = INF;
		for (i = v; i != 0; i = ct[i]) {
			if (!multiset.get(i).isEmpty()) {
				ret = Math.min(ret, getDist(i, v) + multiset.get(i).firstKey());
			}
		}
		return ret == INF ? -1 : ret;
	}
	
	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int u;
		int v;
		int i;
		int j;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		adj = new Node[n + 1];
		for (i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			adj[u] = new Node(v, adj[u]);
			adj[v] = new Node(u, adj[v]);
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
		multiset = new ArrayList<>(n + 1);
		multiset.add(null);
		buildCentroidTree(1, 0);
		white = new boolean[n + 1];
		sb = new StringBuilder();
		m = Integer.parseInt(br.readLine());
		for (i = 0; i < m; i++) {
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
