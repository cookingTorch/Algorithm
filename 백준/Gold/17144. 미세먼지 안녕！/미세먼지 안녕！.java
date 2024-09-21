import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int WALL = Integer.MAX_VALUE;

	private static int r;
	private static int c;
	private static int ap1;
	private static int ap2;
	private static int thr;
	private static int col;
	private static int[] d;
	private static int[] map;
	private static int[] diff;

	private static void diffuse(int pos) {
		int i;
		int val;
		int npos;

		val = map[pos] / 5;
		for (i = 0; i < 4; i++) {
			npos = pos + d[i];
			if (map[npos] != WALL) {
				map[pos] -= val;
				diff[npos] += val;
			}
		}
	}

	private static void diffuseAll() {
		int i;
		int j;

		for (i = col + 1; i < ap1; i += col) {
			diffuse(i);
		}
		for (i = ap2 + col; i < thr; i += col) {
			diffuse(i);
		}
		for (i = col; i < thr; i += col) {
			for (j = 2; j <= c; j++) {
				diffuse(i + j);
			}
		}
		for (i = col; i < thr; i += col) {
			for (j = i + 1; j <= i + c; j++) {
				map[j] += diff[j];
				diff[j] = 0;
			}
		}
	}

	private static void purify() {
		int i;

		for (i = ap1 - col; i > col + 1; i -= col) {
			map[i] = map[i - col];
		}
		System.arraycopy(map, col + 2, map, col + 1, c - 1);
		for (i = col + c; i < ap1; i += col) {
			map[i] = map[i + col];
		}
		System.arraycopy(map, ap1 + 1, map, ap1 + 2, c - 2);
		map[ap1 + 1] = 0;
		for (i = ap2 + col; i < thr - col; i += col) {
			map[i] = map[i + col];
		}
		System.arraycopy(map, r * col + 2, map, r * col + 1, c - 1);
		for (i = r * col + c; i > ap2 + col; i -= col) {
			map[i] = map[i - col];
		}
		System.arraycopy(map, ap2 + 1, map, ap2 + 2, c - 2);
		map[ap2 + 1] = 0;
	}

	public static void main(String[] args) throws IOException {
		int t;
		int i;
		int j;
		int cnt;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ", false);
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		col = c + 2;
		thr = (r + 1) * col;
		map = new int[(r + 2) * col];
		for (i = 1; i <= c; i++) {
			map[i] = WALL;
		}
		for (i = col; i < thr; i += col) {
			map[i] = WALL;
			st = new StringTokenizer(br.readLine(), " ", false);
			map[i + 1] = Integer.parseInt(st.nextToken());
			if (map[i + 1] == -1) {
				map[i + 1] = WALL;
				ap2 = i + 1;
			}
			for (j = 2; j <= c; j++) {
				map[i + j] = Integer.parseInt(st.nextToken());
			}
			map[i + c + 1] = WALL;
		}
		ap1 = ap2 - col;
		System.arraycopy(map, 1, map, (r + 1) * col + 1, c);
		diff = new int[(r + 1) * col];
		d = new int[] {-col, 1, col, -1};
		while (t-- > 0) {
			diffuseAll();
			purify();
		}
		map[ap1] = 0;
		map[ap2] = 0;
		cnt = 0;
		for (i = col; i < thr; i += col) {
			for (j = 1; j <= c; j++) {
				cnt += map[i + j];
			}
		}
		System.out.print(cnt);
	}
}
