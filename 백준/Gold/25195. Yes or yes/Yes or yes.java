import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int NIL = 0;
	private static final char[] YES = {'Y', 'e', 's'};
	private static final char[] NO = {'y', 'e', 's'};

	private static int[] to;
	private static int[] adj;
	private static int[] next;
	private static boolean[] fan;
	private static boolean[] visited;

	private static final boolean dfs(int curr) {
		int edge;

		if (fan[curr]) {
			return true;
		}
		if (adj[curr] == NIL) {
			return false;
		}
		for (edge = adj[curr]; edge != NIL; edge = next[edge]) {
			if (visited[to[edge]]) {
				continue;
			}
			visited[to[edge]] = true;
			if (!dfs(to[edge])) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int s;
		int u;
		int v;
		int edge;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ", false);
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		edge = NIL;
		to = new int[m + 1];
		next = new int[m + 1];
		adj = new int[n + 1];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			to[++edge] = v;
			next[edge] = adj[u];
			adj[u] = edge;
		}
		fan = new boolean[n + 1];
		s = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ", false);
		while (s-- > 0) {
			fan[Integer.parseInt(st.nextToken())] = true;
		}
		visited = new boolean[n + 1];
		if (dfs(1)) {
			System.out.print(YES);
		} else {
			System.out.print(NO);
		}
	}
}
