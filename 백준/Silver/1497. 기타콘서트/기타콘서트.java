import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int Y = 'Y';
	private static final int SPACE = ' ';
	private static final char[] FAIL = {'-', '1'};

	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int i;
		int j;
		int idx;
		int cnt;
		int min;
		long thr;
		long max;
		long sum;
		long guitar;
		long[] guitars;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ", false);
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		guitars = new long[n];
		max = 0;
		for (i = 0; i < n; i++) {
			while (br.read() != SPACE);
			guitar = 0;
			for (j = 0; j < m; j++) {
				guitar <<= 1;
				if (br.read() == Y) {
					guitar |= 1L;
				}
			}
			br.read();
			guitars[i] = guitar;
			max |= guitar;
		}
		if (max == 0L) {
			System.out.print(FAIL);
			return;
		}
		min = n;
		thr = (1 << n) - 1;
		n--;
		for (i = 1; i <= thr; i++) {
			sum = 0L;
			cnt = 0;
			for (j = i, idx = n; j > 0; j >>= 1, idx--) {
				if ((j & 1) == 1) {
					sum |= guitars[idx];
					cnt++;
				}
			}
			if (sum == max && cnt < min) {
				min = cnt;
			}
		}
		System.out.print(min);
	}
}
