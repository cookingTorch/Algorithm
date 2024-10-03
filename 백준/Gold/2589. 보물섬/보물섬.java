import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE >> 1;
	private static final char LAND = 'L';

	private static char[] map;
	private static int[] d;
	private static int[] initialDists;
	private static int[] dists;
	private static ArrayDeque<Integer> q;

	private static final int bfs(int start) {
		int i;
		int max;
		int pos;
		int npos;
		int ndist;

		dists[start] = 0;
		q.addLast(start);
		max = 0;
		while (!q.isEmpty()) {
			pos = q.pollFirst();
			ndist = dists[pos] + 1;
			for (i = 0; i < 4; i++) {
				npos = pos + d[i];
				if (map[npos] == LAND && ndist < dists[npos]) {
					dists[npos] = ndist;
					max = Math.max(max, ndist);
					q.addLast(npos);
				}
			}
		}
		return max;
	}

	public static void main(String[] args) throws IOException {
		int r;
		int c;
		int i;
		int j;
		int col;
		int thr;
		int pos;
		int max;
		int line;
		int size;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		line = c + 1;
		col = line + 1;
		thr = (r + 1) * col;
		map = new char[(r + 2) * col];
		for (i = col + 1; i < thr; i += col) {
			br.read(map, i, line);
		}
		size = (r + 2) * col;
		initialDists = new int[size];
		dists = new int[size];
		for (i = 0; i < size; i++) {
			initialDists[i] = INF;
		}
		d = new int[] {-col, 1, col, -1};
		max = 0;
		q = new ArrayDeque<>();
		for (i = 1; i <= r; i++) {
			for (j = 1; j <= c; j++) {
				pos = i * col + j;
				if (map[pos] == LAND) {
					System.arraycopy(initialDists, col + 1, dists, col + 1, size - col - 1);
					max = Math.max(max, bfs(pos));
				}
			}
		}
		System.out.print(max);
	}
}
