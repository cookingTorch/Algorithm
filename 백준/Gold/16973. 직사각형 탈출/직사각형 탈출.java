import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	private static final int[] dr = {-1, 0, 1, 0};
	private static final int[] dc = {0, 1, 0, -1};
	private static final char WALL = '1';
	
	private static char[][] map;
	private static boolean[][] visited;
	
	private static final int bfs(int h, int w, int sr, int sc, int fr, int fc) {
		int i;
		int j;
		int r;
		int c;
		int nr;
		int nc;
		int dist;
		int[] curr;
		ArrayDeque<int[]> q;
		
		q = new ArrayDeque<>();
		visited[sr][sc] = true;
		if (sr == fr && sc == fc) {
			return 0;
		}
		q.addLast(new int[] {sr, sc, 0});
		while (!q.isEmpty()) {
			curr = q.pollFirst();
			r = curr[0];
			c = curr[1];
			dist = curr[2];
			loop:
			for (i = 0; i < 4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];
				if (visited[nr][nc]) {
					continue;
				}
				visited[nr][nc] = true;
				for (j = 0; j < h; j++) {
					if (map[nr + j][nc] == WALL || map[nr + j][nc + w - 1] == WALL) {
						continue loop;
					}
				}
				for (j = 0; j < w; j++) {
					if (map[nr][nc + j] == WALL || map[nr + h - 1][nc + j] == WALL) {
						continue loop;
					}
				}
				if (nr == fr && nc == fc) {
					return dist + 1;
				}
				q.addLast(new int[] {nr, nc, dist + 1});
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int h;
		int w;
		int sr;
		int sc;
		int fr;
		int fc;
		int i;
		int j;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n + 2][m + 2];
		for (i = 0; i < n + 2; i++) {
			map[i][0] = WALL;
			map[i][m + 1] = WALL;
		}
		for (i = 0; i < m + 2; i++) {
			map[0][i] = WALL;
			map[n + 1][i] = WALL;
		}
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 1; j <= m; j++) {
				map[i][j] = st.nextToken().charAt(0);
			}
		}
		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		sr = Integer.parseInt(st.nextToken());
		sc = Integer.parseInt(st.nextToken());
		fr = Integer.parseInt(st.nextToken());
		fc = Integer.parseInt(st.nextToken());
		visited = new boolean[n + 2][m + 2];
		System.out.print(bfs(h, w, sr, sc, fr, fc));
	}
}
