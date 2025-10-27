import java.util.HashSet;

class Solution {
	private static final int U = 'U';
	private static final int R = 'R';
	private static final int D = 'D';
	private static final int DIR = 2;
	private static final int MAX = 5;
	private static final int THR = MAX << 1;
	private static final int SHIFT = 4;
	private static final int[] dx = {-1, 0, 1, 0};
	private static final int[] dy = {0, 1, 0, -1};

	public int solution(String dirs) {
		int x;
		int y;
		int i;
		int nx;
		int ny;
		int len;
		int dir;
		int key;
		int cnt;
		HashSet<Integer> map;

		cnt = 0;
		map = new HashSet<>();
		len = dirs.length();
		x = MAX;
		y = MAX;
		for (i = 0; i < len; i++) {
			key = switch (dirs.charAt(i)) {
				case U -> {
					dir = 0;
					yield (x << SHIFT | y) << DIR | dir;
				}
				case R -> {
					dir = 1;
					yield (x << SHIFT | y + 1) << DIR | (dir + 2 & 3);
				}
				case D -> {
					dir = 2;
					yield (x + 1 << SHIFT | y) << DIR | (dir + 2 & 3);
				}
				default -> {
					dir = 3;
					yield (x << SHIFT | y) << DIR | dir;
				}
			};
			nx = x + dx[dir];
			ny = y + dy[dir];
			if (nx < 0 || ny < 0 || nx > THR || ny > THR) {
				continue;
			}
			if (!map.contains(key)) {
				map.add(key);
				cnt++;
			}
			x = nx;
			y = ny;
		}
		return cnt;
	}
}
