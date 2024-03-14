import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final char CONNECTED = '1';
	private static final String YES = "YES";
	private static final String NO = "NO";
	
	private static int[] roots;
	
	private static int find(int v) {
		if (roots[v] <= 0) {
			return v;
		}
		return roots[v] = find(roots[v]);
	}
	
	private static void union(int u, int v) {
		int ru, rv;
		
		if ((ru = find(u)) == (rv = find(v))) {
			return;
		}
		if (roots[ru] < roots[rv]) {
			roots[rv] = ru;
		} else {
			if (roots[ru] == roots[rv]) {
				roots[rv]--;
			}
			roots[ru] = rv;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m, now, prev, i, j;
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		roots = new int[n + 1];
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 1; j <= n; j++) {
				if (st.nextToken().charAt(0) == CONNECTED && j > i) {
					union(i, j);
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		prev = Integer.parseInt(st.nextToken());
		for (i = 1; i < m; i++) {
			now = Integer.parseInt(st.nextToken());
			if (find(now) != find(prev)) {
				System.out.print(NO);
				return;
			}
			prev = now;
		}
		System.out.print(YES);
	}
}
