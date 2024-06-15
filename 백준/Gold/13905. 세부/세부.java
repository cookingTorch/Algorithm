import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static final class Edge implements Comparable<Edge> {
		int u;
		int v;
		int weight;
		
		public Edge(int u, int v, int weight) {
			this.u = u;
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(o.weight, this.weight);
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
		int m;
		int i;
		int s;
		int e;
		int min;
		Edge edge;
		BufferedReader br;
		StringTokenizer st;
		PriorityQueue<Edge> pq;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		pq = new PriorityQueue<>();
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			pq.offer(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		roots = new int[n + 1];
		while (!pq.isEmpty()) {
			edge = pq.poll();
			if (union(edge.u, edge.v)) {
				min = edge.weight;
				if (find(s) == find(e)) {
					System.out.print(min);
					return;
				}
			}
		}
		System.out.print("0");
	}
}
