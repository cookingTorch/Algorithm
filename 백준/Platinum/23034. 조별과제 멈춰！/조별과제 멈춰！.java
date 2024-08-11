import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	private static final int MAX_N = 1001;
	private static final char LINE_BREAK = '\n';

	private static class Line implements Comparable<Line> {
		int u;
		int v;
		int weight;

		public Line(int from, int to, int weight) {
			this.u = from;
			this.v = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Line o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	private static class Edge {
		int to;
		int weight;
		Edge next;

		public Edge(int to, int weight, Edge next) {
			this.to = to;
			this.weight = weight;
			this.next = next;
		}
	}

	private static int n;
	private static int[] roots = new int[MAX_N];
	private static int[] path = new int[MAX_N];
	private static int[] previous = new int[MAX_N];
	private static boolean[] visited = new boolean[MAX_N];
	private static Edge[] adj = new Edge[MAX_N];

	private static int find(int v) {
		if (roots[v] <= 0) {
			return v;
		}
		return roots[v] = find(roots[v]);
	}

	private static boolean union(int u, int v) {
		int ru;
		int rv;

		ru = find(u);
		rv = find(v);
		if (ru == rv) {
			return false;
		}
		if (roots[ru] > roots[rv]) {
			roots[ru] = rv;
		} else {
			if (roots[ru] == roots[rv]) {
				roots[ru]--;
			}
			roots[rv] = ru;
		}
		return true;
	}

	private static void bfs(int x, int y) {
		int i;
		int curr;
		int next;
		ArrayDeque<Integer> q;

		for (i = 1; i <= n; i++) {
			visited[i] = false;
		}
		q = new ArrayDeque<>();
		q.addLast(x);
		visited[x] = true;
		for (;;) {
			curr = q.pollFirst();
			for (Edge edge = adj[curr]; edge != null; edge = edge.next) {
				if (visited[next = edge.to]) {
					continue;
				}
				path[next] = edge.weight;
				previous[next] = curr;
				if (next == y) {
					return;
				}
				visited[next] = true;
				q.addLast(next);
			}
		}
	}

	private static int maxEdgeWeight(int x, int y) {
		int max;

		bfs(x, y);
		max = 0;
		for (int i = y; i != x; i = previous[i]) {
			max = Math.max(max, path[i]);
		}
		return max;
	}

	public static void main(String[] args) throws IOException {
		int m;
		int u;
		int v;
		int q;
		int x;
		int y;
		int i;
		int sum;
		int weight;
		Line line;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;
		PriorityQueue<Line> pq;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		pq = new PriorityQueue<>();
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			pq.offer(new Line(a, b, c));
		}
		sum = 0;
		for (i = 1; i < n;) {
			line = pq.poll();
			if (union(u = line.u, v = line.v)) {
				weight = line.weight;
				adj[u] = new Edge(v, weight, adj[u]);
				adj[v] = new Edge(u, weight, adj[v]);
				sum += weight;
				i++;
			}
		}
		q = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		while (q-- > 0) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			sb.append(sum - maxEdgeWeight(x, y)).append(LINE_BREAK);
		}
		System.out.print(sb);
	}
}
