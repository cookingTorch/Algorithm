import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static final int BUILD = 'B';
	
	private static final class Edge {
		int to;
		Edge next;
		
		Edge(int to, Edge next) {
			this.to = to;
			this.next = next;
		}
	}
	
	private static int logN;
	private static int[] ct;
	private static int[] max1;
	private static int[] max2;
	private static int[] maxFrom;
	private static int[] subSize;
	private static int[] distance;
	private static int[][] dp;
	private static boolean[] visited;
	private static Edge[] adj;
	
	private static final void dfs(int curr, int parent) {
		Edge edge;
		
		dp[0][curr] = parent;
		distance[curr] = distance[parent] + 1;
		for (edge = adj[curr]; edge != null; edge = edge.next) {
			if (edge.to == parent) {
				continue;
			}
			dfs(edge.to, curr);
		}
	}
	
	private static final int getSize(int curr, int parent) {
		Edge edge;
		
		subSize[curr] = 1;
		for (edge = adj[curr]; edge != null; edge = edge.next) {
			if (visited[edge.to] || edge.to == parent) {
				continue;
			}
			subSize[curr] += getSize(edge.to, curr);
		}
		return subSize[curr];
	}
	
	private static final int getCentroid(int curr, int parent, int thr) {
		Edge edge;
		
		for (edge = adj[curr]; edge != null; edge = edge.next) {
			if (visited[edge.to] || edge.to == parent) {
				continue;
			}
			if (subSize[edge.to] > thr) {
				return getCentroid(edge.to, curr, thr);
			}
		}
		return curr;
	}
	
	private static final void buildCentroidTree(int curr, int parent) {
		int centroid;
		Edge edge;
		
		centroid = getCentroid(curr, -1, getSize(curr, -1) >> 1);
		ct[centroid] = parent;
		visited[centroid] = true;
		for (edge = adj[centroid]; edge != null; edge = edge.next) {
			if (visited[edge.to]) {
				continue;
			}
			buildCentroidTree(edge.to, centroid);
		}
	}
	
	private static final int lca(int u, int v) {
		int i;
		int temp;
		int diff;
		
		if (distance[u] < distance[v]) {
			temp = u;
			u = v;
			v = temp;
		}
		for (diff = distance[u] - distance[v], i = 0; diff != 0; diff >>= 1, i++) {
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
		return distance[u] + distance[v] - (distance[lca(u, v)] << 1);
	}
	
	private static final void update(int v) {
		int i;
		int dist;
		int prev;
		
		for (prev = v, i = v; i != -1; prev = i, i = ct[i]) {
			dist = getDist(i, v);
			if (prev == maxFrom[i]) {
				if (dist > max1[i]) {
					max1[i] = dist;
				}
			} else if (dist > max1[i]) {
				max2[i] = max1[i];
				max1[i] = dist;
				maxFrom[i] = prev;
			} else if (dist > max2[i]) {
				max2[i] = dist;
			}
		}
	}
	
	private static final int query(int v) {
		int i;
		int ret;
		int dist;
		int prev;
		
		ret = 0;
		for (prev = v, i = v; i != -1; prev = i, i = ct[i]) {
			dist = getDist(i, v);
			if (prev == maxFrom[i]) {
				if (max2[i] != -1) {
					ret = Math.max(ret, dist + max2[i]);
				}
			} else {
				if (max1[i] != -1) {
					ret = Math.max(ret, dist + max1[i]);
				}
			}
		}
		return ret;
	}
	
	public static void main(String[] args) throws IOException {
		int q;
		int i;
		int j;
		int idx;
		int size;
		int[][] lines;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;
		ArrayList<Integer> roots;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		q = Integer.parseInt(br.readLine());
		lines = new int[q][2];
		adj = new Edge[100_001];
		size = 0;
		roots = new ArrayList<>();
		for (i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			lines[i][0] = st.nextToken().charAt(0);
			if (lines[i][0] == BUILD) {
				idx = Integer.parseInt(st.nextToken());
				lines[i][1] = ++size;
				if (idx == -1) {
					roots.add(size);
				} else {
					adj[idx] = new Edge(size, adj[idx]);
					adj[size] = new Edge(idx, adj[size]);
				}
			} else {
				lines[i][1] = Integer.parseInt(st.nextToken());
			}
		}
		logN = (int) Math.ceil(Math.log(size) / Math.log(2));
		dp = new int[logN + 1][size + 1];
		distance = new int[size + 1];
		for (int root : roots) {
			dfs(root, 0);
		}
		for (i = 1; i <= logN; i++) {
			for (j = 1; j <= size; j++) {
				dp[i][j] = dp[i - 1][dp[i - 1][j]];
			}
		}
		ct = new int[size + 1];
		subSize = new int[size + 1];
		visited = new boolean[size + 1];
		for (int root : roots) {
			buildCentroidTree(root, -1);
		}
		max1 = new int[size + 1];
		for (i = 0; i <= size; i++) {
			max1[i] = -1;
		}
		max2 = new int[size + 1];
		for (i = 0; i <= size; i++) {
			max2[i] = -1;
		}
		maxFrom = new int[size + 1];
		sb = new StringBuilder();
		for (int[] line : lines) {
			if (line[0] == BUILD) {
				update(line[1]);
			} else {
				sb.append(query(line[1])).append('\n');
			}
		}
		System.out.print(sb);
	}
}
