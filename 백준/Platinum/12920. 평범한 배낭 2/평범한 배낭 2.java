import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m, v, c, k, size, i, j;
		int[] dp;
		ArrayList<Integer> weight;
		ArrayList<Integer> value;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		weight = new ArrayList<>();
		value = new ArrayList<>();
		weight.add(0);
		value.add(0);
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			for (j = 1; j < k; k -= j, j *= 2) {
				weight.add(v * j);
				value.add(c * j);
			}
			weight.add(v * k);
			value.add(c * k);
		}
		dp = new int[m + 1];
		size = weight.size() - 1;
		for (i = 1; i <= size; i++) {
			for (j = m; j >= 1; j--) {
				if (j >= weight.get(i)) {
					dp[j] = Math.max(dp[j], dp[j - weight.get(i)] + value.get(i));
				}
			}
		}
		System.out.print(dp[m]);
	}
}
