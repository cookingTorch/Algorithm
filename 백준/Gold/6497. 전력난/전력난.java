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
	
	private static int solution(int m, int n, BufferedReader br, StringTokenizer st) throws IOException {
		int z, ans, i;
		Edge edge;
		PriorityQueue<Edge> pq;
		
		ans = 0;
		pq = new PriorityQueue<>();
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			pq.add(new Edge(Integer.parseInt(st.nextToken()) + 1, Integer.parseInt(st.nextToken()) + 1, z = Integer.parseInt(st.nextToken())));
			ans += z;
		}
		root = new int[m + 1];
		for (i = 0; i < m - 1;) {
			edge = pq.poll();
			if (union(edge.u, edge.v)) {
				ans -= edge.weight;
				i++;
			}
		}
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int m, n;
		
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		while (m != 0) {
			sb.append(solution(m, n, br, st)).append('\n');
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
		}
		System.out.print(sb);
	}
}
