import java.util.HashMap;

class Solution {
	private static final int RANGE = 10;

	public int solution(String[] want, int[] number, String[] discount) {
		int i;
		int ans;
		int len;
		int size;
		int item;
		int match;
		int[] arr;
		int[] num;
		int[] cnt;
		HashMap<String, Integer> map;

		map = new HashMap<>();
		len = want.length;
		num = new int[len + 1];
		for (i = 0; i < len; i++) {
			num[i] = number[i];
			map.put(want[i], i);
		}
		arr = new int[RANGE];
		cnt = new int[len + 1];
		match = 0;
		for (i = 0; i < RANGE; i++) {
			item = arr[i] = map.getOrDefault(discount[i], len);
			if (++cnt[item] == num[item]) {
				match++;
			}
		}
		ans = match == len ? 1 : 0;
		size = discount.length;
		for (; i < size; i++) {
			item = arr[i % RANGE];
			if (cnt[item]-- == num[item]) {
				match--;
			}
			map.get(discount[i]);
			item = arr[i % RANGE] = map.getOrDefault(discount[i], len);
			if (++cnt[item] == num[item]) {
				if (++match == len) {
					ans++;
				}
			}
		}
		return ans;
	}
}
