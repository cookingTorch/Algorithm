import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static final class Edge implements Comparable<Edge> {
		int u;
		int v;
		long weight;

		Edge(int u, int v, long weight) {
			this.u = u;
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(weight, o.weight);
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
		long max;
		Edge edge;
		Point[] cows;
		BufferedReader br;
		StringTokenizer st;
		PriorityQueue<Edge> pq;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		cows = new Point[n + 1];
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			cows[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		pq = new PriorityQueue<>();
		for (i = 1; i <= n; i++) {
			for (j = i + 1; j <= n; j++) {
				pq.offer(new Edge(i, j, (long) cows[i].distanceSq(cows[j])));
			}
		}
		max = 0L;
		roots = new int[n + 1];
		for (i = 1; i < n;) {
			edge = pq.poll();
			if (union(edge.u, edge.v)) {
				max = Math.max(max, edge.weight);
				i++;
			}
		}
		System.out.print(max);
	}
}
