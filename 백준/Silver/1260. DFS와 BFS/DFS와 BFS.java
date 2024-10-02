import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	private static final int MAX_N = 1_000;
	private static final char SPACE = ' ';
	private static final char LINE_BREAK = '\n';

	private static StringBuilder sb;

	private static boolean[] visited;
	private static ArrayList<Integer>[] adj;

	private static final void dfs(int curr) {
		for (int next : adj[curr]) {
			if (!visited[next]) {
				sb.append(next).append(SPACE);
				visited[next] = true;
				dfs(next);
			}
		}
	}

	private static final void bfs(int start) {
		int curr;
		ArrayDeque<Integer> q;

		q = new ArrayDeque<>(MAX_N);
		sb.append(start).append(SPACE);
		visited[start] = true;
		q.addLast(start);
		while (!q.isEmpty()) {
			curr = q.pollFirst();
			for (int next : adj[curr]) {
				if (!visited[next]) {
					sb.append(next).append(SPACE);
					visited[next] = true;
					q.addLast(next);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int v;
		int v1;
		int v2;
		int i;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ", false);
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());
		adj = new ArrayList[n + 1];
		for (i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine(), " ", false);
			v1 = Integer.parseInt(st.nextToken());
			v2 = Integer.parseInt(st.nextToken());
			adj[v1].add(v2);
			adj[v2].add(v1);
		}
		for (i = 1; i <= n; i++) {
			Collections.sort(adj[i]);
		}
		sb = new StringBuilder();
		visited = new boolean[n + 1];
		sb.append(v).append(SPACE);
		visited[v] = true;
		dfs(v);
		sb.append(LINE_BREAK);
		visited = new boolean[n + 1];
		bfs(v);
		System.out.print(sb.toString());
	}
}
