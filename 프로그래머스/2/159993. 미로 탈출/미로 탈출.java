import java.util.ArrayDeque;

class Solution {
	private static final int S = 'S';
	private static final int L = 'L';
	private static final int E = 'E';
	private static final int WALL = 'X';
	private static final int NULL = -1;
	private static final int[] dx = {-1, 0, 1, 0};
	private static final int[] dy = {0, 1, 0, -1};

	private static int bfs(int[][] map, int r, int c, int x, int y) {
		int i;
		int nx;
		int ny;
		int dist;
		int dest;
		int[] cur;
		boolean[][] visited;
		ArrayDeque<int[]> q;

		dest = L;
		q = new ArrayDeque<>();
		q.addLast(new int[] {x, y, 0});
		visited = new boolean[r][c];
		visited[x][y] = true;
		while (!q.isEmpty()) {
			cur = q.pollFirst();
			x = cur[0];
			y = cur[1];
			dist = cur[2] + 1;
			for (i = 0; i < 4; i++) {
				nx = x + dx[i];
				ny = y + dy[i];
				if (nx < 0 || nx >= r || ny < 0 || ny >= c || visited[nx][ny] || map[nx][ny] == WALL) {
					continue;
				}
				if (map[nx][ny] == dest) {
					if (dest == L) {
						dest = E;
						q.clear();
						q.addLast(new int[] {nx, ny, dist});
						visited = new boolean[r][c];
						visited[nx][ny] = true;
						break;
					} else {
						return dist;
					}
				}
				q.addLast(new int[] {nx, ny, dist});
				visited[nx][ny] = true;
			}
		}
		return NULL;
	}

	public int solution(String[] maps) {
		int i;
		int j;
		int r;
		int c;
		int x;
		int y;
		int[] row;
		int[][] map;
		String str;

		x = NULL;
		y = NULL;
		r = maps.length;
		c = maps[0].length();
		map = new int[r][c];
		for (i = 0; i < r; i++) {
			str = maps[i];
			row = map[i];
			for (j = 0; j < c; j++) {
				if ((row[j] = str.charAt(j)) == S) {
					x = i;
					y = j;
				}
			}
		}
		return bfs(map, r, c, x, y);
	}
}
