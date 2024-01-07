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
	private static int MAX_CAPACITY = 51;
	
	private static class Edge {
		boolean forward;
		int to, capacity, flow;
		Edge reverse;
		
		Edge(int to, int capacity, boolean forward) {
			this.to = to;
			this.capacity = capacity;
			this.flow = 0;
			this.forward = forward;
		}
	}
	
	private static int[] prev;
	private static Edge[] path;
	private static ArrayList<ArrayList<Edge>> adj;
	
	private static void addAdj(int from, int to, int capacity) {
		Edge forward, backward;
		
		forward = new Edge(to, capacity, true);
		backward = new Edge(from, 0, false);
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
	
	private static boolean fix(int source, int sink) {
		int curr;
		Queue<Integer> q = new LinkedList<>();
		
		Arrays.fill(prev, -1);
		q.add(source);
		while (!q.isEmpty()) {
			curr = q.poll();
			for (Edge line : adj.get(curr)) {
				if (curr < source || (curr == source && line.to <= sink)) {
					continue;
				}
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
	
	private static int mcmf(int source, int sink, int n, int m) {
		int flow, totalFlow, i, j, k;
		
		totalFlow = 0;
		Arrays.fill(prev, -1);
		while (bfs(source, sink)) {
			flow = MAX_CAPACITY;
			for (i = sink; i != source; i = prev[i]) {
				flow = Math.min(flow, path[i].capacity - path[i].flow);
			}
			for (i = sink; i != source; i = prev[i]) {
				path[i].flow += flow;
				path[i].reverse.flow -= flow;
			}
			totalFlow += flow;
			Arrays.fill(prev, -1);
		}
		
		for (i = 1; i <= n; i++) {
			for (j = n + 1; j <= n + m; j++) {
				for (Edge line : adj.get(i)) {
					if (line.to == j && line.flow == 1) {
						line.flow = 0;
						if (fix(i, j)) {
							flow = MAX_CAPACITY;
							for (k = j; k != i; k = prev[k]) {
								flow = Math.min(flow, path[k].capacity - path[k].flow);
							}
							for (k = j; k != i; k = prev[k]) {
								path[k].flow += flow;
								path[k].reverse.flow -= flow;
							}
						} else {
							line.flow = 1;
						}
					}
				}
			}
		}
		
		return totalFlow;
	}
	
	private static void matchup(StringBuilder sb, int n, int m) {
		int i, j;
		int[][] matrix;
		
		matrix = new int[n][m];
		for (i = 1; i <= n; i++) {
			for (Edge line : adj.get(i)) {
				if (line.forward) {
					matrix[i - 1][line.to - n - 1] = line.flow;
				}
			}
		}
		for (i = 0; i < n; i++) {
			for (j = 0; j < m; j++) {
				sb.append(matrix[i][j]);
			}
			sb.append('\n');
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, source, sink, capacity, maxFlow, i, j;
		int[] sum;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		source = 0;
		sink = n + m + 1;
		
		sum = new int[2];
		adj = new ArrayList<>();
		for (i = source; i <= sink; i++) {
			adj.add(new ArrayList<>());
		}
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			capacity = Integer.parseInt(st.nextToken());
			addAdj(source, i, capacity);
			sum[0] += capacity;
			
		}
		for (i = 1; i <= n; i++) {
			for (j = n + 1; j <= n + m; j++) {
				addAdj(i, j, 1);
			}
		}
		st = new StringTokenizer(br.readLine());
		for (i = n + 1; i <= n + m; i++) {
			capacity = Integer.parseInt(st.nextToken());
			addAdj(i, sink, capacity);
			sum[1] += capacity;
		}
		
		if (sum[0] != sum[1] || n * m < sum[0]) {
			sb.append(-1);
		} else {
			prev = new int[sink + 1];
			path = new Edge[sink + 1];
			maxFlow = mcmf(source, sink, n, m);
			if (maxFlow != sum[0]) {
				sb.append(-1);
			} else {
				matchup(sb, n, m);
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
