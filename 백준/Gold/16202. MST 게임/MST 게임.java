import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final char ZERO = '0';
	private static final char SPACE = ' ';
	
	private static final class Edge {
		int u;
		int v;
		int weight;
		
		Edge(int u, int v, int weight) {
			this.u = u;
			this.v = v;
			this.weight = weight;
		}
	}
	
	private static int[] roots;
	private static int[] inital_roots;
	
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
		int k;
		int i;
		int j;
		int idx;
		int ans;
		Edge edge;
		Edge[] edges;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		edges = new Edge[m];
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			edges[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i + 1);
		}
		inital_roots = new int[n + 1];
		roots = new int[n + 1];
		sb = new StringBuilder();
		for (i = 0; i < k; i++) {
			ans = 0;
			System.arraycopy(inital_roots, 0, roots, 0, n + 1);
			for (idx = i, j = 0; idx < m && j < n - 1; idx++) {
				edge = edges[idx];
				if (union(edge.u, edge.v)) {
					ans += edge.weight;
					j++;
				}
			}
			if (j == n - 1) {
				sb.append(ans).append(SPACE);
			} else {
				break;
			}
		}
		for (; i < k; i++) {
			sb.append(ZERO).append(SPACE);
		}
		System.out.print(sb);
	}
}
