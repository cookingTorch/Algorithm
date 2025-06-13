class Solution {
	private static final int MAX = 100;
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
		dir = (dir + i + 3) % 4;
	}

	public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
		int sX;
		int sY;
		int cX;
		int cY;
		int iX;
		int iY;
		int idx;
		int dist;
		int[] dists;

		if (characterX == itemX && characterY == itemY) {
			return 0;
		}
		map = new boolean[MAX + 2][MAX + 2];
		sX = MAX;
		sY = MAX;
		for (int[] rec : rectangle) {
			add(rec[0] << 1, rec[1] << 1, rec[2] << 1, rec[3] << 1);
			if (rec[1] < sY) {
				sX = rec[0];
				sY = rec[1];
			} else if (rec[1] == sY && rec[0] < sX) {
				sX = rec[0];
			}
		}
		cX = characterX << 1;
		cY = characterY << 1;
		iX = itemX << 1;
		iY = itemY << 1;
		x = sX <<= 1;
		y = sY <<= 1;
		dir = 0;
		idx = 0;
		dist = 0;
		dists = new int[2];
		do {
			if ((x == cX && y == cY) || (x == iX && y == iY)) {
				dists[idx++] = dist;
				dist = 0;
			}
			move();
			dist++;
		} while (x != sX || y != sY);
		return Math.min(dists[0] + dist >> 1, dists[1] >> 1);
	}
}
