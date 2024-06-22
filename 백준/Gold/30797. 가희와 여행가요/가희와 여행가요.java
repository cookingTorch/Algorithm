import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static final class Edge implements Comparable<Edge> {
		int u;
		int v;
		int time;
		long cost;
		
		Edge(int u, int v, long cost, int time) {
			this.u = u;
			this.v = v;
			this.cost = cost;
			this.time = time;
		}
		
		@Override
		public int compareTo(Edge o) {
			if (this.cost == o.cost) {
				return Integer.compare(this.time, o.time);
			}
			return Long.compare(this.cost, o.cost);
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
		int q;
		int i;
		int ans;
		long sum;
		Edge edge;
		BufferedReader br;
		StringTokenizer st;
		PriorityQueue<Edge> pq;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		pq = new PriorityQueue<>();
		for (i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			pq.offer(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Long.parseLong(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		ans = 0;
		sum = 0L;
		roots = new int[n + 1];
		for (i = 0; !pq.isEmpty() && i < n - 1;) {
			edge = pq.poll();
			if (union(edge.u, edge.v)) {
				ans = Math.max(ans, edge.time);
				sum += edge.cost;
				i++;
			}
		}
		if (i == n - 1) {
			System.out.print(new StringBuilder().append(ans).append(' ').append(sum));
			return;
		}
		System.out.print("-1");
	}
}
