import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Main {
	private static final int P = 10007;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m, h, i, j;
		int[] dp, blocks;
		int[][] students;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		students = new int[n][m];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			try {
				for (j = 0;; j++) {
					students[i][j] = Integer.parseInt(st.nextToken());
				}
			} catch (NoSuchElementException e) {}
		}
		dp = new int[h + 1];
		dp[0] = 1;
		n--;
		for (i = 0; i < n; i++) {
			blocks = students[i];
			for (j = h; j >= 0; j--) {
				for (int block : blocks) {
					if (block > 0 && j >= block) {
						dp[j] = (dp[j] + dp[j - block]) % P;
					}
				}
			}
		}
		for (int block : students[i]) {
			if (block > 0 && h >= block) {
				dp[h] = (dp[h] + dp[h - block]) % P;
			}
		}
		System.out.print(dp[h]);
	}
}
