import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	private static final int WALL = -1;
	private static final int APPLE = 1;
	private static final int EMPTY = 0;
	private static final int MAX = 10000;
	private static final char LEFT = 'L';
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, k, x, l, size, max, pos, next, dir, time, diff, cnt, head, tail, i;
		int[] map, snake, d;
		
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		size = n + 2;
		max = size * size;
		d = new int[] {-size, 1, size, -1};
		map = new int[max];
		for (i = 1; i <= n; i++) {
			map[i] = WALL;
			map[(n + 1) * size + i] = WALL;
			map[i * size] = WALL;
			map[i * size + n + 1] = WALL;
		}
		for (i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken()) * size + Integer.parseInt(st.nextToken())] = APPLE;
		}
		dir = 1;
		l = Integer.parseInt(br.readLine());
		cnt = 1;
		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		if (st.nextToken().charAt(0) == LEFT) {
			diff = 3;
		} else {
			diff = 1;
		}
		snake = new int[MAX];
		head = 0;
		tail = 0;
		pos = 1 * size + 1;
		snake[head] = pos;
		for (time = 1;; time++, pos = next) {
			next = pos + d[dir];
			switch (map[next]) {
			case WALL:
				System.out.print(time);
				return;
			case EMPTY:
				map[snake[tail++]] = EMPTY;
			case APPLE:
				map[next] = WALL;
				snake[++head] = next;
			}
			if (time == x) {
				dir = (dir + diff) % 4;
				if (cnt++ == l) {
					x = INF;
				} else {
					st = new StringTokenizer(br.readLine());
					x = Integer.parseInt(st.nextToken());
					if (st.nextToken().charAt(0) == LEFT) {
						diff = 3;
					} else {
						diff = 1;
					}
				}
			}
		}
	}
}
