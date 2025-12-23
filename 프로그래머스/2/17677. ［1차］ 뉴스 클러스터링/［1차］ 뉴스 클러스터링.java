import java.util.HashMap;
import java.util.HashSet;

class Solution {
	private static int a = 'a';
	private static int z = 'z';
	private static int DIFF = a - 'A';
	private static int SHIFT = 16;
	private static double MOD = 65536.0;

	private static int lower(int ch) {
		return ch < a ? ch + DIFF : ch;
	}

	private static boolean isAlph(int ch) {
		return a <= ch && ch <= z;
	}

	private HashMap<Integer, Integer> build(String str, HashSet<Integer> keys) {
		int i;
		int ch1;
		int ch2;
		int len;
		int key;
		HashMap<Integer, Integer> map;

		map = new HashMap<>();
		len = str.length();
		for (i = 1; i < len; i++) {
			if (isAlph(ch1 = lower(str.charAt(i - 1))) && isAlph(ch2 = lower(str.charAt(i)))) {
				key = ch1 << SHIFT | ch2;
				keys.add(key);
				map.merge(key, 1, (v, nv) -> v + 1);
			}
		}
		return map;
	}

	public int solution(String str1, String str2) {
		int sum;
		int cnt1;
		int cnt2;
		int inter;
		HashSet<Integer> keys;
		HashMap<Integer, Integer> map1;
		HashMap<Integer, Integer> map2;

		sum = 0;
		inter = 0;
		keys = new HashSet();
		map1 = build(str1, keys);
		map2 = build(str2, keys);
		for (int key : keys) {
			cnt1 = map1.getOrDefault(key, 0);
			cnt2 = map2.getOrDefault(key, 0);
			inter += Math.min(cnt1, cnt2);
			sum += Math.max(cnt1, cnt2);
		}
		if (sum == 0) {
			return (int) MOD;
		}
		return (int) (MOD * inter / sum);
	}
}
