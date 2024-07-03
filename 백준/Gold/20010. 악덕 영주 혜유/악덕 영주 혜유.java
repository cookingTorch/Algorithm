import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static final char LINE_BREAK = '\n';

	private static final class Edge implements Comparable<Edge> {
		int u;
		int v;
		int weight;
		Edge next;

		Edge(int u, int v, int weight) {
			this.u = u;
			this.v = v;
			this.weight = weight;
		}

		Edge(int v, int weight, Edge next) {
			this.v = v;
			this.weight = weight;
			this.next = next;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	private static int max;
	private static int[] roots;
	private static Edge[] adj;

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
			if (roots[rv] == roots[ru]) {
				roots[ru]--;
			}
			roots[rv] = ru;
		}
		return true;
	}

	private static int dfs(int curr, int parent) {
		int max1;
		int max2;
		int dist;
		Edge edge;

		max1 = 0;
		max2 = 0;
		for (edge = adj[curr]; edge != null; edge = edge.next) {
			if (edge.v == parent) {
				continue;
			}
			dist = dfs(edge.v, curr) + edge.weight;
			if (dist > max1) {
				max2 = max1;
				max1 = dist;
			} else if (dist > max2) {
				max2 = dist;
			}
		}
		max = Math.max(max, max1 + max2);
		return max1;
	}

	public static void main(String[] args) throws IOException {
		int n;
		int k;
		int i;
		int ans;
		Edge edge;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;
		PriorityQueue<Edge> pq;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		pq = new PriorityQueue<>();
		for (i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			pq.offer(new Edge(Integer.parseInt(st.nextToken()) + 1, Integer.parseInt(st.nextToken()) + 1, Integer.parseInt(st.nextToken())));
		}
		ans = 0;
		roots = new int[n + 1];
		adj = new Edge[n + 1];
		for (i = 1; i < n;) {
			edge = pq.poll();
			if (union(edge.u, edge.v)) {
				ans += edge.weight;
				adj[edge.u] = new Edge(edge.v, edge.weight, adj[edge.u]);
				adj[edge.v] = new Edge(edge.u, edge.weight, adj[edge.v]);
				i++;
			}
		}
		max = 0;
		dfs(1, 0);
		System.out.print(new StringBuilder().append(ans).append(LINE_BREAK).append(max));
	}
}