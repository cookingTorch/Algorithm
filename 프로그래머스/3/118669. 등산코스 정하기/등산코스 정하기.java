import java.util.PriorityQueue;

class Solution {
	private static final int INF = Integer.MAX_VALUE >>> 1;

	private static final class Edge implements Comparable<Edge> {
		int to;
		int weight;
		Edge next;

		Edge(int to, int weight, Edge next) {
			this.to = to;
			this.weight = weight;
			this.next = next;
		}

		@Override
		public int compareTo(Edge o) {
			return weight - o.weight;
		}
	}

	private int[] prim(int n, int[] gates, boolean[] isSummit, Edge[] adj) {
		int min;
		int node;
		int summit;
		int intensity;
		boolean[] visited;
		Edge edge;
		PriorityQueue<Edge> pq;

		pq = new PriorityQueue<>();
		visited = new boolean[n + 1];
		summit = 0;
		min = INF;
		for (int gate : gates) {
			pq.offer(new Edge(gate, 0, null));
		}
		while (pq.size() != 0) {
			edge = pq.poll();
			node = edge.to;
			if (visited[node]) {
				continue;
			}
			visited[node] = true;
			intensity = edge.weight;
			if (isSummit[node]) {
				if (intensity < min) {
					summit = node;
					min = intensity;
				} else if (intensity == min && node < summit) {
					summit = node;
				}
				continue;
			}
			for (edge = adj[node]; edge != null; edge = edge.next) {
				if (visited[edge.to]) {
					continue;
				}
				edge.weight = Math.max(edge.weight, intensity);
				pq.offer(edge);
			}
		}
		return new int[] {summit, min};
	}

	public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
		int u;
		int v;
		int w;
		boolean[] isSummit;
		Edge[] adj;

		adj = new Edge[n + 1];
		for (int[] path : paths) {
			u = path[0];
			v = path[1];
			w = path[2];
			adj[u] = new Edge(v, w, adj[u]);
			adj[v] = new Edge(u, w, adj[v]);
		}
		isSummit = new boolean[n + 1];
		for (int summit : summits) {
			isSummit[summit] = true;
		}
		return prim(n, gates, isSummit, adj);
	}
}
