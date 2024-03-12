import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
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
	
	private static int n, x;
	private static boolean[] inQueue;
	private static Queue<Integer> q;
	
	private static void spfa(int[] distance, ArrayList<LinkedList<Edge>> adj) {
		int curr, next, i;
		
		for (i = 1; i <= n; i++) {
			distance[i] = INF;
		}
		distance[x] = 0;
		q.add(x);
		while (!q.isEmpty()) {
			curr = q.poll();
			inQueue[curr] = false;
			for (Edge edge : adj.get(curr)) {
				next = edge.to;
				if (distance[curr] + edge.weight < distance[next]) {
					distance[next] = distance[curr] + edge.weight;
					if (!inQueue[next]) {
						q.add(next);
						inQueue[next] = true;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int m, a, b, weight, ans, i;
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
		inQueue = new boolean[n + 1];
		q = new ArrayDeque<>();
		spfa(distanceIn, adjIn);
		spfa(distanceOut, adjOut);
		ans = 0;
		for (i = 1; i <= n; i++) {
			if (distanceIn[i] + distanceOut[i] > ans) {
				ans = distanceIn[i] + distanceOut[i];
			}
		}
		System.out.print(ans);
	}
}
