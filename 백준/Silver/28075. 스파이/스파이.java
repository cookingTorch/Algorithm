import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int n;
	private static int m;
	private static int cnt;
	private static int[][] arr;

	private static final void dfs(int prev, int sum, int depth) {
		int i;
		int j;

		if (depth == n) {
			if (sum >= m) {
				cnt++;
			}
			return;
		}
		depth++;
		for (i = 0; i < 2; i++) {
			for (j = 0; j < 3; j++) {
				if (prev == j) {
					dfs(j, sum + (arr[i][j] >> 1), depth);
				} else {
					dfs(j, sum + arr[i][j], depth);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int i;
		int j;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ", false);
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		cnt = 0;
		arr = new int[2][3];
		for (i = 0; i < 2; i++) {
			st = new StringTokenizer(br.readLine(), " ", false);
			for (j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(-1, 0, 0);
		System.out.print(cnt);
	}
}
