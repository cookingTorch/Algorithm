import java.util.PriorityQueue;

class Solution {
	private static final int NIL = 0;

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
			return this.weight - o.weight;
		}
	}

	private int[] prim(int n, int[] gates, boolean[] isSummit, Edge[] adj) {
		int ans;
		int node;
		int summit;
		int intensity;
		boolean[] visited;
		Edge edge;
		PriorityQueue<Edge> pq;

		pq = new PriorityQueue<>();
		visited = new boolean[n + 1];
		intensity = 0;
		summit = n + 1;
		ans = NIL;
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
			if (edge.weight != intensity) {
				if (ans != NIL) {
					break;
				}
				intensity = edge.weight;
			}
			if (isSummit[node]) {
				if (ans == NIL) {
					ans = intensity;
				}
				if (node < summit) {
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
		return new int[] {summit, ans};
	}

	public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
		int u;
		int v;
		int w;
		Edge[] adj;
		boolean[] isSummit;

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
