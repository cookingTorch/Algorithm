import java.util.Arrays;

class Solution {
	public int[] solution(int n, int s) {
		int i;
		int num;
		int thr;
		int[] ans;

		if (n > s) {
			return new int[] {-1};
		}
		ans = new int[n];
		num = (s - 1) / n;
		thr = n - ((s - 1) % n + 1);
		for (i = 0; i < thr; i++) {
			ans[i] = num;
		}
		num++;
		for (; i < n; i++) {
			ans[i] = num;
		}
		return ans;
	}
}
