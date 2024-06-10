import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static final class Edge implements Comparable<Edge> {
		int to;
		int from;
		long weight;
		
		Edge(int from, int to, long weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.weight, o.weight);
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
		long ans;
		Edge edge;
		BufferedReader br;
		StringTokenizer st;
		PriorityQueue<Edge> pq;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<>();
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 1; j <= i; j++) {
				st.nextToken();
			}
			for (; j <= n; j++) {
				pq.offer(new Edge(i, j, Long.parseLong(st.nextToken())));
			}
		}
		roots = new int[n + 1];
		ans = 0;
		for (i = 0; i < n - 1;) {
			edge = pq.poll();
			if (union(edge.from, edge.to)) {
				ans += edge.weight;
				i++;
			}
		}
		System.out.print(ans);
	}
}
