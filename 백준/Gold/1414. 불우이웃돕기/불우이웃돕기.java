import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	private static final int DIFF1 = 38;
	private static final int DIFF2 = 96;
	private static final int BOUND = 91;
	private static final int NONE = 48;
	private static final String IMPOSSIBLE = "-1";
	
	private static final class Edge implements Comparable<Edge> {
		int from, to, weight;
		
		Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
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
	
	private static boolean union(int u, int v) {
		int ru, rv;
		
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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n, sum, i, j;
		String str;
		Edge edge;
		PriorityQueue<Edge> pq;
		
		n = Integer.parseInt(br.readLine());
		sum = 0;
		pq = new PriorityQueue<>();
		for (i = 1; i <= n; i++) {
			str = br.readLine();
			for (j = 1; j <= n; j++) {
				if (str.charAt(j - 1) != NONE) {
					if (str.charAt(j - 1) < BOUND) {
						pq.add(new Edge(i, j, str.charAt(j - 1) - DIFF1));
						sum += str.charAt(j - 1) - DIFF1;
					} else if (str.charAt(j - 1) != NONE) {
						pq.add(new Edge(i, j, str.charAt(j - 1) - DIFF2));
						sum += str.charAt(j - 1) - DIFF2;
					}
				}
			}
		}
		roots = new int[n + 1];
		for (i = 0; !pq.isEmpty() && i < n - 1;) {
			edge = pq.poll();
			if (union(edge.from, edge.to)) {
				i++;
				sum -= edge.weight;
			}
		}
		if (i < n - 1) {
			System.out.print(IMPOSSIBLE);
			return;
		}
		System.out.print(sum);
	}
}
