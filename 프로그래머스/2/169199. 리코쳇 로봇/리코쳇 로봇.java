import java.util.ArrayDeque;

class Solution {
	private static final int R = 'R';
	private static final int G = 'G';
	private static final int D = 'D';
	private static final int FAIL = -1;
	private static final int VISITED = 0;
	private static final int[] dx = {-1, 0, 1, 0};
	private static final int[] dy = {0, 1, 0, -1};

	private static int bfs(int[][] map, int x, int y) {
		int i;
		int nx;
		int ny;
		int size;
		int dist;
		int[] cur;
		ArrayDeque<int[]> q;

		q = new ArrayDeque<>();
		q.addLast(new int[] {x, y});
		map[x][y] = VISITED;
		dist = 0;
		while (!q.isEmpty()) {
			dist++;
			size = q.size();
			while (size-- > 0) {
				cur = q.pollFirst();
				x = cur[0];
				y = cur[1];
				for (i = 0; i < 4; i++) {
					nx = x;
					ny = y;
					while (map[nx += dx[i]][ny += dy[i]] != D);
					if (map[nx -= dx[i]][ny -= dy[i]] == VISITED) {
						continue;
					}
					if (map[nx][ny] == G) {
						return dist;
					}
					q.addLast(new int[] {nx, ny});
					map[nx][ny] = VISITED;
				}
			}
		}
		return FAIL;
	}

	public int solution(String[] board) {
		int r;
		int c;
		int x;
		int y;
		int i;
		int j;
		int[] row;
		int[][] map;
		String str;

		r = board.length;
		c = board[0].length();
		map = new int[r + 2][c + 2];
		x = 0;
		y = 0;
		row = map[0];
		for (i = 1; i <= c; i++) {
			row[i] = D;
		}
		System.arraycopy(row, 1, map[r + 1], 1, c);
		for (i = 1; i <= r; i++) {
			str = board[i - 1];
			row = map[i];
			row[0] = D;
			row[c + 1] = D;
			for (j = 1; j <= c; j++) {
				if ((row[j] = str.charAt(j - 1)) == R) {
					x = i;
					y = j;
				}
			}
		}
		return bfs(map, x, y);
	}
}
