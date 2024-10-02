import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	private static final int MIN = Integer.MIN_VALUE;
	private static final char LINE_BREAK = '\n';

	private static int n;
	private static int max;
	private static int min;
	private static int[] arr;

	private static final void dfs(int depth, int num, int add, int sub, int mul, int div) {
		if (depth == n) {
			if (num > max) {
				max = num;
			}
			if (num < min) {
				min = num;
			}
			return;
		}
		depth++;
		if (add != 0) {
			dfs(depth, num + arr[depth], add - 1, sub, mul, div);
		}
		if (sub != 0) {
			dfs(depth, num - arr[depth], add, sub - 1, mul, div);
		}
		if (mul != 0) {
			dfs(depth, num * arr[depth], add, sub, mul - 1, div);
		}
		if (div != 0) {
			dfs(depth, num / arr[depth], add, sub, mul, div - 1);
		}
	}

	public static void main(String[] args) throws IOException {
		int i;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ", false);
		arr = new int[n];
		for (i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		n--;
		max = MIN;
		min = INF;
		st = new StringTokenizer(br.readLine(), " ", false);
		dfs(0, arr[0], Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		System.out.print(new StringBuilder().append(max).append(LINE_BREAK).append(min));
	}
}
