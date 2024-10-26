import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final char[] YES = {'Y', 'e', 's'};
	private static final char[] NO = {'y', 'e', 's'};

	static class Edge {
		int to;
		Edge next;

		Edge(int to, Edge next) {
			this.to = to;
			this.next = next;
		}
	}

	private static boolean[] fan;
	private static boolean[] visited;
	private static Edge[] adj;
	private static int N,M;

	private static final boolean dfs(int curr) {
		Edge edge;

		if (fan[curr]) {
			return true;
		}
		if (adj[curr] == null) {
			return false;
		}
		for (edge = adj[curr]; edge != null; edge = edge.next) {
			if (visited[edge.to]) {
				continue;
			}
			visited[edge.to] = true;
			if (!dfs(edge.to)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adj = new Edge[N+1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			adj[from] = new Edge(to, adj[from]);
		}

		fan = new boolean[N+1];
		int s = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		while (s-- > 0) {
			fan[Integer.parseInt(st.nextToken())] = true;
		}
		visited = new boolean[N+1];
		if (dfs(1)) {
			System.out.print(YES);
		} else {
			System.out.print(NO);
		}
	}
}
