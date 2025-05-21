import java.util.ArrayDeque;

class Solution {
	private static final class Edge {
		int to;
		Edge next;

		Edge(int to, Edge next) {
			this.to = to;
			this.next = next;
		}
	}

	private int bfs(int n, Edge[] adj) {
		int i;
		int size;
		int node;
		boolean[] visited;
		Edge edge;
		ArrayDeque<Integer> q;

		q = new ArrayDeque<>();
		visited = new boolean[n + 1];
		q.add(1);
		visited[1] = true;
		size = 1;
		while (!q.isEmpty()) {
			size = q.size();
			for (i = 0; i < size; i++) {
				node = q.pollFirst();
				for (edge = adj[node]; edge != null; edge = edge.next) {
					if (!visited[edge.to]) {
						q.addLast(edge.to);
						visited[edge.to] = true;
					}
				}
			}
		}
		return size;
	}

	public int solution(int n, int[][] edge) {
		int u;
		int v;
		int i;
		int len;
		Edge[] adj;

		adj = new Edge[n + 1];
		len = edge.length;
		for (i = 0; i < len; i++) {
			u = edge[i][0];
			v = edge[i][1];
			adj[u] = new Edge(v, adj[u]);
			adj[v] = new Edge(u, adj[v]);
		}
		return bfs(n, adj);
	}
}
