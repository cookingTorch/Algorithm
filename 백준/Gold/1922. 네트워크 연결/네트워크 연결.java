import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static class Edge implements Comparable<Edge> {
		int u, v, weight;
		
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
	
	private static int[] root;
	
	private static boolean union(int u, int v) {
		int ru, rv;
		
		if ((ru = find(u)) == (rv = find(v))) {
			return false;
		}
		if (root[ru] > root[rv]) {
			root[ru] = rv;
		} else {
			if (root[ru] == root[rv]) {
				root[ru]--;
			}
			root[rv] = ru;
		}
		return true;
	}
	
	private static int find(int u) {
		if (root[u] <= 0) {
			return u;
		}
		return root[u] = find(root[u]);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m, ans, i;
		Edge edge;
		PriorityQueue<Edge> pq;
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<>();
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			pq.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		root = new int[n + 1];
		ans = 0;
		for (i = 0; i < n - 1;) {
			edge = pq.poll();
			if (union(edge.u, edge.v)) {
				ans += edge.weight;
				i++;
			}
		}
		System.out.print(ans);
	}
}
