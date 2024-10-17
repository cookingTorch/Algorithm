import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	private static final int MAX = 1_002;

	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int i;
		int j;
		int k;
		int min;
		boolean[] s;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ", false);
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		s = new boolean[MAX];
		st = new StringTokenizer(br.readLine(), " ", false);
		for (i = 0; i < m; i++) {
			s[Integer.parseInt(st.nextToken())] = true;
		}
		min = INF;
		for (i = 1; i < MAX; i++) {
			if (s[i]) {
				continue;
			}
			for (j = i; j < MAX; j++) {
				if (s[j]) {
					continue;
				}
				for (k = j; k < MAX; k++) {
					if (s[k]) {
						continue;
					}
					min = Math.min(min, Math.abs(n - i * j * k));
				}
			}
		}
		System.out.print(min);
	}
}
