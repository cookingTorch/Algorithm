import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	private static final int DIFF1 = 'A' - 27;
	private static final int DIFF2 = 'a' - 1;
	private static final char BOUND = 'Z' + 1;
	private static final char NONE = '0';
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
		char[] str;
		int[][] cables;
		Edge edge;
		PriorityQueue<Edge> pq;
		
		n = Integer.parseInt(br.readLine());
		cables = new int[n][n];
		for (i = 0; i < n; i++) {
			str = br.readLine().toCharArray();
			for (j = 0; j < n; j++) {
				if (str[j] != NONE) {
					if (str[j] < BOUND) {
						cables[i][j] = str[j] - DIFF1;
					} else {
						cables[i][j] = str[j] - DIFF2;
					}
				}
			}
		}
		sum = 0;
		pq = new PriorityQueue<>();
		for (i = 0; i < n; i++) {
			sum += cables[i][i];
			for (j = i + 1; j < n; j++) {
				sum += cables[i][j] + cables[j][i];
				if (cables[i][j] != 0 && (cables[j][i] == 0 || cables[i][j] < cables[j][i])) {
					pq.add(new Edge(i + 1, j + 1, cables[i][j]));
				} else if (cables[j][i] != 0) {
					pq.add(new Edge(j + 1, i + 1, cables[j][i]));
				}
			}
		}
		roots = new int[n + 1];
		for (i = 0; i < n - 1;) {
			edge = pq.poll();
            if (edge == null) {
                System.out.print(IMPOSSIBLE);
			    return;
            }
			if (union(edge.from, edge.to)) {
				i++;
				sum -= edge.weight;
			}
		}
		System.out.print(sum);
	}
}
