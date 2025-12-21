import java.util.ArrayDeque;

class Solution {
	private static final int WALL = 0;
	private static final int FAIL = -1;
	private static final int[] dx = {-1, 0, 1, 0};
	private static final int[] dy = {0, 1, 0, -1};

	public int solution(int[][] maps) {
		int i;
		int r;
		int c;
		int x;
		int y;
		int nx;
		int ny;
		int size;
		int dist;
		int[] cur;
		ArrayDeque<int[]> q;

		r = maps.length - 1;
		c = maps[0].length - 1;
		q = new ArrayDeque<>();
		q.addLast(new int[] {0, 0});
		maps[0][0] = WALL;
		for (dist = 2; (size = q.size()) != 0; dist++) {
			while (size-- > 0) {
				cur = q.pollFirst();
				x = cur[0];
				y = cur[1];
				for (i = 0; i < 4; i++) {
					nx = x + dx[i];
					ny = y + dy[i];
					if (nx < 0 || nx > r || ny < 0 || ny > c || maps[nx][ny] == WALL) {
						continue;
					}
					if (nx == r && ny == c) {
						return dist;
					}
					q.addLast(new int[] {nx, ny});
					maps[nx][ny] = WALL;
				}
			}
		}
		return FAIL;
	}
}
