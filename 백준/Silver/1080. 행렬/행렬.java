import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int ONE = '1';
	private static final int ZERO = '0';
	private static final char NONE = '0';
	private static final char[] FAIL = {'-', '1'};

	private static int n;
	private static int m;
	private static int cnt;
	private static char[][] a;
	private static BufferedReader br;

	private static final void input(char[][] map) throws IOException {
		int i;

		for (i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
	}

	private static final void xor(int x, int y) {
		int i;
		int j;

		cnt++;
		for (i = x; i < x + 3; i++) {
			for (j = y; j < y + 3; j++) {
				if (a[i][j] == ZERO) {
					a[i][j] = ONE;
				} else {
					a[i][j] = ZERO;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int i;
		int j;
		char[][] b;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ", false);
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		a = new char[n][];
		b = new char[n][];
		input(a);
		input(b);
		if (n < 3 || m < 3) {
			for (i = 0; i < n; i++) {
				for (j = 0; j < m; j++) {
					if (a[i][j] != b[i][j]) {
						System.out.print(FAIL);
						return;
					}
				}
			}
			System.out.print(NONE);
			return;
		}
		cnt = 0;
		for (i = 0; i < n - 2; i++) {
			for (j = 0; j < m - 2; j++) {
				if (a[i][j] != b[i][j]) {
					xor(i, j);
				}
			}
			if (a[i][j] != b[i][j] || a[i][j + 1] != b[i][j + 1]) {
				System.out.print(FAIL);
				return;
			}
		}
		for (j = 0; j < m - 2; j++) {
			if (a[i][j] != b[i][j]) {
				xor(i, j);
			}
		}
		if (a[i][j] != b[i][j] || a[i][j + 1] != b[i][j + 1]) {
			System.out.print(FAIL);
			return;
		}
		for (++i; i < n; i++) {
			for (j = 0; j < m; j++) {
				if (a[i][j] != b[i][j]) {
					System.out.print(FAIL);
					return;
				}
			}
		}
		System.out.print(cnt);
	}
}
