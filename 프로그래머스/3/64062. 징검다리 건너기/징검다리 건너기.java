import java.util.ArrayDeque;

class Solution {
	private static final int INF = Integer.MAX_VALUE >>> 1;

	public int solution(int[] stones, int k) {
		int i;
		int len;
		int min;
		int[] rock;
		ArrayDeque<int[]> stack;

		len = stones.length;
		stack = new ArrayDeque<>(len);
		stack.addFirst(new int[] {-1, INF});
		min = INF;
		for (i = 0; i < len; i++) {
			while (stack.peekFirst()[1] <= stones[i]) {
				rock = stack.pollFirst();
				if (i - stack.peekFirst()[0] - 1 >= k) {
					min = Math.min(min, rock[1]);
				}
			}
			stack.addFirst(new int[] {i, stones[i]});
		}
		while (stack.size() > 1) {
			rock = stack.pollFirst();
			if (len - stack.peekFirst()[0] - 1 >= k) {
				min = Math.min(min, rock[1]);
			}
		}
		return min;
	}
}
