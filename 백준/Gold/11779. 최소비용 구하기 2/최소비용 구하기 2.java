import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	
	private static final class Edge {
		int idx, weight;
		Edge next;
		
		Edge(int idx, int weight, Edge next) {
			this.idx = idx;
			this.weight = weight;
			this.next = next;
		}
	}
	
	private static int n;
	private static int[] prev;
	private static Edge[] adj;
	
	private static final int dijkstra(int start, int end) {
		int curr, i;
		int[] distance;
		Edge edge;
		PriorityQueue<Integer> pq;
		
		distance = new int[n + 1];
		for (i = 1; i <= n; i++) {
			distance[i] = INF;
		}
		distance[start] = 0;
		pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(distance[o1], distance[o2]);
			}
		});
		pq.add(start);
		for (;;) {
			curr = pq.poll();
			if (curr == end) {
				return distance[end];
			}
			for (edge = adj[curr]; edge != null; edge = edge.next) {
				if (distance[curr] + edge.weight < distance[edge.idx]) {
					distance[edge.idx] = distance[curr] + edge.weight;
					prev[edge.idx] = curr;
					pq.add(edge.idx);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int m, a, b, weight, start, end, i;
		ArrayDeque<Integer> stack;
		
		n = Integer.parseInt(br.readLine());
		adj = new Edge[n + 1];
		m = Integer.parseInt(br.readLine());
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			adj[a] = new Edge(b, weight, adj[a]);
		}
		prev = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		sb.append(dijkstra(start, end)).append('\n');
		stack = new ArrayDeque<>();
		for (i = end; i != start; i = prev[i]) {
			stack.addFirst(i);
		}
		stack.addFirst(start);
		sb.append(stack.size()).append('\n');
		while (!stack.isEmpty()) {
			sb.append(stack.pollFirst()).append(' ');
		}
		System.out.print(sb);
	}
}
