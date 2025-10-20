import java.util.ArrayList;

class Solution {
	private static final long INF = Long.MAX_VALUE;
	private static final long MIN = Long.MIN_VALUE;
	private static final char DOT = '.';
	private static final char STAR = '*';

	public String[] solution(int[][] line) {
		int i;
		int j;
		int len;
		int row;
		int col;
		long a;
		long b;
		long c;
		long d;
		long e;
		long f;
		long x;
		long y;
		long div;
		long minX;
		long minY;
		long maxX;
		long maxY;
		char[][] res;
		String[] ans;
		ArrayList<Long> xs;
		ArrayList<Long> ys;

		minX = INF;
		minY = INF;
		maxX = MIN;
		maxY = MIN;
		len = line.length;
		xs = new ArrayList<>();
		ys = new ArrayList<>();
		for (i = 0; i < len; i++) {
			a = line[i][0];
			b = line[i][1];
			e = line[i][2];
			for (j = i + 1; j < len; j++) {
				c = line[j][0];
				d = line[j][1];
				f = line[j][2];
				if ((div = a * d - b * c) == 0 || (x = b * f - e * d) % div != 0 || (y = e * c - a * f) % div != 0) {
					continue;
				}
				minX = Math.min(minX, x /= div);
				minY = Math.min(minY, y /= div);
				maxX = Math.max(maxX, x);
				maxY = Math.max(maxY, y);
				xs.add(x);
				ys.add(y);
			}
		}
		row = (int) (maxY - minY) + 1;
		col = (int) (maxX - minX) + 1;
		res = new char[row][col];
		for (i = 0; i < col; i++) {
			res[0][i] = DOT;
		}
		for (i = 1; i < row; i++) {
			System.arraycopy(res[0], 0, res[i], 0, col);
		}
		len = xs.size();
		for (i = 0; i < len; i++) {
			res[row - 1 - (int) (ys.get(i) - minY)][(int) (xs.get(i) - minX)] = STAR;
		}
		ans = new String[row];
		for (i = 0; i < row; i++) {
			ans[i] = new String(res[i]);
		}
		return ans;
	}
}
