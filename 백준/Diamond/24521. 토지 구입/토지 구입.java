import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	private static final int[] dx = {-1, 0};
	private static final int[] dy = {0, -1};
	private static final char L = 'L';
	private static final char R = 'R';
	
	private static final class Edge {
		int to, capacity, flow;
		Edge reverse;
		
		Edge(int to, int capacity) {
			this.to = to;
			this.capacity = capacity;
		}
	}
	
	private static int size;
	private static int[] level, work;
	private static char[] ans;
	private static Queue<Integer> q;
	private static ArrayList<ArrayList<Edge>> adj;
	
	private static void addEdge(int from, int to, int capacity, boolean twoWay) {
		Edge forward, backward;
		
		forward = new Edge(to, capacity);
		backward = new Edge(from, twoWay ? capacity : 0);
		forward.reverse = backward;
		backward.reverse = forward;
		adj.get(from).add(forward);
		adj.get(to).add(backward);
	}
	
	private static boolean bfs(int source, int sink) {
		int curr;
		
		Arrays.fill(level, INF);
		level[source] = 0;
		q.add(source);
		while (!q.isEmpty()) {
			curr = q.poll();
			for (Edge edge : adj.get(curr)) {
				if (level[edge.to] == INF && edge.capacity > edge.flow) {
					level[edge.to] = level[curr] + 1;
					q.add(edge.to);
				}
			}
		}
		return level[sink] != INF;
	}
	
	private static int dfs(int curr, int sink, int max) {
		int flow;
		Edge edge;
		ArrayList<Edge> edges;
		
		if (curr == sink) {
			return max;
		}
		edges = adj.get(curr);
		for (; work[curr] < edges.size(); work[curr]++) {
			edge = edges.get(work[curr]);
			if (level[edge.to] == level[curr] + 1 && edge.capacity > edge.flow) {
				flow = dfs(edge.to, sink, Math.min(max, edge.capacity - edge.flow));
				if (flow > 0) {
					edge.flow += flow;
					edge.reverse.flow -= flow;
					return flow;
				}
			}
		}
		return 0;
	}
	
	private static int dinic(int source, int sink) {
		int total, flow;
		
		level = new int[sink + 1];
		q = new ArrayDeque<>();
		total = 0;
		while (bfs(source, sink)) {
			work = new int[sink + 1];
			do {
				flow = dfs(source, sink, INF);
				total += flow;
			} while (flow != 0);
		}
		return total;
	}
	
	private static void visit(int node, int source, int sink) {
		if (ans[node] == L) {
			return;
		}
		ans[node] = L;
		for (Edge edge : adj.get(node)) {
			if (edge.capacity > edge.flow) {
				visit(edge.to, source, sink);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, k, max, source, sink, pos, next, sum, x, y, i, j, l;
		int[][] cell, bonus, a, b;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		size = m;
		max = n * size;
		source = max;
		sink = source + 1;
		cell = new int[n][m];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < m; j++) {
				cell[i][j] = Integer.parseInt(st.nextToken()) - 1;
			}
		}
		bonus = new int[k][k];
		for (i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < k; j++) {
				bonus[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		a = new int[n][m];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < m; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		b = new int[n][m];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < m; j++) {
				b[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		adj = new ArrayList<>(sink + 1);
		for (i = 0; i <= sink; i++) {
			adj.add(new ArrayList<>());
		}
		sum = 0;
		for (i = 0; i < n; i++) {
			for (j = 0; j < m; j++) {
				pos = i * size + j;
				if (a[i][j] > b[i][j]) {
					addEdge(source, pos, a[i][j] - b[i][j], false);
					sum += a[i][j];
				} else if (b[i][j] > a[i][j]) {
					sum += b[i][j];
					addEdge(pos, sink, b[i][j] - a[i][j], false);
				} else {
					sum += a[i][j];
				}
				for (l = 0; l < 2; l++) {
					x = i + dx[l];
					y = j + dy[l];
					if (x < 0 || y < 0) {
						continue;
					}
					if (bonus[cell[i][j]][cell[x][y]] > 0) {
						sum += bonus[cell[i][j]][cell[x][y]];
						next = x * size + y;
						addEdge(pos, next, bonus[cell[i][j]][cell[x][y]], true);
					}
				}
			}
		}
		sb.append(sum - dinic(source, sink)).append('\n');
		ans = new char[sink + 1];
		Arrays.fill(ans, R);
		visit(source, source, sink);
		for (i = 0; i < n; i++) {
			for (j = 0; j < m; j++) {
				sb.append(ans[i * size + j]);
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
}
