import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final char CONNECTED = '1';
	private static final String YES = "YES";
	private static final String NO = "NO";
	
	private static int[] roots;
	
	private static final int find(int v) {
		if (roots[v] <= 0) {
			return v;
		}
		return roots[v] = find(roots[v]);
	}
	
	private static final void union(int u, int v) {
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
		int n;
		int m;
		int i;
		int j;
		int start;
		String str;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		roots = new int[n + 1];
		for (i = 1; i <= n; i++) {
			str = br.readLine();
			for (j = i; j < n;) {
				if (str.charAt(j++ << 1) == CONNECTED) {
					union(i, j);
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		start = find(Integer.parseInt(st.nextToken()));
		while (--m > 0) {
			if (start != find(Integer.parseInt(st.nextToken()))) {
				System.out.print(NO);
				return;
			}
		}
		System.out.print(YES);
	}
}
