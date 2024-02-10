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
	
	private static class Edge {
		int to, capacity, flow;
		Edge reverse;
		
		Edge(int to, int capacity) {
			this.to = to;
			this.capacity = capacity;
		}
	}
	
	private static int[] prev;
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
				if (edge.capacity - edge.flow > 0 && prev[edge.to] == -1) {
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
		int totalFlow, flow, i;
		
		totalFlow = 0;
		while (bfs(source, sink)) {
			flow = INF;
			for (i = sink; i != source; i = prev[i]) {
				flow = Math.min(flow, path[i].capacity - path[i].flow);
			}
			for (i = sink; i != source; i = prev[i]) {
				path[i].flow += flow;
				path[i].reverse.flow -= flow;
			}
			totalFlow += flow;
		}
		return totalFlow;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, k, d, z, source, sink, i, j;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		source = 0;
		sink = n + d + 1;
		adj = new ArrayList<>();
		for (i = 0; i <= sink; i++) {
			adj.add(new ArrayList<>());
		}
		st = new StringTokenizer(br.readLine());
		for (i = n + 1; i < sink; i++) {
			addAdj(i, sink, Integer.parseInt(st.nextToken()));
		}
		for (i = 1; i <= n; i++) {
			addAdj(source, i, k);
			st = new StringTokenizer(br.readLine());
			z = Integer.parseInt(st.nextToken());
			for (j = 0; j < z; j++) {
				addAdj(i, n + Integer.parseInt(st.nextToken()), 1);
			}
		}
		prev = new int[sink + 1];
		path = new Edge[sink + 1];
		System.out.print(maxFlow(source, sink));
	}
}
