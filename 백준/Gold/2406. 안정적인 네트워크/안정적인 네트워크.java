import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static final char SPACE = ' ';
	private static final char LINE_BREAK = '\n';
	
	private static final class Edge implements Comparable<Edge> {
		int u;
		int v;
		int weight;
		
		Edge(int u, int v, int weight) {
			this.u = u;
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	private static int[] roots;
	
	private static int find(int v) {
		if (roots[v] <= 0) {
			return v;
		}
		return roots[v] = find(roots[v]);
	}
	
	private static final boolean union(int u, int v) {
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
	
	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int i;
		int j;
		int thr;
		int ans;
		Edge edge;
		Edge[] edges;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;
		PriorityQueue<Edge> pq;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		thr = n - 2;
		roots = new int[n + 1];
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			if (union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))) {
				thr--;
			}
		}
		pq = new PriorityQueue<>();
		br.readLine();
		for (i = 2; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 1; j <= i; j++) {
				st.nextToken();
			}
			for (; j <= n; j++) {
				pq.offer(new Edge(i, j, Integer.parseInt(st.nextToken())));
			}
		}
		ans = 0;
		edges = new Edge[thr];
		for (i = 0; i < thr;) {
			edge = pq.poll();
			if (union(edge.u, edge.v)) {
				ans += edge.weight;
				edges[i++] = edge;
			}
		}
		sb = new StringBuilder();
		sb.append(ans).append(SPACE).append(thr).append(LINE_BREAK);
		for (Edge connection : edges) {
			sb.append(connection.u).append(SPACE).append(connection.v).append(LINE_BREAK);
		}
		System.out.print(sb);
	}
}
