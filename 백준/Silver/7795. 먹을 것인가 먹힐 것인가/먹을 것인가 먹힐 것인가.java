import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static final int MIN = Integer.MIN_VALUE;
	private static final int MAX_NM = 20_001;
	private static final char LINE_BREAK = '\n';

	private static BufferedReader br;

	private static final int solution(int[] a, int[] b) throws IOException {
		int n;
		int m;
		int i;
		int cnt;
		int idxA;
		int idxB;
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ", false);
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ", false);
		for (i = 1; i <= n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine(), " ", false);
		for (i = 1; i <= m; i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(a, 1, n + 1);
		Arrays.sort(b, 1, m + 1);
		cnt = 0;
		idxA = n;
		idxB = m;
		while (idxB > 0) {
			if (a[idxA] > b[idxB]) {
				idxA--;
			} else {
				idxB--;
				cnt += n - idxA;
			}
		}
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		int t;
		int[] a;
		int[] b;
		StringBuilder sb;

		a = new int[MAX_NM];
		b = new int[MAX_NM];
		a[0] = MIN;
		br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		while (t-- > 0) {
			sb.append(solution(a, b)).append(LINE_BREAK);
		}
		System.out.print(sb.toString());
	}
}
