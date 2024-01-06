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
	private static int MAX_CAPACITY = 101;
	private static int MAX_COST = 1001;
	
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
	private static ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
	
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
		Queue<Integer> q = new LinkedList<>();
		
		Arrays.fill(inQueue, false);
		Arrays.fill(distance, MAX_COST);
		distance[source] = 0;
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
		if (distance[sink] < MAX_COST) {
			return true;
		}
		return false;
	}
	
	private static int minCost(int source, int sink) {
		int minFlow, cost, i;
		
		minFlow = MAX_CAPACITY;
		cost = 0;
		while (spfa(source, sink)) {
			for (i = sink; i != source; i = prev[i]) {
				minFlow = Math.min(minFlow, path[i].capacity - path[i].flow);
			}
			for (i = sink; i != source; i = prev[i]) {
				path[i].flow += minFlow;
				path[i].reverse.flow -= minFlow;
				cost += minFlow * path[i].cost;
			}
		}
		return cost;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, source, sink, capacity, cost, i, j;
		int[] books;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		source = 0;
		sink = n + m + 1;
		books = new int[n + m + 1];
		
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			books[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (i = n + 1; i <= n + m; i++) {
			books[i] = Integer.parseInt(st.nextToken());
		}
		
		for (i = 0; i <= sink; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 1; i <= n; i++) {
			addAdj(source, i, books[i], 0);
		}
		for (i = n + 1; i <= n + m; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 1; j <= n; j++) {
				capacity = Math.min(books[i], books[j]);
				cost = Integer.parseInt(st.nextToken());
				addAdj(j, i, capacity, cost);
			}
		}
		for (i = n + 1; i <= n + m; i++) {
			addAdj(i, sink, books[i], 0);
		}
		
		prev = new int[sink + 1];
		path = new Edge[sink + 1];
		inQueue = new boolean[sink + 1];
		distance = new int[sink + 1];
		sb.append(minCost(source, sink));
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}