import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int PLACE = 2;
	private static final int WORK = 3;
	private static final int NONE = -1;
	
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
		for (i = 0; i < PLACE; i++) {
			for (j = 0; j < WORK; j++) {
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
		arr = new int[PLACE][WORK];
		for (i = 0; i < PLACE; i++) {
			st = new StringTokenizer(br.readLine(), " ", false);
			for (j = 0; j < WORK; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(NONE, 0, 0);
		System.out.print(cnt);
	}
}
