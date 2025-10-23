import java.util.Arrays;

class Solution {
	private static final int RADIX = 10;

	private static int getS(String line) {
		return Integer.parseInt(line, 11, 13, RADIX) * 3_600_000
				+ Integer.parseInt(line, 14, 16, RADIX) * 60_000
				+ Integer.parseInt(line, 17, 19, RADIX) * 1_000
				+ Integer.parseInt(line, 20, 23, RADIX);
	}

	private static int getT(String line) {
		return (int) (Double.parseDouble(line.substring(24, line.length() - 1)) * 1_000.0);
	}

	public int solution(String[] lines) {
		int l;
		int r;
		int i;
		int len;
		int max;
		int[] end;
		int[] start;

		len = lines.length;
		end = new int[len];
		start = new int[len];
		for (i = 0; i < len; i++) {
			end[i] = getS(lines[i]);
			start[i] = end[i] - getT(lines[i]) + 1;
		}
		Arrays.sort(start);
		max = 0;
		for (l = 0, r = 0; r < len; r++) {
			for (; end[l] < start[r] - 999; l++);
			max = Math.max(max, r - l + 1);
		}
		return max;
	}
}
