import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	
	private static class Edge {
		int to, cost;
		
		Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}
	
	private static int n;
	private static ArrayList<LinkedList<Edge>> adj;
	
	private static int dijkstra(int start, int end) {
		int curr;
		int[] distance;
		boolean[] visited;
		PriorityQueue<Integer> pq;
		
		distance = new int[n + 1];
		Arrays.fill(distance, INF);
		distance[start] = 0;
		pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(distance[o1], distance[o2]);
			}
		});
		pq.add(start);
		visited = new boolean[n + 1];
		while (!pq.isEmpty()) {
			curr = pq.poll();
			visited[curr] = true;
			if (curr == end) {
				return distance[end];
			}
			for (Edge edge : adj.get(curr)) {
				if (!visited[edge.to] && distance[curr] + edge.cost < distance[edge.to]) {
					distance[edge.to] = distance[curr] + edge.cost;
					pq.add(edge.to);
				}
			}
		}
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int m, u, v, cost, i;
		Edge[][] edges;
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		adj = new ArrayList<>();
		for (i = 0; i <= n; i++) {
			adj.add(new LinkedList<>());
		}
		edges = new Edge[n + 1][n + 1];
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			if (edges[u][v] == null) {
				edges[u][v] = new Edge(v, cost);
				adj.get(u).add(edges[u][v]);
				
			} else {
				edges[u][v].cost = Math.min(edges[u][v].cost, cost);
			}
		}
		st = new StringTokenizer(br.readLine());
		System.out.print(dijkstra(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
	}
}
