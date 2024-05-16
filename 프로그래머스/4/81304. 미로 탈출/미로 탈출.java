import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
	private static final int INF = Integer.MAX_VALUE >> 1;
	
	private static final class Edge {
		int to;
		int from;
		int weight;
		boolean backward;
		Edge next;
		
		Edge(int from, int to, int weight, Edge next, boolean backward) {
			this.from = from;
			this.to = to;
			this.weight = weight;
			this.next = next;
			this.backward = backward;
		}
		
		final boolean off(int toggle) {
			boolean to;
			boolean from;
			
			from = isTrap[this.from] ? (((toggle >> trapIdx[this.from]) & 1) == 0) : true;
			to = isTrap[this.to] ? (((toggle >> trapIdx[this.to]) & 1) == 0) : true;
			return from ^ to ^ backward;
		}
	}
	
	private static final class Node implements Comparable<Node> {
		int idx;
		int toggle;
		
		Node(int idx, int toggle) {
			this.idx = idx;
			this.toggle = toggle;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(distance[this.idx][this.toggle], distance[o.idx][o.toggle]);
		}
	}
	
	private static int[] trapIdx;
	private static int[][] distance;
	private static boolean[] isTrap;
	private static Edge[] adj;
	
	private static final int dijkstra(int start, int end) {
		int curr;
		int next;
		int weight;
		int toggle;
		int nextToggle;
		Node node;
		Edge edge;
		PriorityQueue<Node> pq;
		
		distance[start][0] = 0;
		pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		while (!pq.isEmpty()) {
			node = pq.poll();
			curr = node.idx;
			toggle = node.toggle;
			if (curr == end) {
				return distance[curr][toggle];
			}
			for (edge = adj[curr]; edge != null; edge = edge.next) {
				if (edge.off(toggle)) {
					continue;
				}
				next = edge.to;
				weight = edge.weight;
				if (isTrap[next]) {
					nextToggle = toggle ^ (1 << trapIdx[next]);
					if (distance[curr][toggle] + weight < distance[next][nextToggle]) {
						distance[next][nextToggle] = distance[curr][toggle] + weight;
						pq.offer(new Node(next, nextToggle));
					}
					continue;
				}
				if (distance[curr][toggle] + weight < distance[next][toggle]) {
					distance[next][toggle] = distance[curr][toggle] + weight;
					pq.offer(new Node(next, toggle));
				}
			}
		}
		return 0;
	}
	
	public int solution(int n, int start, int end, int[][] roads, int[] traps) {
		int i;
		int idx;

		idx = 0;
		trapIdx = new int[n + 1];
		isTrap = new boolean[n + 1];
		for (int trap : traps) {
			trapIdx[trap] = idx++;
			isTrap[trap] = true;
		}
		adj = new Edge[n + 1];
		for (int[] edge : roads) {
			adj[edge[0]] = new Edge(edge[0], edge[1], edge[2], adj[edge[0]], false);
			adj[edge[1]] = new Edge(edge[1], edge[0], edge[2], adj[edge[1]], true);
		}
		distance = new int[n + 1][1 << traps.length];
		for (i = 1; i <= n; i++) {
			Arrays.fill(distance[i], INF);
		}
		return dijkstra(start, end);
	}
}
