import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

class Solution {
	private static final int SIZE = 11;
	private static final int DIFF = 'A';

	public String[] solution(String[] orders, int[] course) {
		int i;
		int j;
		int len;
		int sub;
		int cnt;
		int size;
		int order;
		int[] max;
		char[] res;
		boolean[] courses;
		String str;
		String[] ans;
		HashMap<Integer, Integer> map;
		ArrayList<Integer> list;

		courses = new boolean[SIZE];
		size = course.length;
		for (i = 0; i < size; i++) {
			courses[course[i]] = true;
		}
		map = new HashMap<>();
		max = new int[SIZE];
		size = orders.length;
		for (i = 0; i < size; i++) {
			order = 0;
			str = orders[i];
			len = str.length();
			for (j = 0; j < len; j++) {
				order |= 1 << str.charAt(j) - DIFF;
			}
			for (sub = order; sub != 0; sub = sub - 1 & order) {
				if (courses[len = Integer.bitCount(sub)]) {
					max[len] = Math.max(max[len], map.merge(sub, 1, (v, nv) -> v + 1));
				}
			}
		}
		list = new ArrayList<>();
		for (Entry entry : map.entrySet()) {
			sub = (int) entry.getKey();
			cnt = (int) entry.getValue();
			if (cnt > 1 && cnt == max[Integer.bitCount(sub)]) {
				list.add(sub);
			}
		}
		size = list.size();
		ans = new String[size];
		res = new char[SIZE];
		for (i = 0; i < size; i++) {
			sub = list.get(i);
			len = Integer.bitCount(sub);
			for (j = 0; sub != 0; sub ^= sub & -sub, j++) {
				res[j] = (char) (DIFF + Integer.numberOfTrailingZeros(sub));
			}
			ans[i] = new String(res, 0, len);
		}
		Arrays.sort(ans, 0, size);
		return ans;
	}
}
