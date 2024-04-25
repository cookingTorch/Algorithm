import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int sum;
	private static int[] costs;
	private static int[] roots;
	private static String IMPOSSIBLE = "Oh no";
	
	private static final int find(int v) {
		if (roots[v] <= 0) {
			return v;
		}
		return roots[v] = find(roots[v]);
	}
	
	private static final void union(int u, int v) {
		int ru;
		int rv;
		
		ru = find(u);
		rv = find(v);
		if (ru == rv) {
			return;
		}
		if (roots[ru] > roots[rv]) {
			roots[ru] = rv;
			if (costs[ru] < costs[rv]) {
				sum -= costs[rv];
				costs[rv] = costs[ru];
			} else {
				sum -= costs[ru];
			}
		} else {
			if (roots[ru] == roots[rv]) {
				roots[ru]--;
			}
			roots[rv] = ru;
			if (costs[ru] > costs[rv]) {
				sum -= costs[ru];
				costs[ru] = costs[rv];
			} else {
				sum -= costs[rv];
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n;
		int m;
		int k;
		int i;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		costs = new int[n + 1];
		sum = 0;
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			costs[i] = Integer.parseInt(st.nextToken());
			sum += costs[i];
		}
		roots = new int[n + 1];
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		if (k < sum) {
			System.out.print(IMPOSSIBLE);
			return;
		}
		System.out.print(sum);
	}
}
