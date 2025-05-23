import java.util.ArrayDeque;

class Solution {
	private static final char ROTATE = 'R';

	public int[][] solution(int[][] rc, String[] operations) {
		int r;
		int c;
		int i;
		int j;
		ArrayDeque<Integer> left;
		ArrayDeque<Integer> right;
		ArrayDeque<Integer> row;
		ArrayDeque<ArrayDeque<Integer>> mid;

		r = rc.length - 1;
		c = rc[0].length - 1;
		left = new ArrayDeque<>();
		right = new ArrayDeque<>();
		mid = new ArrayDeque<>();
		for (i = 0; i <= r; i++) {
			left.addLast(rc[i][0]);
			right.addLast(rc[i][c]);
		}
		for (i = 0; i <= r; i++) {
			row = new ArrayDeque<>();
			for (j = 1; j < c; j++) {
				row.addLast(rc[i][j]);
			}
			mid.addLast(row);
		}
		for (String op : operations) {
			if (op.charAt(0) == ROTATE) {
				row = mid.peekFirst();
				row.addFirst(left.pollFirst());
				right.addFirst(row.pollLast());
				row = mid.peekLast();
				row.addLast(right.pollLast());
				left.addLast(row.pollFirst());
			} else {
				left.addFirst(left.pollLast());
				mid.addFirst(mid.pollLast());
				right.addFirst(right.pollLast());
			}
		}
		for (i = 0; i <= r; i++) {
			rc[i][0] = left.pollFirst();
			row = mid.pollFirst();
			for (j = 1; j < c; j++) {
				rc[i][j] = row.pollFirst();
			}
			rc[i][c] = right.pollFirst();
		}
		return rc;
	}
}
