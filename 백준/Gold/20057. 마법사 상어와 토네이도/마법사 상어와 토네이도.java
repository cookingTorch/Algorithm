import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int n;
	private static int x;
	private static int y;
	private static int cnt;
	private static int temp;
	private static int[][] map;

	private static void add(int x, int y, int val) {
		temp += val;
		if (x < 0 || x >= n || y < 0 || y >= n) {
			cnt += val;
		} else {
			map[x][y] += val;
		}
	}

	private static final void left() {
		int val;

		y--;
		val = map[x][y];
		temp = 0;
		add(x - 2, y,
				val * 2 / 100);
		add(x - 1, y - 1,
				val * 10 / 100);
		add(x - 1, y,
				val * 7 / 100);
		add(x - 1, y + 1,
				val / 100);
		add(x, y - 2,
				val * 5 / 100);
		add(x + 1, y - 1,
				val * 10 / 100);
		add(x + 1, y,
				val * 7 / 100);
		add(x + 1, y + 1,
				val / 100);
		add(x + 2, y,
				val * 2 / 100);
		add(x, y - 1,
				val - temp);
		map[x][y] = 0;
	}

	private static final void down() {
		int val;

		x++;
		val = map[x][y];
		temp = 0;
		add(x, y - 2,
				val * 2 / 100);
		add(x + 1, y - 1,
				val * 10 / 100);
		add(x, y - 1,
				val * 7 / 100);
		add(x - 1, y - 1,
				val / 100);
		add(x + 2, y,
				val * 5 / 100);
		add(x + 1, y + 1,
				val * 10 / 100);
		add(x, y + 1,
				val * 7 / 100);
		add(x - 1, y + 1,
				val / 100);
		add(x, y + 2,
				val * 2 / 100);
		add(x + 1, y,
				val - temp);
		map[x][y] = 0;
	}

	private static final void right() {
		int val;

		y++;
		val = map[x][y];
		temp = 0;
		add(x - 2, y,
				val * 2 / 100);
		add(x - 1, y - 1,
				val / 100);
		add(x - 1, y,
				val * 7 / 100);
		add(x - 1, y + 1,
				val * 10 / 100);
		add(x, y + 2,
				val * 5 / 100);
		add(x + 1, y - 1,
				val / 100);
		add(x + 1, y,
				val * 7 / 100);
		add(x + 1, y + 1,
				val * 10 / 100);
		add(x + 2, y,
				val * 2 / 100);
		add(x, y + 1,
				val - temp);
		map[x][y] = 0;
	}

	private static final void up() {
		int val;

		x--;
		val = map[x][y];
		temp = 0;
		add(x, y - 2,
				val * 2 / 100);
		add(x + 1, y - 1,
				val / 100);
		add(x, y - 1,
				val * 7 / 100);
		add(x - 1, y - 1,
				val * 10 / 100);
		add(x - 2, y,
				val * 5 / 100);
		add(x + 1, y + 1,
				val / 100);
		add(x, y + 1,
				val * 7 / 100);
		add(x - 1, y + 1,
				val * 10 / 100);
		add(x, y + 2,
				val * 2 / 100);
		add(x - 1, y,
				val - temp);
		map[x][y] = 0;
	}

	private static final void tornado() {
		int i;
		int j;

		cnt = 0;
		x = y = n >> 1;
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
		int i;
		int j;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ", false);
		n = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ", false);
			for (j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		tornado();
		System.out.print(cnt);
	}
}
