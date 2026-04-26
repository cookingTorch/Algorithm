import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	private static final String DELIM = " ";

	private static int a;
	private static int b;
	private static int m;
	private static int n;
	private static int[][] depth;
	private static int[] colMin;
	private static int[] leftLess;
	private static int[] rightLess;
	private static int[] stack;
	private static long pondArea;

	private static final long calculate() {
		int top;
		int bottom;
		int j;
		int stackSize;
		int rowHeight;
		int rowLimit;
		int widthLimit;
		int width;
		int area;
		long ret;
		long numerator;
		long denominator;
		long height;
		long volume;

		ret = 0L;
		rowLimit = Math.min(Math.max(a, b), m);
		for (top = 0; top < m; top++) {
			Arrays.fill(colMin, INF);
			for (bottom = top; bottom < m && bottom < top + rowLimit; bottom++) {
				rowHeight = bottom - top + 1;
				widthLimit = 0;
				if (rowHeight <= a) {
					widthLimit = Math.max(widthLimit, b);
				}
				if (rowHeight <= b) {
					widthLimit = Math.max(widthLimit, a);
				}
				widthLimit = Math.min(widthLimit, n);
				for (j = 0; j < n; j++) {
					if (depth[bottom][j] < colMin[j]) {
						colMin[j] = depth[bottom][j];
					}
				}
				if (widthLimit == 0) {
					continue;
				}
				stackSize = 0;
				for (j = 0; j < n; j++) {
					while (stackSize > 0 && colMin[stack[stackSize - 1]] >= colMin[j]) {
						stackSize--;
					}
					if (stackSize == 0) {
						leftLess[j] = -1;
					} else {
						leftLess[j] = stack[stackSize - 1];
					}
					stack[stackSize++] = j;
				}
				stackSize = 0;
				for (j = n - 1; j >= 0; j--) {
					while (stackSize > 0 && colMin[stack[stackSize - 1]] >= colMin[j]) {
						stackSize--;
					}
					if (stackSize == 0) {
						rightLess[j] = n;
					} else {
						rightLess[j] = stack[stackSize - 1];
					}
					stack[stackSize++] = j;
				}
				for (j = 0; j < n; j++) {
					if (colMin[j] == 0) {
						continue;
					}
					width = rightLess[j] - leftLess[j] - 1;
					if (width > widthLimit) {
						width = widthLimit;
					}
					area = rowHeight * width;
					if (area >= pondArea) {
						continue;
					}
					numerator = (long) colMin[j] * pondArea - 1L;
					denominator = pondArea - area;
					height = numerator / denominator;
					volume = height * area;
					if (volume > ret) {
						ret = volume;
					}
				}
			}
		}
		return ret;
	}

	public static void main(String[] args) throws IOException {
		int i;
		int j;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), DELIM, false);
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		pondArea = (long) m * n;
		depth = new int[m][n];
		colMin = new int[n];
		leftLess = new int[n];
		rightLess = new int[n];
		stack = new int[n];
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), DELIM, false);
			for (j = 0; j < n; j++) {
				depth[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(calculate());
	}
}