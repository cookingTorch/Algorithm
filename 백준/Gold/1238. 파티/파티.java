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
	
	private static int x;
	private static PriorityQueue<Integer> pq;
	
	private static void dijkstra(int[] distance, ArrayList<LinkedList<Edge>> adj) {
		int curr, next;
		
		Arrays.fill(distance, INF);
		distance[x] = 0;
		pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(distance[o1], distance[o2]);
			}
		});
		pq.add(x);
		while (!pq.isEmpty()) {
			curr = pq.poll();
			for (Edge edge : adj.get(curr)) {
				next = edge.to;
				if (distance[curr] + edge.weight < distance[next]) {
					distance[next] = distance[curr] + edge.weight;
					pq.add(next);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m, a, b, weight, ans, i;
		int[] distanceIn, distanceOut;
		ArrayList<LinkedList<Edge>> adjIn, adjOut;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		adjIn = new ArrayList<>();
		for (i = 0; i <= n; i++) {
			adjIn.add(new LinkedList<>());
		}
		adjOut = new ArrayList<>();
		for (i = 0; i <= n; i++) {
			adjOut.add(new LinkedList<>());
		}
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			adjIn.get(b).add(new Edge(a, weight));
			adjOut.get(a).add(new Edge(b, weight));
		}
		distanceIn = new int[n + 1];
		distanceOut = new int[n + 1];
		dijkstra(distanceIn, adjIn);
		dijkstra(distanceOut, adjOut);
		ans = 0;
		for (i = 1; i <= n; i++) {
			ans = Math.max(ans, distanceIn[i] + distanceOut[i]);
		}
		System.out.print(ans);
	}
}
