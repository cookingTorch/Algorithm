import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	private static final int THR = 2;
	private static final int EMPTY = 0;
	private static final int[] dx = new int[] {0, -1, -1, -1, 0, 1, 1, 1};
	private static final int[] dy = new int[] {-1, -1, 0, 1, 1, 1, 0, -1};

	private static final class Cloud {
		private static ArrayDeque<Cloud> bin = new ArrayDeque<>();

		int x;
		int y;

		Cloud(int x, int y) {
			this.x = x;
			this.y = y;
		}

		private Cloud setCloud(int x, int y) {
			this.x = x;
			this.y = y;
			return this;
		}

		static Cloud getCloud(int x, int y) {
			if (bin.isEmpty()) {
				return new Cloud(x, y);
			}
			return bin.pollFirst().setCloud(x, y);
		}

		void delete() {
			bin.addFirst(this);
		}
	}

	private static int n;
	private static int[][] amount;
	private static boolean[][] visited;
	private static ArrayDeque<Cloud> clouds;

	private static final void rain(int d, int s) {
		int i;
		int j;
		int k;
		int x;
		int y;
		int nx;
		int ny;
		int ddx;
		int ddy;
		Cloud cloud;

		ddx = dx[d] * s;
		ddy = dy[d] * s;
		while (!clouds.isEmpty()) {
			cloud = clouds.pollFirst();
			x = ((cloud.x + ddx) % n + n) % n;
			y = ((cloud.y + ddy) % n + n) % n;
			amount[x][y]++;
			visited[x][y] = true;
			cloud.delete();
		}
		for (x = 0; x < n; x++) {
			for (y = 0; y < n; y++) {
				if (visited[x][y]) {
					for (i = 1; i < 8; i += 2) {
						nx = x + dx[i];
						ny = y + dy[i];
						if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
							continue;
						}
						if (amount[nx][ny] != EMPTY) {
							amount[x][y]++;
						}
					}
				}
			}
		}
		for (x = 0; x < n; x++) {
			for (y = 0; y < n; y++) {
				if (visited[x][y]) {
					visited[x][y] = false;
				} else if (amount[x][y] >= THR) {
					amount[x][y] -= THR;
					clouds.addFirst(Cloud.getCloud(x, y));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int m;
		int i;
		int j;
		int d;
		int s;
		int sum;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ", false);
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		amount = new int[n][n];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ", false);
			for (j = 0; j < n; j++) {
				amount[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		clouds = new ArrayDeque<>();
		clouds.addFirst(new Cloud(n - 1, 0));
		clouds.addFirst(new Cloud(n - 1, 1));
		clouds.addFirst(new Cloud(n - 2, 0));
		clouds.addFirst(new Cloud(n - 2, 1));
		visited = new boolean[n][n];
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine(), " ", false);
			d = Integer.parseInt(st.nextToken()) - 1;
			s = Integer.parseInt(st.nextToken());
			rain(d, s);
		}
		sum = 0;
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				sum += amount[i][j];
			}
		}
		System.out.print(sum);
	}
}
