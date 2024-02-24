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
	private static Queue<Integer> q;
	private static ArrayList<ArrayList<Edge>> adj;
	
	private static void addAdj(int from, int to, int capacity) {
		Edge forward, backward;
		
		forward = new Edge(to, capacity);
		backward = new Edge(from, capacity);
		forward.reverse = backward;
		backward.reverse = forward;
		adj.get(from).add(forward);
		adj.get(to).add(backward);
	}
	
	private static boolean bfs(int source, int sink) {
		int curr;
		
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
		int total, flow, i;
		
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
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m, a, b, c, source, sink, i;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		source = 1;
		sink = n;
		adj = new ArrayList<>();
		for (i = 0; i <= sink; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			addAdj(a, b, c);
		}
		System.out.print(maxFlow(source, sink));
	}
}
