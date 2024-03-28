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
		boolean on;
		Edge reverse;
		
		Edge(int to, int capacity) {
			this.to = to;
			this.capacity = capacity;
			on = true;
		}
	}
	
	private static int n;
	private static int[] level, work;
	private static Queue<Integer> q;
	private static ArrayList<Edge> edges;
	private static ArrayList<ArrayList<Edge>> adj;
	
	private static void addAdj(int from, int to, int capacity) {
		Edge forward, backward;
		
		forward = new Edge(to, capacity);
		backward = new Edge(from, capacity);
		forward.reverse = backward;
		backward.reverse = forward;
		adj.get(from).add(forward);
		adj.get(to).add(backward);
		edges.add(forward);
	}
	
	private static boolean bfs(int source, int sink) {
		int curr;
		
		Arrays.fill(level, INF);
		level[source] = 0;
		q.add(source);
		while (!q.isEmpty()) {
			curr = q.poll();
			for (Edge edge : adj.get(curr)) {
				if (edge.on && level[edge.to] == INF && edge.capacity > edge.flow) {
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
			if (edge.on && level[edge.to] == level[curr] + 1 && edge.capacity > edge.flow) {
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
		
		level = new int[n + 1];
		q = new ArrayDeque<>();
		total = 0;
		while(bfs(source, sink)) {
			work = new int[n + 1];
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
		
		int m, source, sink, weight, sum, min, i;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		adj = new ArrayList<>(n + 1);
		for (i = 0; i <= n; i++) {
			adj.add(new ArrayList<>());
		}
		edges = new ArrayList<>();
		sum = 0;
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			addAdj(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), (weight = Integer.parseInt(st.nextToken())));
			sum += weight;
		}
		st = new StringTokenizer(br.readLine());
		source = Integer.parseInt(st.nextToken());
		sink = Integer.parseInt(st.nextToken());
		min = INF;
		for (Edge off : edges) {
			for (Edge edge : edges) {
				edge.flow = 0;
				edge.reverse.flow = 0;
			}
			off.on = false;
			off.reverse.on = false;
			min = Math.min(min, dinic(source, sink));
			off.on = true;
			off.reverse.on = true;
		}
		System.out.print(sum - min);
	}
}
