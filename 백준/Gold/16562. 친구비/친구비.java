import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[] costs, roots;
	private static String IMPOSSIBLE = "Oh no";
	
	private static final int find(int v) {
		if (roots[v] <= 0) {
			return v;
		}
		return roots[v] = find(roots[v]);
	}
	
	private static final void union(int u, int v) {
		int ru, rv;
		
		ru = find(u);
		rv = find(v);
		if (ru == rv) {
			return;
		}
		if (roots[ru] > roots[rv]) {
			roots[ru] = rv;
			costs[rv] = Math.min(costs[ru], costs[rv]);
			costs[ru] = 0;
		} else {
			if (roots[ru] == roots[rv]) {
				roots[ru]--;
			}
			roots[rv] = ru;
			costs[ru] = Math.min(costs[ru], costs[rv]);
			costs[rv] = 0;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m, k, sum, i;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		costs = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			costs[i] = Integer.parseInt(st.nextToken());
		}
		roots = new int[n + 1];
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		sum = 0;
		for (i = 1; i <= n; i++) {
			sum += costs[i];
		}
		if (k < sum) {
			System.out.print(IMPOSSIBLE);
			return;
		}
		System.out.print(sum);
	}
}
