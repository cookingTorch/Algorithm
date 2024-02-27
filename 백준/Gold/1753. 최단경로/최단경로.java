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
		int to, weight;
		
		Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
	
	private static int[] distance;
	private static boolean[] inQueue;
	private static ArrayList<LinkedList<Edge>> adj;
	
	private static void dijkstra(int start) {
		int curr;
		PriorityQueue<Integer> pq;

		Arrays.fill(distance, INF);
		distance[start] = 0;
		pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(distance[o1], distance[o2]);
			}
		});
		pq.add(start);
		while (!pq.isEmpty()) {
			curr = pq.poll();
			inQueue[curr] = false;
			for (Edge edge : adj.get(curr)) {
				if (distance[curr] + edge.weight < distance[edge.to]) {
					distance[edge.to] = distance[curr] + edge.weight;
					if (!inQueue[edge.to]) {
						pq.add(edge.to);
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
		
		int num, e, k, u, v, w, i;
		
		st = new StringTokenizer(br.readLine());
		num = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(br.readLine()) - 1;
		adj = new ArrayList<>();
		for (i = 0; i < num; i++) {
			adj.add(new LinkedList<>());
		}
		for (i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken()) - 1;
			v = Integer.parseInt(st.nextToken()) - 1;
			w = Integer.parseInt(st.nextToken());
			adj.get(u).add(new Edge(v, w));
		}
		distance = new int[num];
		inQueue = new boolean[num];
		dijkstra(k);
		for (i = 0; i < num; i++) {
			sb.append(distance[i] == INF ? "INF" : distance[i]).append('\n');
		}
		System.out.print(sb);
	}
}
