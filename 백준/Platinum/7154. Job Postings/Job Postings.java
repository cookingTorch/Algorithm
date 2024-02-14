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
	private static final int[][] matrix = {{4, 3, 2, 1}, {8, 7, 6, 5}, {12, 11, 10, 9}};
	
	private static class Edge {
		int to, capacity, flow, cost;
		Edge reverse;
		
		Edge(int to, int capacity, int cost) {
			this.to = to;
			this.capacity = capacity;
			this.cost = cost;
		}
	}
	
	private static int n, m;
	private static int[] prev, distance;
	private static Edge[] path;
	private static boolean[] inQueue;
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
	
	private static boolean lpfa(int source, int sink) {
		int curr;
		
		Arrays.fill(distance, MIN);
		distance[source] = 0;
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
		while (lpfa(source, sink)) {
			flow = INF;
			for (i = sink; i != source; i = prev[i]) {
				flow = Math.min(flow, path[i].capacity - path[i].flow);
			}
			for (i = sink; i != source; i = prev[i]) {
				path[i].flow += flow;
				path[i].reverse.flow -= flow;
				cost += path[i].cost * flow;
			}
		}
		return cost;
	}
	
	private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		int year, source, sink, i, j;
		int[] p;
		
		source = 0;
		sink = m + n + 1;
		p = new int[n + 1];
		for (i = 1; i <= n; i++) {
			p[i] = Integer.parseInt(br.readLine());
		}
		adj = new ArrayList<>();
		for (i = 0; i <= sink; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 1; i <= m; i++) {
			addAdj(source, i, 1, 0);
		}
		for (i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			year = Integer.parseInt(st.nextToken()) - 1;
			for (j = 0; j < 4; j++) {
				addAdj(i, m + 1 + Integer.parseInt(st.nextToken()), 1, matrix[year][j]);
			}
		}
		for (i = 1; i <= n; i++) {
			addAdj(m + i, sink, p[i], 0);
		}
		prev = new int[sink + 1];
		path = new Edge[sink + 1];
		distance = new int[sink + 1];
		inQueue = new boolean[sink + 1];
		q = new ArrayDeque<>();
		return maxCost(source, sink);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		while (n != 0) {
			sb.append(solution(br, st)).append('\n');
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
		}
		System.out.print(sb);
	}
}
