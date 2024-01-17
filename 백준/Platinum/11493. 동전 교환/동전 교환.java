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
	private static final int MAX_CAP = Integer.MAX_VALUE - 1;
	private static final int INF = Integer.MAX_VALUE;
	
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
	
	private static void addAdj(int from, int to, int capacity, int cost) {
		Edge forward, backward;
		
		forward = new Edge(to, capacity, cost);
		backward = new Edge(from, 0, -cost);
		forward.reverse = backward;
		backward.reverse = forward;
		adj.get(from).add(forward);
		adj.get(to).add(backward);
	}
	
	private static boolean spfa(int source, int sink) {
		int curr, dist;
		Queue<Integer> q;
		
		Arrays.fill(inQueue, false);
		Arrays.fill(distance, INF);
		distance[source] = 0;
		q = new LinkedList<>();
		q.add(source);
		inQueue[source] = true;
		while (!q.isEmpty()) {
			curr = q.poll();
			inQueue[curr] = false;
			for (Edge line : adj.get(curr)) {
				if (line.capacity - line.flow > 0) {
					dist = distance[curr] + line.cost;
					if (dist < distance[line.to]) {
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
		return distance[sink] < INF;
	}
	
	private static int mcmf(int source, int sink) {
		int cost, flow, i;
		
		cost = 0;
		while (spfa(source, sink)) {
			flow = INF;
			for (i = sink; i != source; i = prev[i]) {
				flow = Math.min(flow, path[i].capacity - path[i].flow);
			}
			for (i = sink; i != source; i = prev[i]) {
				path[i].flow += flow;
				path[i].reverse.flow -= flow;
				cost += path[i].cost * flow;
			}
		}
		return cost;
	}
	
	private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		int n, m, x, y, source, sink, i;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		source = 0;
		sink = n + 1;
		adj = new ArrayList<>();
		for (i = 0; i <= sink; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			addAdj(x, y, MAX_CAP, 1);
			addAdj(y, x, MAX_CAP, 1);
		}
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			if (Integer.parseInt(st.nextToken()) == 0) {
				addAdj(i, sink, 1, 0);
			}
		}
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			if (Integer.parseInt(st.nextToken()) == 0) {
				addAdj(source, i, 1, 0);
			}
		}
		inQueue = new boolean[sink + 1];
		distance = new int[sink + 1];
		prev = new int[sink + 1];
		path = new Edge[sink + 1];
		return mcmf(source, sink);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		
		t = Integer.parseInt(br.readLine());
		for (testCase = 0; testCase < t; testCase++) {
			sb.append(solution(br, st)).append('\n');
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}