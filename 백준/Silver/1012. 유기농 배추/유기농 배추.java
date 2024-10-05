import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int COL = 52;
	private static final int MAX_K = 2_500;
	private static final int[] d = {-COL, 1, COL, -1};
	private static final char LINE_BREAK = '\n';

	private static int[] positions;
	private static boolean[] map;
	private static BufferedReader br;

	private static final void dfs(int pos) {
		int i;
		int npos;

		map[pos] = false;
		for (i = 0; i < 4; i++) {
			npos = pos + d[i];
			if (map[npos]) {
				dfs(npos);
			}
		}
	}

	private static final int solution() throws IOException {
		int k;
		int i;
		int cnt;
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ", false);
		st.nextToken();
		st.nextToken();
		k = Integer.parseInt(st.nextToken());
		for (i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine(), " ", false);
			map[positions[i] = (Integer.parseInt(st.nextToken()) + 1) * COL + Integer.parseInt(st.nextToken()) + 1] = true;
		}
		cnt = 0;
		while (k-- > 0) {
			if (map[positions[k]]) {
				dfs(positions[k]);
				cnt++;
			}
		}
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		int t;
		StringBuilder sb;

		positions = new int[MAX_K];
		map = new boolean[COL * COL];
		br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		while (t-- > 0) {
			sb.append(solution()).append(LINE_BREAK);
		}
		System.out.print(sb.toString());
	}
}
