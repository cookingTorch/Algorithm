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
	private static final int INF = Integer.MAX_VALUE - 1;
	private static final int MIN = Integer.MIN_VALUE + 1;
	
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
	
	private static boolean spfa(int source, int sink) {
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
	
	private static void addAdj(int from, int to, int capacity, int cost) {
		Edge forward, backward;
		
		forward = new Edge(to, capacity, cost);
		backward = new Edge(from, 0, -cost);
		forward.reverse = backward;
		backward.reverse = forward;
		adj.get(from).add(forward);
		adj.get(to).add(backward);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, p, source, sink, cost, i, j;
		int[][] cnt;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());
		source = 0;
		sink = n + 1;
		cnt = new int[n + 1][n + 1];
		for (i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = i + 1; j <= n; j++) {
				cnt[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		adj = new ArrayList<>();
		for (i = 0; i <= sink; i++) {
			adj.add(new ArrayList<>());
		}
		addAdj(source, 1, p, 0);
		for (i = 1; i < n; i++) {
			addAdj(i, i + 1, INF, 0);
		}
		for (i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = i + 1; j <= n; j++) {
				cost = Integer.parseInt(st.nextToken());
				addAdj(i, j, cnt[i][j], cost);
			}
		}
		addAdj(n, sink, p, 0);
		
		prev = new int[sink + 1];
		path = new Edge[sink + 1];
		inQueue = new boolean[sink + 1];
		distance = new int[sink + 1];
		sb.append(mcmf(source, sink));
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}