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
	private static int MAX_CAPACITY = 401;
	private static int MIN_COST = -40001;
	
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
	
	private static boolean lpfa(int source, int sink) {
		int curr, dist;
		Queue<Integer> q = new LinkedList<>();
		
		Arrays.fill(distance, MIN_COST);
		Arrays.fill(inQueue, false);
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
		if (distance[sink] > MIN_COST) {
			return true;
		}
		return false;
	}
	
	private static int[] maxFlowMaxCost(int source, int sink) {
		int maxFlow, flow, cost, i;
		
		maxFlow = 0;
		cost = 0;
		while (lpfa(source, sink)) {
			flow = MAX_CAPACITY;
			for (i = sink; i != source; i = prev[i]) {
				flow = Math.min(flow, path[i].capacity - path[i].flow);
			}
			for (i = sink; i != source; i = prev[i]) {
				path[i].flow += flow;
				path[i].reverse.flow -= flow;
				cost += flow * path[i].cost;
			}
			maxFlow += flow;
		}
		return new int[] {maxFlow, cost};
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, source, sink, possible, work, salary, i, j;
		int[] mfmc;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		source = 0;
		sink = n + m + 1;
		
		adj = new ArrayList<>();
		for (i = 0; i <= sink; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 1; i <= n; i++) {
			addAdj(source, i, 1, 0);
		}
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			possible = Integer.parseInt(st.nextToken());
			for (j = 0; j < possible; j++) {
				work = n + Integer.parseInt(st.nextToken());
				salary = Integer.parseInt(st.nextToken());
				addAdj(i, work, 1, salary);
			}
		}
		for (i = n + 1; i <= n + m; i++) {
			addAdj(i, sink, 1, 0);
		}
		
		prev = new int[sink + 1];
		path = new Edge[sink + 1];
		inQueue = new boolean[sink + 1];
		distance = new int[sink + 1];
		mfmc = maxFlowMaxCost(source, sink);
		sb.append(mfmc[0]).append('\n').append(mfmc[1]);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}