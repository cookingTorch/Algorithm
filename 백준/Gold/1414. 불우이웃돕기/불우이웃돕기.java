import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	private static final int a = 'a';
	private static final int z = 'z';
	private static final int A = 'A';
	private static final int Z = 'Z';
	private static final int NUM = 27;
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
		int[] weights;
		char[] str;
		Edge edge;
		PriorityQueue<Edge> pq;
		
		weights = new int[z + 1];
		for (i = a; i <= z; i++) {
			weights[i] = i - a + 1;
		}
		for (i = A; i <= Z; i++) {
			weights[i] = i - A + NUM;
		}
		n = Integer.parseInt(br.readLine());
		sum = 0;
		pq = new PriorityQueue<>();
		for (i = 0; i < n; i++) {
			str = br.readLine().toCharArray();
			for (j = 0; j < n; j++) {
				sum += weights[str[j]];
				if (weights[str[j]] != 0) {
					pq.offer(new Edge(i + 1, j + 1, weights[str[j]]));
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
