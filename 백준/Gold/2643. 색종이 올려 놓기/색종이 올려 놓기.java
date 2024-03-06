import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, max, temp, len1, len2, i, j;
		int[] dp;
		int[][] papers;
		
		n = Integer.parseInt(br.readLine());
		papers = new int[n][2];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			len1 = Integer.parseInt(st.nextToken());
			len2 = Integer.parseInt(st.nextToken());
			papers[i][0] = Math.min(len1, len2);
			papers[i][1] = Math.max(len1, len2);
		}
		Arrays.sort(papers, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0]) {
					return Integer.compare(o1[1], o2[1]);
				}
				return Integer.compare(o1[0], o2[0]);
			}
		});
		max = 0;
		dp = new int[n];
		for (i = 0; i < n; i++) {
			temp = 0;
			for (j = i - 1; j >= 0; j--) {
				if (papers[j][0] <= papers[i][0] && papers[j][1] <= papers[i][1]) {
					temp = Math.max(temp, dp[j]);
				}
			}
			dp[i] = temp + 1;
			max = Math.max(max, dp[i]);
		}
		System.out.print(max);
	}
}
