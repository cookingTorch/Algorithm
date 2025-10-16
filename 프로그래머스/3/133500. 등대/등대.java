class Solution {
	private static final class Edge {
		int to;
		Edge next;

		Edge(int to, Edge next) {
			this.to = to;
			this.next = next;
		}
	}

	private static int cnt;
	private static Edge[] adj;

	private static boolean isOn(int parent, int node) {
		boolean on;
		Edge edge;

		on = false;
		for (edge = adj[node]; edge != null; edge = edge.next) {
			if (edge.to != parent) {
				on |= !isOn(node, edge.to);
			}
		}
		if (on) {
			cnt++;
		}
		return on;
	}

	public int solution(int n, int[][] lighthouse) {
		int i;
		int len;
		int[] edge;

		len = lighthouse.length;
		adj = new Edge[n + 1];
		for (i = 0; i < len; i++) {
			edge = lighthouse[i];
			adj[edge[0]] = new Edge(edge[1], adj[edge[0]]);
			adj[edge[1]] = new Edge(edge[0], adj[edge[1]]);
		}
		cnt = 0;
		isOn(0, 1);
		return cnt;
	}
}
