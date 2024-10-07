import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int PUSH = 4;
	private static final int POP = 3;
	private static final int RESTORE = 7;
	private static final int PRINT = 5;
	private static final char LINE_BREAK = '\n';

	public static void main(String[] args) throws IOException {
		int n;
		int i;
		int[] idx;
		int[] prev;
		long[] sum;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		idx = new int[n + 1];
		prev = new int[n + 1];
		sum = new long[n + 1];
		sb = new StringBuilder();
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine(), " ", false);
			switch (st.nextToken().length()) {
				case PUSH:
					sum[idx[i] = i] = sum[prev[i] = idx[i - 1]] + Long.parseLong(st.nextToken());
					break;
				case POP:
					idx[i] = prev[idx[i - 1]];
					break;
				case RESTORE:
					idx[i] = idx[Integer.parseInt(st.nextToken())];
					break;
				case PRINT:
					sb.append(sum[idx[i] = idx[i - 1]]).append(LINE_BREAK);
					break;
			}
		}
		System.out.print(sb.toString());
	}
}
