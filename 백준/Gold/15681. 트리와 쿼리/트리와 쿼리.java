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
	
	private static int[] subSize;
	private static Edge[] adj;
	
	private static final int dfs(int curr, int parent) {
		Edge edge;
		
		subSize[curr] = 1;
		for (edge = adj[curr]; edge != null; edge = edge.next) {
			if (edge.to == parent) {
				continue;
			}
			subSize[curr] += dfs(edge.to, curr);
		}
		return subSize[curr];
	}
	
	public static void main(String[] args) throws IOException {
		int n;
		int r;
		int q;
		int u;
		int v;
		int i;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		adj = new Edge[n + 1];
		for (i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			adj[u] = new Edge(v, adj[u]);
			adj[v] = new Edge(u, adj[v]);
		}
		subSize = new int[n + 1];
		dfs(r, -1);
		sb = new StringBuilder();
		for (i = 0; i < q; i++) {
			sb.append(subSize[Integer.parseInt(br.readLine())]).append('\n');
		}
		System.out.print(sb);
	}
}
