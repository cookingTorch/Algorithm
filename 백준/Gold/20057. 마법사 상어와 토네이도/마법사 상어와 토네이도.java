import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int OUT_OF_BOUNDS = -1;

	private static int pos;
	private static int col;
	private static int cnt;
	private static int col2;
	private static int temp;
	private static int[] map;

	private static void add(int pos, int val) {
		temp += val;
		if (map[pos] == OUT_OF_BOUNDS) {
			cnt += val;
		} else {
			map[pos] += val;
		}
	}

	private static final void left() {
		int val;

		pos--;
		val = map[pos];
		temp = 0;
		add(pos - col2,
				val * 2 / 100);
		add(pos - col - 1,
				val * 10 / 100);
		add(pos - col,
				val * 7 / 100);
		add(pos - col + 1,
				val / 100);
		add(pos - 2,
				val * 5 / 100);
		add(pos + col - 1,
				val * 10 / 100);
		add(pos + col,
				val * 7 / 100);
		add(pos + col + 1,
				val / 100);
		add(pos + col2,
				val * 2 / 100);
		add(pos - 1,
				val - temp);
		map[pos] = 0;
	}

	private static final void down() {
		int val;

		pos += col;
		val = map[pos];
		temp = 0;
		add(pos - 2,
				val * 2 / 100);
		add(pos + col - 1,
				val * 10 / 100);
		add(pos - 1,
				val * 7 / 100);
		add(pos - col - 1,
				val / 100);
		add(pos + col2,
				val * 5 / 100);
		add(pos + col + 1,
				val * 10 / 100);
		add(pos + 1,
				val * 7 / 100);
		add(pos - col + 1,
				val / 100);
		add(pos + 2,
				val * 2 / 100);
		add(pos + col,
				val - temp);
		map[pos] = 0;
	}

	private static final void right() {
		int val;

		pos++;
		val = map[pos];
		temp = 0;
		add(pos - col2,
				val * 2 / 100);
		add(pos - col - 1,
				val / 100);
		add(pos - col,
				val * 7 / 100);
		add(pos - col + 1,
				val * 10 / 100);
		add(pos + 2,
				val * 5 / 100);
		add(pos + col - 1,
				val / 100);
		add(pos + col,
				val * 7 / 100);
		add(pos + col + 1,
				val * 10 / 100);
		add(pos + col2,
				val * 2 / 100);
		add(pos + 1,
				val - temp);
		map[pos] = 0;
	}

	private static final void up() {
		int val;

		pos -= col;
		val = map[pos];
		temp = 0;
		add(pos - 2,
				val * 2 / 100);
		add(pos + col - 1,
				val / 100);
		add(pos - 1,
				val * 7 / 100);
		add(pos - col - 1,
				val * 10 / 100);
		add(pos - col2,
				val * 5 / 100);
		add(pos + col + 1,
				val / 100);
		add(pos + 1,
				val * 7 / 100);
		add(pos - col + 1,
				val * 10 / 100);
		add(pos + 2,
				val * 2 / 100);
		add(pos - col,
				val - temp);
		map[pos] = 0;
	}

	private static final void tornado(int n) {
		int i;
		int j;

		cnt = 0;
		pos = ((n >> 1) + 2) * col + ((n >> 1) + 2);
		for (i = 1; i < n; i++) {
			for (j = 0; j < i; j++) {
				left();
			}
			for (j = 0; j < i; j++) {
				down();
			}
			i++;
			for (j = 0; j < i; j++) {
				right();
			}
			for (j = 0; j < i; j++) {
				up();
			}
		}
		for (j = 1; j < n; j++) {
			left();
		}
	}

	public static void main(String[] args) throws IOException {
		int n;
		int i;
		int j;
		int end;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ", false);
		n = Integer.parseInt(st.nextToken());
		col = n + 4;
		col2 = col << 1;
		map = new int[col * col];
		end = n + 1;
		for (i = 0; i < col; i++) {
			map[i] = OUT_OF_BOUNDS;
		}
		System.arraycopy(map, 0, map, col, col);
		for (i = 2; i <= end; i++) {
			map[i * col] = OUT_OF_BOUNDS;
			map[i * col + 1] = OUT_OF_BOUNDS;
			st = new StringTokenizer(br.readLine(), " ", false);
			for (j = 2; j <= end; j++) {
				map[i * col + j] = Integer.parseInt(st.nextToken());
			}
			map[i * col + end + 1] = OUT_OF_BOUNDS;
			map[i * col + end + 2] = OUT_OF_BOUNDS;
		}
		System.arraycopy(map, 0, map, (end + 1) * col, col);
		System.arraycopy(map, 0, map, (end + 2) * col, col);
		tornado(n);
		System.out.print(cnt);
	}
}
