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
	
	private static void addAdj(int from, int to, int capacity, boolean twoWay) {
		Edge forward, backward;
		
		forward = new Edge(to, capacity);
		if (twoWay) {
			backward = new Edge(from, capacity);
		} else {
			backward = new Edge(from, 0);
		}
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
	
	private static int solution(BufferedReader br, StringTokenizer st, int n, int m) throws IOException {
		int source, sink, i;
		
		source = 0;
		sink = n + 1;
		adj = new ArrayList<>(sink + 1);
		for (i = 0; i <= sink; i++) {
			adj.add(new ArrayList<>());
		}
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			if (Integer.parseInt(st.nextToken()) == 0) {
				addAdj(source, i, 1, false);
			} else {
				addAdj(i, sink, 1, false);
			}
		}
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			addAdj(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 1, true);
		}
		return dinic(source, sink);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		while (n != 0 || m != 0) {
			sb.append(solution(br, st, n, m)).append('\n');
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
		}
		System.out.print(sb);
	}
}
