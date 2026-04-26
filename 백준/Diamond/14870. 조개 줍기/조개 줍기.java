import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	private static final String DELIM = " ";

	private static int n;
	private static int[][] dp;
	private static int[][] tree;
	private static int[] left;
	private static int[] right;
	private static long answer;

	private static final void add(int row, int idx, int value) {
		while (idx <= n) {
			tree[row][idx] += value;
			idx += idx & -idx;
		}
	}

	private static final void update(int row, int l, int r, int value) {
		add(row, l, value);
		if (r < n) {
			add(row, r + 1, -value);
		}
	}

	private static final int query(int row, int idx) {
		int ret;

		ret = 0;
		while (idx > 0) {
			ret += tree[row][idx];
			idx -= idx & -idx;
		}
		return ret;
	}

	private static final int get(int row, int col) {
		return dp[row][col] + query(row, col);
	}

	private static final void process(int row, int col, int delta) {
		int i;
		int j;

		left[row] = col;
		right[row] = col;
		for (i = row + 1; i <= n; i++) {
			left[i] = n + 1;
			right[i] = 0;
		}
		i = row;
		j = col;
		while (true) {
			if (j < n && Math.max(get(i - 1, j + 1), get(i, j)) + delta == Math.max(get(i - 1, j + 1), get(i, j) + delta)) {
				j++;
			} else {
				i++;
			}
			if (i > n) {
				break;
			}
			right[i] = j;
		}
		i = row;
		j = col;
		while (true) {
			if (i < n && Math.max(get(i + 1, j - 1), get(i, j)) + delta == Math.max(get(i + 1, j - 1), get(i, j) + delta)) {
				i++;
			} else {
				j++;
			}
			if (j > n || right[i] < j) {
				break;
			}
			left[i] = Math.min(left[i], j);
		}
		for (i = row; i <= n; i++) {
			if (left[i] > right[i]) {
				continue;
			}
			update(i, left[i], right[i], delta);
			answer += (long) delta * (right[i] - left[i] + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		int i;
		int j;
		int value;
		int row;
		int col;
		int delta;
		char command;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		dp = new int[n + 1][n + 1];
		tree = new int[n + 1][n + 2];
		left = new int[n + 1];
		right = new int[n + 1];
		answer = 0L;
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine(), DELIM, false);
			for (j = 1; j <= n; j++) {
				value = Integer.parseInt(st.nextToken());
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + value;
				answer += dp[i][j];
			}
		}
		sb.append(answer).append('\n');
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), DELIM, false);
			command = st.nextToken().charAt(0);
			row = Integer.parseInt(st.nextToken());
			col = Integer.parseInt(st.nextToken());
			if (command == 'U') {
				delta = 1;
			} else {
				delta = -1;
			}
			process(row, col, delta);
			sb.append(answer).append('\n');
		}
		System.out.print(sb);
	}
}