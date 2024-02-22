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
	
	private static int[] prev, distance;
	private static boolean[] inQueue;
	private static Edge[] path;
	private static Queue<Integer> q;
	private static ArrayList<ArrayList<Edge>> adj;
	
	private static void addAdj(int from , int to, int capacity, int cost) {
		Edge forward, backward;
		
		forward = new Edge(to, capacity, cost);
		backward = new Edge(from, 0, -cost);
		forward.reverse = backward;
		backward.reverse = forward;
		adj.get(from).add(forward);
		adj.get(to).add(backward);
	}
	
	private static boolean lpfa(int source, int sink) {
		int curr;
		
		Arrays.fill(distance, MIN);
		distance[source] = 0;
		q = new ArrayDeque<>();
		q.add(source);
		while (!q.isEmpty()) {
			curr = q.poll();
			inQueue[curr] = false;
			for (Edge edge : adj.get(curr)) {
				if (edge.capacity > edge.flow && distance[curr] + edge.cost > distance[edge.to]) {
					distance[edge.to] = distance[curr] + edge.cost;
					prev[edge.to] = curr;
					path[edge.to] = edge;
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
		prev = new int[sink + 1];
		path = new Edge[sink + 1];
		distance = new int[sink + 1];
		inQueue = new boolean[sink + 1];
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
		StringTokenizer st;
		
		int n, source, sink, nil, cap, i, j;
		
		n = Integer.parseInt(br.readLine());
		source = 0;
		nil = n + 1;
		sink = n + 5;
		adj = new ArrayList<>();
		for (i = 0; i <= sink; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 1; i <= n; i++) {
			addAdj(source, i, 1, 0);
		}
		addAdj(source, nil, INF, 0);
		st = new StringTokenizer(br.readLine());
		for (i = sink - 3; i < sink; i++) {
			cap = Integer.parseInt(st.nextToken());
			addAdj(i, sink, cap, 0);
			addAdj(nil, i, cap - 1, 0);
		}
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = sink - 3; j < sink; j++) {
				addAdj(i, j, 1, Integer.parseInt(st.nextToken()));
			}
		}
		System.out.print(maxCost(source, sink));
	}
}
