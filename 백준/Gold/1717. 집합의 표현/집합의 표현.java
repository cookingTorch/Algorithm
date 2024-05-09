import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final char UNION = '0';
	private static final String YES = "YES\n";
	private static final String NO = "NO\n";
	
	private static int[] roots;
	
	private static final int find(int v) {
		if (roots[v] <= 0) {
			return v;
		}
		return roots[v] = find(roots[v]);
	}
	
	private static final void union(int u, int v) {
		int ru;
		int rv;
		
		if ((ru = find(u)) == (rv = find(v))) {
			return;
		}
		if (roots[ru] > roots[rv]) {
			roots[ru] = rv;
		} else {
			if (roots[ru] == roots[rv]) {
				roots[ru]--;
			}
			roots[rv] = ru;
		}
	}
	
	private static final boolean check(int u, int v) {
		return find(u) == find(v);
	}
	
	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int i;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		roots = new int[n + 2];
		sb = new StringBuilder();
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			if (st.nextToken().charAt(0) == UNION) {
				union(Integer.parseInt(st.nextToken()) + 1, Integer.parseInt(st.nextToken()) + 1);
			} else {
				sb.append(check(Integer.parseInt(st.nextToken()) + 1, Integer.parseInt(st.nextToken()) + 1) ? YES : NO);
			}
		}
		System.out.print(sb);
	}
}
