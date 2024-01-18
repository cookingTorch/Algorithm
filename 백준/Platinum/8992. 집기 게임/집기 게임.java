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
	private static final int MIN = Integer.MIN_VALUE + 1;
	private static final int INF = Integer.MAX_VALUE - 1;
	
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
	
	private static int[] prev, distance;
	private static Edge[] path;
	private static boolean[] inQueue;
	private static ArrayList<ArrayList<Edge>> adj;
	
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
		Queue<Integer> q = new LinkedList<>();
		
		Arrays.fill(inQueue, false);
		Arrays.fill(distance, MIN);
		distance[source] = 0;
		q.add(source);
		inQueue[source] = true;
		while (!q.isEmpty()) {
			curr = q.poll();
			inQueue[curr] = false;
			for (Edge line : adj.get(curr)) {
				if (line.capacity - line.flow > 0) {
					dist = distance[curr] + line.cost;
					if (dist > distance[line.to]) {
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
		return distance[sink] > MIN;
	}
	
	private static int[] mcmf(int source, int sink) {
		int cost, flow, totalFlow, i;
		
		cost = 0;
		totalFlow = 0;
		while (lpfa(source, sink)) {
			flow = INF;
			for (i = sink; i != source; i = prev[i]) {
				flow = Math.min(flow, path[i].capacity - path[i].flow);
			}
			for (i = sink; i != source; i = prev[i]) {
				path[i].flow += flow;
				path[i].reverse.flow -= flow;
				cost += path[i].cost * flow;
			}
			totalFlow += flow;
		}
		return new int[] {totalFlow, cost};
	}
	
	private static int[] solution(BufferedReader br, StringTokenizer st) throws IOException {
		int n, m, x, x2, y, y2, w, temp, source, sink, i, j;
		int[][] row;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		source = 0;
		sink = n + m + 1;
		adj = new ArrayList<>();
		for (i = 0; i <= sink; i++) {
			adj.add(new ArrayList<>());
		}
		row = new int[n][4];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			row[i][0] = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			st.nextToken();
			row[i][3] = Integer.parseInt(st.nextToken());
			row[i][1] = Math.min(x, x2);
			row[i][2] = Math.max(x, x2);
		}
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			st.nextToken();
			y2 = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			temp = Math.min(y, y2);
			y2 = Math.max(y, y2);
			y = temp;
			for (j = 0; j < n; j++) {
				if ((row[j][1] <= x && x <= row[j][2]) && (y <= row[j][0] && row[j][0] <= y2)) {
					addAdj(j + 1, n + i + 1, 1, row[j][3] * w);
				}
			}
		}
		for (i = 1; i <= n; i++) {
			addAdj(source, i, 1, 0);
		}
		for (i = n + 1; i <= n + m; i++) {
			addAdj(i, sink, 1, 0);
		}
		prev = new int[sink + 1];
		path = new Edge[sink + 1];
		distance = new int[sink + 1];
		inQueue = new boolean[sink + 1];
		return mcmf(source, sink);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		int[] ans;
		
		t = Integer.parseInt(br.readLine());
		for (testCase = 0; testCase < t; testCase++) {
			ans = solution(br, st);
			sb.append(ans[0]).append(' ').append(ans[1]).append('\n');
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}