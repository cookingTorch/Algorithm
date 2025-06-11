import java.util.PriorityQueue;

class Solution {
	private static final class Edge implements Comparable<Edge> {
		int to;
		int summit;
		int intensity;
		Edge next;

		Edge(int to) {
			this.to = to;
			this.summit = to;
		}

		Edge(int to, int intensity, Edge next) {
			this.to = to;
			this.intensity = intensity;
			this.next = next;
		}

		@Override
		public int compareTo(Edge o) {
			if (this.intensity == o.intensity) {
				return this.summit - o.summit;
			}
			return this.intensity - o.intensity;
		}
	}

	private int[] prim(int n, int[] summits, boolean[] isGate, Edge[] adj) {
		int node;
		int summit;
		int intensity;
		boolean[] visited;
		Edge edge;
		PriorityQueue<Edge> pq;

		pq = new PriorityQueue<>();
		visited = new boolean[n + 1];
		for (int start : summits) {
			pq.offer(new Edge(start));
		}
		summit = 0;
		intensity = 0;
		while (pq.size() != 0) {
			edge = pq.poll();
			node = edge.to;
			if (visited[node]) {
				continue;
			}
			visited[node] = true;
			summit = edge.summit;
			intensity = edge.intensity;
			if (isGate[node]) {
				break;
			}
			for (edge = adj[node]; edge != null; edge = edge.next) {
				if (visited[edge.to]) {
					continue;
				}
				edge.intensity = Math.max(edge.intensity, intensity);
				edge.summit = summit;
				pq.offer(edge);
			}
		}
		return new int[] {summit, intensity};
	}

	public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
		int u;
		int v;
		int w;
		boolean[] isGate;
		Edge[] adj;

		adj = new Edge[n + 1];
		for (int[] path : paths) {
			u = path[0];
			v = path[1];
			w = path[2];
			adj[u] = new Edge(v, w, adj[u]);
			adj[v] = new Edge(u, w, adj[v]);
		}
		isGate = new boolean[n + 1];
		for (int gate : gates) {
			isGate[gate] = true;
		}
		return prim(n, summits, isGate, adj);
	}
}
