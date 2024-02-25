import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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
	private static ArrayList<Edge> limits;
	private static ArrayList<LinkedList<Edge>> adj;
	
	private static void addAdj(int from, int to, int capacity, boolean limited) {
		Edge forward, backward;
		
		forward = new Edge(to, capacity);
		backward = new Edge(from, 0);
		forward.reverse = backward;
		backward.reverse = forward;
		adj.get(from).add(forward);
		adj.get(to).add(backward);
		if (limited) {
			limits.add(forward);
		}
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
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, source, sink, cap, sum, left, right, mid, idx, i, j;
		
		n = Integer.parseInt(br.readLine());
		source = 0;
		sink = 2 * n + 1;
		adj = new ArrayList<>();
		for (i = 0; i <= sink; i++) {
			adj.add(new LinkedList<>());
		}
		limits = new ArrayList<>();
		right = 0;
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			cap = Integer.parseInt(st.nextToken());
			addAdj(source, i, cap, false);
			right = Math.max(right, cap);
		}
		for (i = 1; i <= n; i++) {
			for (j = 1; j <= n; j++) {
				addAdj(i, n + j, 0, true);
			}
		}
		sum = 0;
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			cap = Integer.parseInt(st.nextToken());
			addAdj(n + i, sink, cap, false);
			sum += cap;
			right = Math.max(right, cap);
		}
		path = new Edge[sink + 1];
		left = 0;
		while (left < right) {
			mid = (left + right) / 2;
			for (Edge edge : limits) {
				edge.capacity = mid;
			}
			for (i = 0; i <= sink; i++) {
				for (Edge edge : adj.get(i)) {
					edge.flow = 0;
				}
			}
			if (maxFlow(source, sink) == sum) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		for (Edge edge : limits) {
			edge.capacity = right;
		}
		for (i = 0; i <= sink; i++) {
			for (Edge edge : adj.get(i)) {
				edge.flow = 0;
			}
		}
		maxFlow(source, sink);
		sb.append(right);
		idx = 0;
		for (i = 1; i <= n; i++) {
			sb.append('\n');
			for (j = 1; j <= n; j++) {
				sb.append(limits.get(idx++).flow).append(' ');
			}
		}
		System.out.print(sb);
	}
}
