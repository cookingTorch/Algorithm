import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
			this.flow = 0;
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
		q = new LinkedList<>();
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
		int flow, totalFlow, i;
		
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
		prev = new int[sink + 1];
		path = new Edge[sink + 1];
		System.out.print(maxFlow(source, sink));
	}
}