import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final char LINE_BREAK = '\n';

	public static void main(String[] args) throws IOException {
		int t;
		int j;
		long i;
		long sum;
		long diff;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		while (t-- > 0) {
			st = new StringTokenizer(br.readLine());
			diff = -Long.parseLong(st.nextToken()) + Long.parseLong(st.nextToken());
			sum = 0L;
			for (sum = 0L, i = 0L, j = 0; sum < diff; i += 2L, sum += i, j++);
			if ((sum - diff) << 1 < i) {
				sb.append(j << 1);
			} else {
				sb.append((j << 1) - 1);
			}
			sb.append(LINE_BREAK);
		}
		System.out.print(sb);
	}
}
