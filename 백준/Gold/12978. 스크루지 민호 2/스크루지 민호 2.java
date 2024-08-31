import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
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

	private static int dfs(int parent, int curr) {
		int police;
		Edge edge;

		police = 0;
		for (edge = adj[curr]; edge != null; edge = edge.next) {
			if (edge.to == parent) {
				continue;
			}
			police |= dfs(curr, edge.to);
		}
		cnt += police;
		return police ^ 1;
	}

	public static void main(String[] args) throws IOException {
		int n;
		int u;
		int v;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		adj = new Edge[n + 1];
		while (--n > 0) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			adj[u] = new Edge(v, adj[u]);
			adj[v] = new Edge(u, adj[v]);
		}
		cnt = 0;
		dfs(0, 1);
		System.out.print(cnt);
	}
}
