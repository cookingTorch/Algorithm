import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int u;
		int v;
		int k;
		int mid;
		int ans;
		int lighterCnt;
		int heavierCnt;
		boolean[][] lighter;
		boolean[][] heavier;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		heavier = new boolean[n + 1][n + 1];
		lighter = new boolean[n + 1][n + 1];
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			lighter[u][v] = true;
			heavier[v][u] = true;
		}
		for (k = 1; k <= n; k++) {
			for (u = 1; u <= n; u++) {
				for (v = 1; v <= n; v++) {
					if (lighter[u][k] && lighter[k][v]) {
						lighter[u][v] = true;
					}
					if (heavier[u][k] && heavier[k][v]) {
						heavier[u][v] = true;
					}
				}
			}
		}
		ans = 0;
		mid = n >> 1;
		loop:
		for (u = 1; u <= n; u++) {
			lighterCnt = 0;
			heavierCnt = 0;
			for (v = 1; v <= n; v++) {
				if (lighter[u][v] && ++lighterCnt > mid) {
					ans++;
					continue loop;
				}
				if (heavier[u][v] && ++heavierCnt > mid) {
					ans++;
					continue loop;
				}
			}
		}
		System.out.print(ans);
	}
}
