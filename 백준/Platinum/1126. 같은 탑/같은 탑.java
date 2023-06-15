import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	private static int[] block;
	private static int[][] dp;
	
	private static int maxHeight(int num, int diff) {
		if (diff < 0 || diff > 500000) {
			return -1;
		}
		else if (dp[num][diff] == 0 && num != 0) {
			dp[num][diff] = Math.max(maxHeight(num - 1, diff), maxHeight(num - 1, diff + block[num]));
			if (maxHeight(num - 1, diff - block[num]) != -1) {
				dp[num][diff] = Math.max(dp[num][diff], dp[num - 1][diff - block[num]] + block[num]);
			}
			else if (maxHeight(num - 1, block[num] - diff) != -1) {
				dp[num][diff] = Math.max(dp[num][diff], dp[num - 1][block[num] - diff] + diff);
			}
		}
		return dp[num][diff];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, i;
		
		n = Integer.parseInt(br.readLine());
		dp = new int[n + 1][500001];
		Arrays.fill(dp[0], -1);
		dp[0][0] = 0;
		
		block = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			block[i] = Integer.parseInt(st.nextToken());
		}
		
		sb.append((maxHeight(n, 0) == 0) ? -1 : dp[n][0]);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}