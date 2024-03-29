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
			this.flow = 0;
		}
	}
	
	private static int[] level, work;
	private static Queue<Integer> q;
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
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String str;
		
		int r, c, size, source, sink, node, i, j;
		char[] chess;
		
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		size = r * c;
		source = 2 * size;
		sink = 2 * size + 1;
		adj = new ArrayList<>();
		for (i = 0; i <= sink; i++) {
			adj.add(new ArrayList<>());
		}
		chess = new char[size];
		for (i = 0; i < r; i++) {
			str = br.readLine();
			for (j = 0; j < c; j++) {
				node = i * c + j;
				chess[node] = str.charAt(j);
				if (chess[node] == '.') {
					addAdj(node, node + size, 1);
					if ((i + j) % 2 == 1) {
						if (i % 2 == 0) {
							addAdj(source, node, 1);
						} else {
							addAdj(node + size, sink, 1);
						}
					}
				}
			}
		}
		for (i = 0; i < r; i += 2) {
			for (j = 1; j < c; j += 2) {
				node = i * c + j;
				if (chess[node] == '.') {
					if (i > 0 && chess[node - c] == '.') {
						addAdj(node + size, node - c, 1);
					}
					if (i < r - 1 && chess[node + c] == '.') {
						addAdj(node + size, node + c, 1);
					}
					if (j > 0 && chess[node - 1] == '.') {
						addAdj(node + size, node - 1, 1);
					}
					if (j < c - 1 && chess[node + 1] == '.') {
						addAdj(node + size, node + 1, 1);
					}
				}
			}
		}
		for (i = 0; i < r; i += 2) {
			for (j = 0; j < c; j += 2) {
				node = i * c + j;
				if (chess[node] == '.') {
					if (i > 0 && chess[node - c] == '.') {
						addAdj(node + size, node - c, 1);
					}
					if (i < r - 1 && chess[node + c] == '.') {
						addAdj(node + size, node + c, 1);
					}
				}
			}
		}
		for (i = 1; i < r; i += 2) {
			for (j = 1; j < c; j += 2) {
				node = i * c + j;
				if (chess[node] == '.') {
					if (j > 0 && chess[node - 1] == '.') {
						addAdj(node + size, node - 1, 1);
					}
					if (j < c - 1 && chess[node + 1] == '.') {
						addAdj(node + size, node + 1, 1);
					}
				}
			}
		}
		System.out.print(dinic(source, sink));
	}
}
