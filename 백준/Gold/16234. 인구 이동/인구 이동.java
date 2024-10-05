import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	private static int n;
	private static int l;
	private static int r;
	private static int col;
	private static int thr;
	private static int idx;
	private static int cnt;
	private static int[] d;
	private static int[] map;
	private static int[] positions;
	private static boolean[] visited;
	private static ArrayDeque<Integer> q;

	private static final void dfs(int pos) {
		int i;
		int npos;
		int diff;

		positions[idx++] = pos;
		visited[pos] = true;
		cnt++;
		for (i = 0; i < 4; i++) {
			npos = pos + d[i];
			if (visited[npos]) {
				continue;
			}
			diff = Math.abs(map[pos] - map[npos]);
			if (l <= diff && diff <= r) {
				dfs(npos);
			}
		}
	}

	private static final boolean open() {
		int i;
		int j;

		idx = 0;
		for (i = col; i < thr; i += col) {
			for (j = 1; j <= n; j++) {
				if (!visited[i + j]) {
					cnt = 0;
					dfs(i + j);
					if (cnt == 1) {
						visited[i + j] = false;
						idx--;
					} else {
						q.addLast(cnt);
					}
				}
			}
		}
		return !q.isEmpty();
	}

	private static final void move() {
		int i;
		int sum;
		int start;
		int end;

		end = 0;
		while (!q.isEmpty()) {
			start = end;
			end = start + q.pollFirst();
			sum = 0;
			for (i = start; i < end; i++) {
				sum += map[positions[i]];
				visited[positions[i]] = false;
			}
			sum /= end - start;
			for (i = start; i < end; i++) {
				map[positions[i]] = sum;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int i;
		int j;
		int size;
		int time;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ", false);
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		col = n + 2;
		thr = (n + 1) * col;
		size = col * col;
		map = new int[size];
		visited = new boolean[size];
		for (i = 1; i <= n; i++) {
			visited[i] = true;
		}
		for (i = col; i < thr; i += col) {
			visited[i] = true;
			st = new StringTokenizer(br.readLine(), " ", false);
			for (j = 1; j <= n; j++) {
				map[i + j] = Integer.parseInt(st.nextToken());
			}
			visited[i + j] = true;
		}
		System.arraycopy(visited, 1, visited, thr + 1, n);
		d = new int[] {-col, 1, col, -1};
		positions = new int[n * n];
		q = new ArrayDeque<>();
		for (time = 0; open(); time++) {
			move();
		}
		System.out.print(time);
	}
}
