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
	
	private static final int find(int v) {
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
		int i;
		int j;
		int thr;
		int ans;
		int cost;
		Edge edge;
		Edge[] edges;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;
		PriorityQueue<Edge> pq;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		thr = n - 1;
		ans = 0;
		roots = new int[n + 1];
		pq = new PriorityQueue<>();
		for (i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 1; j <= i; j++) {
				st.nextToken();
			}
			for (; j <= n; j++) {
				cost = Integer.parseInt(st.nextToken());
				if (cost < 0) {
					if (union(i, j)) {
						thr--;
					}
					ans -= cost;
				} else {
					pq.offer(new Edge(i, j, cost));
				}
			}
		}
		edges = new Edge[thr];
		for (i = 0; i < thr;) {
			edge = pq.poll();
			if (union(edge.u, edge.v)) {
				ans += edge.weight;
				edges[i++] = edge;
			}
		}
		sb = new StringBuilder().append(ans).append(SPACE).append(thr);
		for (Edge added : edges) {
			sb.append(LINE_BREAK).append(added.u).append(SPACE).append(added.v);
		}
		System.out.print(sb);
	}
}
