import java.util.ArrayList;

class Solution {
	private static final int DIFF = '0';
	private static final int[] FAIL = {-1};
	private static final int[] dx = {-1, 0, 1, 0};
	private static final int[] dy = {0, 1, 0, -1};
	private static final char SEA = 'X';

	private static int r;
	private static int c;
	private static char[][] map;

	private static int dfs(int x, int y) {
		int i;
		int nx;
		int ny;
		int res;

		res = map[x][y] - DIFF;
		map[x][y] = SEA;
		for (i = 0; i < 4; i++) {
			nx = x + dx[i];
			ny = y + dy[i];
			if (0 <= nx && nx < r && 0 <= ny && ny < c && map[nx][ny] != SEA) {
				res += dfs(nx, ny);
			}
		}
		return res;
	}

	public int[] solution(String[] maps) {
		int i;
		int j;
		ArrayList<Integer> list;

		r = maps.length;
		map = new char[r][];
		for (i = 0; i < r; i++) {
			map[i] = maps[i].toCharArray();
		}
		c = map[0].length;
		list = new ArrayList<>();
		for (i = 0; i < r; i++) {
			for (j = 0; j < c; j++) {
				if (map[i][j] != SEA) {
					list.add(dfs(i, j));
				}
			}
		}
		if (list.isEmpty()) {
			return FAIL;
		}
		return list.stream().mapToInt(x -> x).sorted().toArray();
	}
}
