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
	private static Edge[][] exist;
	private static Queue<Integer> q;
	private static ArrayList<ArrayList<Edge>> adj;
	
	private static void addAdj(int from, int to, int capacity) {
		Edge forward, backward;
		
		if (exist[from][to] != null) {
			exist[from][to].capacity += capacity;
			exist[to][from].capacity += capacity;
			return;
		}
		forward = new Edge(to, capacity);
		backward = new Edge(from, capacity);
		forward.reverse = backward;
		backward.reverse = forward;
		adj.get(from).add(forward);
		adj.get(to).add(backward);
		exist[from][to] = forward;
		exist[to][from] = backward;
	}
	
	private static boolean bfs(int source, int sink) {
		int curr;
		
		Arrays.fill(prev, -1);
		q.clear();
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
		
		int n, m, a, b, c, s, t, i;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		adj = new ArrayList<>();
		for (i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}
		exist = new Edge[n][n];
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken()) - 1;
			b = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken());
			addAdj(a, b, c);
		}
		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken()) - 1;
		t = Integer.parseInt(st.nextToken()) - 1;
		prev = new int[n];
		path = new Edge[n];
		q = new ArrayDeque<>();
		System.out.print(maxFlow(s, t));
	}
}
