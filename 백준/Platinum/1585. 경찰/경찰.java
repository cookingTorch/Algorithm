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
	private static final int MIN = Integer.MIN_VALUE;
	
	private static class Edge {
		int to, capacity, flow, cost;
		Edge reverse;
		
		Edge(int to, int capacity, int cost) {
			this.to = to;
			this.capacity = capacity;
			this.cost = cost;
		}
	}
	
	private static int totalFlow;
	private static int[] prev, distance;
	private static boolean[] inQueue;
	private static Edge[] path;
	private static Queue<Integer> q;
	private static ArrayList<ArrayList<Edge>> adj;
	
	private static void addAdj(int from, int to, int capacity, int cost) {
		Edge forward, backward;
		
		forward = new Edge(to, capacity, cost);
		backward = new Edge(from, 0, -cost);
		forward.reverse = backward;
		backward.reverse = forward;
		adj.get(from).add(forward);
		adj.get(to).add(backward);
	}
	
	private static boolean spfa(int source, int sink) {
		int curr;
		
		Arrays.fill(distance, INF);
		distance[source] = 0;
		q.clear();
		q.add(source);
		while (!q.isEmpty()) {
			curr = q.poll();
			inQueue[curr] = false;
			for (Edge edge : adj.get(curr)) {
				if (edge.capacity > edge.flow && distance[curr] + edge.cost < distance[edge.to]) {
					prev[edge.to] = curr;
					path[edge.to] = edge;
					distance[edge.to] = distance[curr] + edge.cost;
					if (!inQueue[edge.to]) {
						q.add(edge.to);
						inQueue[edge.to] = true;
					}
				}
			}
		}
		if (distance[sink] == INF) {
			return false;
		}
		return true;
	}
	
	private static int minCost(int source, int sink) {
		int flow, cost, i;
		
		totalFlow = 0;
		cost = 0;
		while (spfa(source, sink)) {
			flow = INF;
			for (i = sink; i != source; i = prev[i]) {
				flow = Math.min(flow, path[i].capacity - path[i].flow);
			}
			for (i = sink; i != source; i = prev[i]) {
				path[i].flow += flow;
				path[i].reverse.flow -= flow;
				cost += flow * path[i].cost;
			}
			totalFlow += flow;
		}
		return cost;
	}
	
	private static boolean lpfa(int source, int sink) {
		int curr;
		
		Arrays.fill(distance, MIN);
		distance[source] = 0;
		q.clear();
		q.add(source);
		while (!q.isEmpty()) {
			curr = q.poll();
			inQueue[curr] = false;
			for (Edge edge : adj.get(curr)) {
				if (edge.capacity > edge.flow && distance[curr] + edge.cost > distance[edge.to]) {
					prev[edge.to] = curr;
					path[edge.to] = edge;
					distance[edge.to] = distance[curr] + edge.cost;
					if (!inQueue[edge.to]) {
						q.add(edge.to);
						inQueue[edge.to] = true;
					}
				}
			}
		}
		if (distance[sink] == MIN) {
			return false;
		}
		return true;
	}
	
	private static int maxCost(int source, int sink) {
		int flow, cost, i;
		
		cost = 0;
		while (lpfa(source, sink)) {
			flow = INF;
			for (i = sink; i != source; i = prev[i]) {
				flow = Math.min(flow, path[i].capacity - path[i].flow);
			}
			for (i = sink; i != source; i = prev[i]) {
				path[i].flow += flow;
				path[i].reverse.flow -= flow;
				cost += flow * path[i].cost;
			}
		}
		return cost;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, t, f, source, sink, s, min, i, j;
		int[] in, out;
		
		n = Integer.parseInt(br.readLine());
		source = 0;
		sink = 2 * n + 1;
		adj = new ArrayList<>();
		for (i = 0; i <= sink; i++) {
			adj.add(new ArrayList<>());
		}
		in = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			addAdj(source, i, 1, 0);
			in[i] = Integer.parseInt(st.nextToken());
		}
		out = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			addAdj(n + i, sink, 1, 0);
			out[i] = Integer.parseInt(st.nextToken());
		}
		t = Integer.parseInt(br.readLine());
		f = Integer.parseInt(br.readLine());
		for (i = 1; i <= n; i++) {
			for (j = 1; j <= n; j++) {
				if ((s = out[j] - in[i]) > 0) {
					addAdj(i, n + j, 1, s < t ? Math.min(f, (t - s) * (t - s)) : 0);
				}
			}
		}
		prev = new int[sink + 1];
		path = new Edge[sink + 1];
		distance = new int[sink + 1];
		inQueue = new boolean[sink + 1];
		q = new ArrayDeque<>();
		min = minCost(source, sink);
		if (totalFlow != n) {
			System.out.print("-1");
			return;
		}
		for (i = 0; i <= sink; i++) {
			for (Edge edge : adj.get(i)) {
				edge.flow = 0;
			}
		}
		sb.append(min).append(' ').append(maxCost(source, sink));
		System.out.print(sb);
	}
}
