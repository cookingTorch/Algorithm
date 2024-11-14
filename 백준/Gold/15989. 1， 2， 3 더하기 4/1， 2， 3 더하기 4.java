import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int MAX_N = 10_000;
	private static final char LINE_BREAK = '\n';

	public static void main(String[] args) throws IOException {
		int t;
		int n;
		int i;
		int[] dp2;
		int[] dp3;
		StringBuilder sb;
		BufferedReader br;

		br = new BufferedReader(new InputStreamReader(System.in));
		dp2 = new int[MAX_N + 1];
		dp3 = new int[MAX_N + 1];
		dp2[2] = 1;
		dp2[3] = 1;
		dp3[3] = 1;
		for (i = 4; i <= MAX_N; i++) {
			dp2[i] = 1 + dp2[i - 2];
			dp3[i] = 1 + dp2[i - 3] + dp3[i - 3];
		}
		t = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		while (t-- > 0) {
			n = Integer.parseInt(br.readLine());
			sb.append(1 + dp2[n] + dp3[n]).append(LINE_BREAK);
		}
		System.out.print(sb.toString());
	}
}
