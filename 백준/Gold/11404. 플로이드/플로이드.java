import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	
	private static int[] distance;
	private static boolean[] inQueue;
	private static Queue<Integer> q;
	
	private static class Edge {
		int to, cost;
		
		Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}
	
	private static ArrayList<ArrayList<Edge>> adj;
	
	private static void spfa(int start) {
		int curr;
		
		Arrays.fill(distance, INF);
		distance[start] = 0;
		q.add(start);
		while (!q.isEmpty()) {
			curr = q.poll();
			inQueue[curr] = false;
			for (Edge edge : adj.get(curr)) {
				if (distance[curr] + edge.cost < distance[edge.to]) {
					distance[edge.to] = distance[curr] + edge.cost;
					if (!inQueue[edge.to]) {
						q.add(edge.to);
						inQueue[edge.to] = true;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, i, j;
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		adj = new ArrayList<>();
		for (i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			adj.get(Integer.parseInt(st.nextToken()) - 1).add(new Edge(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken())));
		}
		distance = new int[n];
		inQueue = new boolean[n];
		q = new ArrayDeque<>();
		for (i = 0; i < n; i++) {
			spfa(i);
			for (j = 0; j < n; j++) {
				sb.append(distance[j] == INF ? 0 : distance[j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
}
