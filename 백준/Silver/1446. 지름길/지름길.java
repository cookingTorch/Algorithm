import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		int n;
		int d;
		int i;
		int end;
		int start;
		int[] dp;
		ArrayList<int[]>[] shortcuts;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ", false);
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		shortcuts = new ArrayList[d + 1];
		while (n-- > 0) {
			st = new StringTokenizer(br.readLine(), " ", false);
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			if (end <= d) {
				if (shortcuts[end] == null) {
					shortcuts[end] = new ArrayList<>();
				}
				shortcuts[end].add(new int[]{start, Integer.parseInt(st.nextToken())});
			}
		}
		dp = new int[d + 1];
		for (i = 1; i <= d; i++) {
			dp[i] = dp[i - 1] + 1;
			if (shortcuts[i] != null) {
				for (int[] shortcut : shortcuts[i]) {
					dp[i] = Math.min(dp[i], dp[shortcut[0]] + shortcut[1]);
				}
			}
		}
		System.out.print(dp[d]);
	}
}
