import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static class Edge implements Comparable<Edge> {
		int u, v;
		double weight;
		
		Edge(int u, int v, double weight) {
			this.u = u;
			this.v = v;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
	}
	
	private static int[] root;
	
	private static double dist(double[] u, double[] v) {
		return Math.sqrt((u[0] - v[0]) * (u[0] - v[0]) + (u[1] - v[1]) * (u[1] - v[1]));
	}
	
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
		
		int n, m, u, v, size, i;
		double ans;
		double[][] gods;
		Edge edge;
		PriorityQueue<Edge> pq;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		gods = new double[n + 1][2];
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			gods[i][0] = Double.parseDouble(st.nextToken());
			gods[i][1] = Double.parseDouble(st.nextToken());
		}
		size = n - 1;
		root = new int[n + 1];
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			if (union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))) {
				size--;
			}
		}
		pq = new PriorityQueue<>();
		for (u = 1; u <= n; u++) {
			for (v = u + 1; v <= n; v++) {
				pq.add(new Edge(u, v, dist(gods[u], gods[v])));
			}
		}
		ans = 0;
		for (i = 0; i < size;) {
			edge = pq.poll();
			if (union(edge.u, edge.v)) {
				ans += edge.weight;
				i++;
			}
		}
        System.out.print(String.format("%.2f", ans));
	}
}
