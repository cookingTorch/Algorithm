import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	private static final int MAX_N = 1_000;
	private static final char SPACE = ' ';
	private static final char LINE_BREAK = '\n';
	private static final Edge NIL = new Edge(Integer.MAX_VALUE, null);

	private static final class Edge {
		int to;
		Edge next;

		Edge(int to, Edge next) {
			this.to = to;
			this.next = next;
		}
	}

	private static boolean[] visited;
	private static Edge[] adj;
	private static StringBuilder sb;

	private static final void addEdge(int u, int v) {
		Edge edge;

		for (edge = adj[u]; edge.next.to < v; edge = edge.next);
		edge.next = new Edge(v, edge.next);
	}

	private static final void dfs(int curr) {
		int to;
		Edge edge;

		for (edge = adj[curr].next; edge != NIL; edge = edge.next) {
			if (!visited[to = edge.to]) {
				sb.append(to).append(SPACE);
				visited[to] = true;
				dfs(to);
			}
		}
	}

	private static final void bfs(int start) {
		int to;
		int curr;
		Edge edge;
		ArrayDeque<Integer> q;

		q = new ArrayDeque<>(MAX_N);
		sb.append(start).append(SPACE);
		visited[start] = true;
		q.addLast(start);
		while (!q.isEmpty()) {
			curr = q.pollFirst();
			for (edge = adj[curr].next; edge != NIL; edge = edge.next) {
				if (!visited[to = edge.to]) {
					sb.append(to).append(SPACE);
					visited[to] = true;
					q.addLast(to);
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
		adj = new Edge[n + 1];
		for (i = 1; i <= n; i++) {
			adj[i] = new Edge(0, NIL);
		}
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine(), " ", false);
			v1 = Integer.parseInt(st.nextToken());
			v2 = Integer.parseInt(st.nextToken());
			addEdge(v1, v2);
			addEdge(v2, v1);
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
