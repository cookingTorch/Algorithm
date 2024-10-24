import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int CONNECTED = '1';
	private static final char[] YES = {'Y', 'E', 'S'};
	private static final char[] NO = {'N', 'O'};

	private static int[] roots;

	private static final int find(int v) {
		if (roots[v] <= 0) {
			return v;
		}
		return roots[v] = find(roots[v]);
	}

	private static final void union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u == v) {
			return;
		}
		if (roots[u] < roots[v]) {
			roots[u] = v;
		} else {
			if (roots[u] == roots[v]) {
				roots[u]--;
			}
			roots[v] = u;
		}
	}

	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int i;
		int j;
		int curr;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		roots = new int[n + 1];
		for (i = 1; i <= n; i++) {
			for (j = 1; j <= n; j++) {
				if (br.read() == CONNECTED) {
					union(i, j);
				}
				br.read();
			}
		}
		st = new StringTokenizer(br.readLine(), " ", false);
		curr = Integer.parseInt(st.nextToken());
		while (--m > 0) {
			if (find(curr) != find(curr = Integer.parseInt(st.nextToken()))) {
				System.out.print(NO);
				return;
			}
		}
		System.out.print(YES);
	}
}
