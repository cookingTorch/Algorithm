import java.util.HashSet;

class Solution {
	private static final int DIFF = '0';

	private static int cnt;
	private static int len;
	private static int[] nums;
	private static boolean[] visited;
	private static HashSet<Integer> set;

	private static boolean isPrime(int n) {
		int i;

		if (n == 2 || n == 3) {
			return true;
		}
		if (n < 2 || (n & 1) == 0 || n % 3 == 0) {
			return false;
		}
		for (i = 5; i * i <= n; i += 6) {
			if (n % i == 0 || n % (i + 2) == 0) {
				return false;
			}
		}
		return true;
	}

	private static void dfs(int num, int depth) {
		int i;

		if (!set.contains(num)) {
			if (isPrime(num)) {
				cnt++;
			}
			set.add(num);
		}
		if (depth++ == len) {
			return;
		}
		num *= 10;
		for (i = 0; i < len; i++) {
			if (!visited[i]) {
				visited[i] = true;
				dfs(num + nums[i], depth);
				visited[i] = false;
			}
		}
	}

	public int solution(String numbers) {
		int i;

		len = numbers.length();
		nums = new int[len];
		for (i = 0; i < len; i++) {
			nums[i] = numbers.charAt(i) - DIFF;
		}
		visited = new boolean[len];
		set = new HashSet<>();
		cnt = 0;
		for (i = 0; i < len; i++) {
			visited[i] = true;
			dfs(nums[i], 1);
			visited[i] = false;
		}
		return cnt;
	}
}
