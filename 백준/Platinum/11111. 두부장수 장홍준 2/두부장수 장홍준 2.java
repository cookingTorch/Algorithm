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
	private static final int MAX_CAP = 3;
	private static final int MIN_DIST = Integer.MIN_VALUE;
	
	private static class Edge {
		int to, capacity, flow, cost;
		Edge reverse;
		
		Edge(int to, int capacity, int cost) {
			this.to = to;
			this.capacity = capacity;
			this.flow = 0;
			this.cost = cost;
		}
	}
	
	private static boolean[] inQueue;
	private static int[] prev, distance;
	private static Edge[] path;
	private static ArrayList<ArrayList<Edge>> adj;
	
	private static int[][] genScore() {
		int[][] score;
		
		score = new int[5][];
		score[0] = new int[] {10, 8, 7, 5, 1};
		score[1] = new int[] {8, 6, 4, 3, 1};
		score[2] = new int[] {7, 4, 3, 2, 1};
		score[3] = new int[] {5, 3, 2, 2, 1};
		score[4] = new int[] {1, 1, 1, 1, 0};
		return score;
	}
	
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
		int curr, dist;
		Queue<Integer> q;
		
		q = new LinkedList<>();
		Arrays.fill(inQueue, false);
		Arrays.fill(distance, MIN_DIST);
		distance[source] = 0;
		q.add(source);
		inQueue[source] = true;
		while (!q.isEmpty()) {
			curr = q.poll();
			inQueue[curr] = false;
			for (Edge line : adj.get(curr)) {
				if (line.capacity - line.flow > 0) {
					dist = distance[curr] + line.cost;
					if (distance[line.to] < dist) {
						distance[line.to] = dist;
						prev[line.to] = curr;
						path[line.to] = line;
						if (!inQueue[line.to]) {
							q.add(line.to);
							inQueue[line.to] = true;
						}
					}
				}
			}
		}
		return distance[sink] > MIN_DIST;
	}
	
	private static int maxCost(int source, int sink) {
		int cost, flow, i;
		
		cost = 0;
		while (lpfa(source, sink)) {
			flow = MAX_CAP;
			for (i = sink; i != source; i = prev[i]) {
				flow = Math.min(flow, path[i].capacity - path[i].flow);
			}
			for (i = sink; i != source; i = prev[i]) {
				path[i].flow += flow;
				path[i].reverse.flow -= flow;
				cost += path[i].cost;
			}
		}
		return cost;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, source, sink, node, i, j;
		int[][] score;
		char[] tofu;
		String str;
		
		score = genScore();
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		source = n * m;
		sink = n * m + 1;
		tofu = new char[n * m];
		for (i = 0; i < n; i++) {
			str = br.readLine();
			for (j = 0; j < m; j++) {
				tofu[i * m + j] = str.charAt(j);
				if (tofu[i * m + j] == 'F') {
					tofu[i * m + j]--;
				}
			}
		}
		adj = new ArrayList<>();
		for (i = 0; i <= sink; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 0; i < n; i++) {
			for (j = 0; j < m; j++) {
				node = i * m + j;
				addAdj(source, node, 2, 0);
				if ((i + j) % 2 == 0) {
					if (j < m - 1) {
						addAdj(node, node + 1, 1, score[tofu[node] - 'A'][tofu[node + 1] - 'A']);
					}
					if (i < n - 1) {
						addAdj(node, node + m, 1, score[tofu[node] - 'A'][tofu[node + m] - 'A']);
					}
					if (j > 0) {
						addAdj(node, node - 1, 1, score[tofu[node] - 'A'][tofu[node - 1] - 'A']);
					}
					if (i > 0) {
						addAdj(node, node - m, 1, score[tofu[node] - 'A'][tofu[node - m] - 'A']);
					}
				}
				addAdj(node, sink, 1, 0);
			}
		}
		inQueue = new boolean[sink + 1];
		distance = new int[sink + 1];
		prev = new int[sink + 1];
		path = new Edge[sink + 1];
		sb.append(maxCost(source, sink));
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}