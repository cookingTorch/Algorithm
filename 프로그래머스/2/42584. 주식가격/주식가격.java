import java.util.ArrayDeque;

class Solution {
	public int[] solution(int[] prices) {
		int i;
		int len;
		int idx;
		int val;
		int[] ans;
		ArrayDeque<int[]> stack;

		len = prices.length;
		ans = new int[len];
		stack = new ArrayDeque<>(len + 1);
		stack.addFirst(new int[] {0, 0});
		for (i = 0; i < len; i++) {
			val = prices[i];
			while (stack.peekFirst()[1] > val) {
				idx = stack.pollFirst()[0];
				ans[idx] = i - idx;
			}
			stack.addFirst(new int[] {i, val});
		}
		i--;
		stack.pollLast();
		while (!stack.isEmpty()) {
			idx = stack.pollFirst()[0];
			ans[idx] = i - idx;
		}
		return ans;
	}
}
