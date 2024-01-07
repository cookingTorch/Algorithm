import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int MAX_CAPACITY = 1001;
	
	private static class Edge {
		int to, capacity, flow;
		Edge reverse;
		
		Edge(int to, int capacity) {
			this.to = to;
			this.capacity = capacity;
			flow = 0;
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
		Queue<Integer> q = new LinkedList<>();
		
		Arrays.fill(prev, -1);
		q.add(source);
		while (!q.isEmpty()) {
			curr = q.poll();
			for (Edge line : adj.get(curr)) {
				if (line.capacity - line.flow > 0 && prev[line.to] == -1) {
					prev[line.to] = curr;
					path[line.to] = line;
					if (line.to == sink) {
						return true;
					}
					q.add(line.to);
				}
			}
		}
		return false;
	}
	private static int maxFlow(int source, int sink) {
		int flow, totalFlow, i;
		
		totalFlow = 0;
		while(bfs(source, sink)) {
			flow = MAX_CAPACITY;
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
	
	private static void solution(BufferedReader br, StringBuilder sb, StringTokenizer st) throws IOException {
		int n, m, source, sink, sum, node, i, j;
		int[][] matrix;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		source = n * m;
		sink = n * m + 1;
		
		matrix = new int[n][m];
		sum = 0;
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < m; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
				sum += matrix[i][j];
			}
		}
		
		adj = new ArrayList<>();
		for (i = 0; i <= sink; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 0; i < n; i++) {
			for (j = 0; j < m; j++) {
				node = i * m + j;
				if ((i + j) % 2 == 0) {
					addAdj(source, node, matrix[i][j]);
					if (i > 0) {
						addAdj(node, node - m, MAX_CAPACITY - 1);
					}
					if (j < m - 1) {
						addAdj(node, node + 1, MAX_CAPACITY - 1);
					}
					if (i < n - 1) {
						addAdj(node, node + m, MAX_CAPACITY - 1);
					}
					if (j > 0) {
						addAdj(node, node - 1, MAX_CAPACITY - 1);
					}
				} else {
					addAdj(node, sink, matrix[i][j]);
				}
			}
		}
		
		prev = new int[sink + 1];
		path = new Edge[sink + 1];
		sb.append(sum - maxFlow(source, sink)).append('\n');
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		
		t = Integer.parseInt(br.readLine());
		for (testCase = 0; testCase < t; testCase++) {
			solution(br, sb, st);
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}