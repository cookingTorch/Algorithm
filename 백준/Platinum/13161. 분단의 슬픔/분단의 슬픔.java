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
	private static boolean[] visited;
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
	
	private static void visit(int node) {
		if (visited[node]) {
			return;
		}
		visited[node] = true;
		for (Edge edge : adj.get(node)) {
			if (edge.capacity > edge.flow) {
				visit(edge.to);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, source, sink, sad, i, j;
		int[] camp;
		
		n = Integer.parseInt(br.readLine());
		source = 0;
		sink = n + 1;
		adj = new ArrayList<>(sink + 1);
		for (i = 0; i <= sink; i++) {
			adj.add(new ArrayList<>());
		}
		camp = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			camp[i] = Integer.parseInt(st.nextToken());
		}
		for (i = 1; i <= n; i++) {
			if (camp[i] == 1) {
				addAdj(source, i, INF, false);
			} else if (camp[i] == 2) {
				addAdj(i, sink, INF, false);
			}
		}
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 1; j <= n; j++) {
				sad = Integer.parseInt(st.nextToken());
				if (j > i && sad != 0) {
					addAdj(i, j, sad, true);
				}
			}
		}
		sb.append(dinic(source, sink)).append('\n');
		visited = new boolean[sink + 1];
		visit(source);
		for (i = 1; i <= n; i++) {
			if (visited[i]) {
				sb.append(i).append(' ');
			}
		}
		sb.append('\n');
		for (i = 1; i <= n; i++) {
			if (!visited[i]) {
				sb.append(i).append(' ');
			}
		}
		sb.append('\n');
		System.out.print(sb);
	}
}
