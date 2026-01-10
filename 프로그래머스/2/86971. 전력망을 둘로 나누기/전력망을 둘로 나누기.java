class Solution {
	private static final class Edge {
		int to;
		Edge next;
		
		Edge(int to, Edge next) {
			this.to = to;
			this.next = next;
		}
	}
	
	private static int num;
	private static int min;
	private static Edge[] adj;
	
	private static int dfs(int parent, int node) {
		int cnt;
		Edge edge;
		
		cnt = 1;
		for (edge = adj[node]; edge != null; edge = edge.next) {
			if (edge.to != parent) {
				cnt += dfs(node, edge.to);
			}
		}
		min = Math.min(min, Math.abs(num - (cnt << 1)));
		return cnt;
	}

	public int solution(int n, int[][] wires) {
		int u;
		int v;
		int i;

		adj = new Edge[n + 1];
		num = n - 1;
		for (i = 0; i < num; i++) {
			u = wires[i][0];
			v = wires[i][1];
			adj[u] = new Edge(v, adj[u]);
			adj[v] = new Edge(u, adj[v]);
		}
		num = n;
		min = n;
		dfs(0, 1);
		return min;
	}
}
