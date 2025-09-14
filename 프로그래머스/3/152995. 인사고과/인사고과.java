import java.util.Arrays;

class Solution {
	private static final int FAIL = -1;

	public int solution(int[][] scores) {
		int ans;
		int sum;
		int work;
		int peer;
		int prev;
		int[] wanho;

		wanho = scores[0];
		sum = wanho[0] + wanho[1];
		Arrays.sort(scores, 0, scores.length, (o1, o2) -> o2[0] - o1[0]);
		ans = 1;
		prev = 0;
		work = 0;
		peer = 0;
		for (int[] score : scores) {
			if (score[0] != work) {
				work = score[0];
				prev = peer;
			}
			if (score[1] < prev) {
				if (score == wanho) {
					ans = FAIL;
					break;
				}
				continue;
			} else {
				peer = Math.max(peer, score[1]);
			}
			if (score[0] + score[1] > sum) {
				ans++;
			}
		}
		return ans;
	}
}