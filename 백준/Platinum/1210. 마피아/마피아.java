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
	
	private static int[] prev;
	private static boolean[] visited;
	private static Edge[] path;
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
		Queue<Integer> q;
		
		Arrays.fill(prev, -1);
		q = new ArrayDeque<>();
		q.add(source);
		while (!q.isEmpty()) {
			curr = q.poll();
			for (Edge edge : adj.get(curr)) {
				if (prev[edge.to] == -1 && edge.capacity > edge.flow) {
					prev[edge.to] = curr;
					path[edge.to] = edge;
					if (edge.to == sink) {
						return true;
					}
					q.add(edge.to);
				}
			}
		}
		return false;
	}
	
	private static int maxFlow(int source, int sink) {
		int flow, total, i;
		
		prev = new int[sink + 1];
		path = new Edge[sink + 1];
		total = 0;
		while (bfs(source, sink)) {
			flow = INF;
			for (i = sink; i != source; i = prev[i]) {
				flow = Math.min(flow, path[i].capacity - path[i].flow);
			}
			for (i = sink; i != source; i = prev[i]) {
				path[i].flow += flow;
				path[i].reverse.flow -= flow;
			}
			total += flow;
		}
		return total;
	}
	
	private static void dfs(int node) {
		if (visited[node]) {
			return;
		}
		visited[node] = true;
		for (Edge edge : adj.get(node)) {
			if (edge.capacity > edge.flow) {
				dfs(edge.to);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, cost, source, sink, toll1, toll2, i;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		source = 0;
		sink = 2 * n + 1;
		adj = new ArrayList<>(sink + 1);
		for (i = 0; i <= sink; i++) {
			adj.add(new ArrayList<>());
		}
		st = new StringTokenizer(br.readLine());
		addAdj(source, Integer.parseInt(st.nextToken()), INF);
		addAdj(n + Integer.parseInt(st.nextToken()), sink, INF);
		for (i = 1; i <= n; i++) {
			cost = Integer.parseInt(br.readLine());
			addAdj(i, n + i, cost);
		}
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			toll1 = Integer.parseInt(st.nextToken());
			toll2 = Integer.parseInt(st.nextToken());
			addAdj(n + toll1, toll2, INF);
			addAdj(n + toll2, toll1, INF);
		}
		maxFlow(source, sink);
		visited = new boolean[sink + 1];
		dfs(source);
		for (i = 1; i <= n; i++) {
			if (visited[i] && !visited[n + i]) {
				sb.append(i).append(' ');
			}
		}
		System.out.print(sb);
	}
}
