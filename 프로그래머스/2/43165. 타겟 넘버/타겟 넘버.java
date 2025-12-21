import java.util.HashMap;

class Solution {
	private static int mid;
	private static int cnt;
	private static int[] nums;
	private static HashMap<Integer, Integer> map;

	private static void dfsL(int num, int depth) {
		if (depth > mid) {
			map.merge(num, 1, (v, nv) -> v + 1);
			return;
		}
		dfsL(num - nums[depth], depth + 1);
		dfsL(num + nums[depth], depth + 1);
	}

	private static void dfsR(int num, int depth) {
		if (depth == mid) {
			cnt += map.getOrDefault(num, 0);
			return;
		}
		dfsR(num - nums[depth], depth - 1);
		dfsR(num + nums[depth], depth - 1);
	}

	public int solution(int[] numbers, int target) {
		cnt = 0;
		nums = numbers;
		mid = numbers.length >> 1;
		map = new HashMap<>();
		dfsL(0, 0);
		dfsR(target, numbers.length - 1);
		return cnt;
	}
}
