class Solution {
	private static final int P = 'P';
	private static final int O = 'O';
	private static final int X = 'X';
	private static final int SIZE = 5;
	private static final int[] dx1 = {-1, 0, 1, 0};
	private static final int[] dy1 = {0, 1, 0, -1};
	private static final int[][] dx2 = {{-1, -2, -1}, {-1, 0, 1}, {1, 2, 1}, {-1, 0, 1}};
	private static final int[][] dy2 = {{-1, 0, 1}, {1, 2, 1}, {-1, 0, 1}, {-1, -2, -1}};
	
	private static int[][] map;
	
	private static boolean validate(int x, int y) {
		int i;
		int j;
		int nx;
		int ny;
		
		for (i = 0; i < 4; i++) {
			nx = x + dx1[i];
			ny = y + dy1[i];
			if (nx < 0 || nx >= SIZE || ny < 0 || ny >= SIZE) {
				continue;
			}
			if (map[nx][ny] == P) {
				return false;
			}
			if (map[nx][ny] == X) {
				continue;
			}
			for (j = 0; j < 3; j++) {
				nx = x + dx2[i][j];
				ny = y + dy2[i][j];
				if (nx < 0 || nx >= SIZE || ny < 0 || ny >= SIZE) {
					continue;
				}
				if (map[nx][ny] == P) {
					return false;
				}
			}
		}
		return true;
	}
	
	public int[] solution(String[][] places) {
		int i;
		int j;
		int k;
		int len;
		int[] ans;
		String[] place;
		
		len = places.length;
		ans = new int[len];
		map = new int[SIZE][SIZE];
		loop:
		for (i = 0; i < len; i++) {
			place = places[i];
			for (j = 0; j < SIZE; j++) {
				for (k = 0; k < SIZE; k++) {
					map[j][k] = place[j].charAt(k);
				}
			}
			ans[i] = 1;
			for (j = 0; j < SIZE; j++) {
				for (k = 0; k < SIZE; k++) {
					if (map[j][k] == P) {
						if (!validate(j, k)) {
							ans[i] = 0;
							continue loop;
						}
					}
				}
			}
		}
		return ans;
	}
}
