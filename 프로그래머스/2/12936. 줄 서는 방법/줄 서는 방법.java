class Solution {
	public int[] solution(int n, long k) {
		int i;
		int j;
		int[] ans;
		long fact;
		long[] facts;
		boolean[] visited;

		facts = new long[n];
		facts[0] = 1;
		for (i = 1; i < n; i++) {
			facts[i] = facts[i - 1] * i;
		}
		ans = new int[n];
		visited = new boolean[n + 1];
		for (i = 0; i < n; i++) {
			fact = facts[n - 1 - i];
			for (j = 1; j <= n; j++) {
				if (visited[j]) {
					continue;
				}
				if (k <= fact) {
					ans[i] = j;
					visited[j] = true;
					break;
				}
				k -= fact;
			}
		}
		return ans;
	}
}
