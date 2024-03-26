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
	private static final int A = 'A';
	private static final int WALL = 0;
	private static final int DEST = '*' - 'A' + 1;
	private static final int NUM = 26;
	private static final int CNT = 10000000;
	private static final char WALL_CHAR = '-';
	
	private static final class Edge {
		int to, capacity, flow;
		Edge reverse;
		
		Edge(int to, int capacity) {
			this.to = to;
			this.capacity = capacity;
		}
	}
	
	private static int[] level, work, diff;
	private static Queue<Integer> q;
	private static ArrayList<ArrayList<Edge>> adj;
	
	private static void addAdj(int from, int to, int capacity) {
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
			} while (flow != 0);
		}
		return total;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m, size, max, source, sink, pos, next, i, j, k;
		int[] map, cost;
		char[] str;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		size = m + 2;
		max = (n + 2) * (m + 2);
		source = 2 * max;
		sink = source + 1;
		map = new int[max];
		for (i = 1; i <= n; i++) {
			str = br.readLine().toCharArray();
			for (j = 1; j <= m; j++) {
				if (str[j - 1] != WALL_CHAR) {
					map[i * size + j] = str[j - 1] - A + 1;
				}
			}
		}
		cost = new int[NUM + 1];
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= NUM; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}
		adj = new ArrayList<>(sink + 1);
		for (i = 0; i <= sink; i++) {
			adj.add(new ArrayList<>());
		}
		diff = new int[] {-size, 1, size, -1};
		for (i = 1; i <= n; i++) {
			for (j = 1; j <= m; j++) {
				pos = i * size + j;
				if (map[pos] == WALL) {
					continue;
				}
				if (map[pos] == DEST) {
					addAdj(pos, sink, INF);
					continue;
				}
				addAdj(pos, pos + max, cost[map[pos]] + CNT);
				for (k = 0; k < 4; k++) {
					next = pos + diff[k];
					if (map[next] != WALL) {
						addAdj(pos + max, next, INF);
					}
				}
				if (i == 1 || i == n || j == 1 || j == m) {
					addAdj(source, pos, INF);
				}
			}
		}
		System.out.print(dinic(source, sink) % CNT);
	}
}
