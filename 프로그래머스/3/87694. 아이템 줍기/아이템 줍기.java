class Solution {
	private static final int MAX = 50;
	private static final int[] dx = {0, 1, 0, -1, 0, 1, 0};
	private static final int[] dy = {-1, 0, 1, 0, -1, 0, 1};

	private int x;
	private int y;
	private int dir;
	private boolean[][] map;

	private void add(int x1, int y1, int x2, int y2) {
		int i;

		for (i = x1; i <= x2; i++) {
			map[i][y1] = true;
		}
		for (i = y1; i <= y2; i++) {
			map[x2][i] = true;
		}
		for (i = x2; i >= x1; i--) {
			map[i][y2] = true;
		}
		for (i = y2; i >= y1; i--) {
			map[x1][i] = true;
		}
	}

	private void move() {
		int i;
		int nx;
		int ny;

		for (i = 0; i < 4; i++) {
			nx = x + dx[dir + i];
			ny = y + dy[dir + i];
			if (map[nx][ny]) {
				x = nx;
				y = ny;
				break;
			}
		}
		dir = dir + i + 3 & 3;
	}

	public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
		int startX;
		int startY;
		int idx;
		int dist;
		int[] dists;

		map = new boolean[MAX + 1 << 1][MAX + 1 << 1];
		startX = MAX;
		startY = MAX;
		for (int[] rec : rectangle) {
			add(rec[0] << 1, rec[1] << 1, rec[2] << 1, rec[3] << 1);
			if (rec[1] < startY) {
				startX = rec[0];
				startY = rec[1];
			} else if (rec[1] == startY && rec[0] < startX) {
				startX = rec[0];
			}
		}
		characterX <<= 1;
		characterY <<= 1;
		itemX <<= 1;
		itemY <<= 1;
		x = startX <<= 1;
		y = startY <<= 1;
		dir = 0;
		idx = 0;
		dist = 0;
		dists = new int[2];
		do {
			if ((x == characterX && y == characterY) || (x == itemX && y == itemY)) {
				dists[idx++] = dist;
				dist = 0;
			}
			move();
			dist++;
		} while (x != startX || y != startY);
		return Math.min(dists[0] + dist >>> 1, dists[1] >>> 1);
	}
}
