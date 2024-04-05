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
	private static final int CHAR = 'a';
	private static final int[] dx = {-1, 0, 1, 0};
	private static final int[] dy = {0, 1, 0, -1};
	private static final char BANK = 'B';
	private static final char DOT = '.';
	
	private static final class Edge {
		int to, capacity, flow;
		Edge reverse;
		
		Edge(int to, int capacity) {
			this.to = to;
			this.capacity = capacity;
		}
	}
	
	private static int[] level, work;
	private static Queue<Integer> q;
	private static ArrayList<ArrayList<Edge>> adj;
	
	private static void addEdge(int from, int to, int capacity) {
		Edge forward, backward;
		
		forward = new Edge(to, capacity);
		backward = new Edge(from, 0);
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
			} while (flow > 0);
		}
		return total;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m, c, pos, next, max, size, source, sink, ans, x, y, i, j, k;
		int[] costs;
		String[] ins;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		size = n;
		max = m * size;
		source = 2 * max;
		sink = source + 1;
		ins = new String[m];
		for (i = 0; i < m; i++) {
			ins[i] = br.readLine();
		}
		costs = new int[c];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < c; i++) {
			costs[i] = Integer.parseInt(st.nextToken());
		}
		adj = new ArrayList<>(sink + 1);
		for (i = 0; i <= sink; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 0; i < m; i++) {
			for (j = 0; j < n; j++) {
				pos = i * size + j;
				switch (ins[i].charAt(j)) {
				default:
					addEdge(pos, pos + max, costs[ins[i].charAt(j) - CHAR]);
					break;
				case BANK:
					addEdge(source, pos, INF);
				case DOT:
					addEdge(pos, pos + max, INF);
				}
				if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
					addEdge(pos + max, sink, INF);
				}
				for (k = 0; k < 4; k++) {
					x = i + dx[k];
					y = j + dy[k];
					if (x < 0 || x >= m || y < 0 || y >= n) {
						continue;
					}
					next = x * size + y;
					addEdge(pos + max, next, INF);
				}
			}
		}
		ans = dinic(source, sink);
		System.out.print(ans == INF ? "-1" : ans);
	}
}
