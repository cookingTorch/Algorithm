import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

class Solution {
    private static final int DIFF = 'A';
    private static final int SIZE = 11;

    public String[] solution(String[] orders, int[] course) {
        int i;
        int j;
        int len;
        int bit;
        int sub;
        int cnt;
        int size;
        int[] max;
        int[] order;
        boolean[] lens;
        String str;
        String[] ans;
        StringBuilder sb;
        HashMap<Integer, Integer> map;
        ArrayList<Integer> courses;

        lens = new boolean[SIZE];
        size = course.length;
        for (i = 0; i < size; i++) {
            lens[course[i]] = true;
        }
        size = orders.length;
        order = new int[size];
        for (i = 0; i < size; i++) {
            bit = 0;
            str = orders[i];
            len = str.length();
            for (j = 0; j < len; j++) {
                bit |= 1 << str.charAt(j) - DIFF;
            }
            order[i] = bit;
        }
        max = new int[SIZE];
        map = new HashMap<>();
        for (i = 0; i < size; i++) {
            for (j = 0; j < i; j++) {
                bit = order[i] & order[j];
                for (sub = bit; sub != 0; sub = sub - 1 & bit) {
                    len = Integer.bitCount(sub);
                    if (!lens[len]) {
                        continue;
                    }
                    cnt = map.getOrDefault(sub, 0) + 1;
                    map.put(sub, cnt);
                    max[len] = Math.max(max[len], cnt);
                }
            }
        }
        courses = new ArrayList<>();
        for (Entry<Integer, Integer> entry : map.entrySet()) {
            bit = entry.getKey();
            if (entry.getValue() == max[Integer.bitCount(bit)]) {
                courses.add(bit);
            }
        }
        size = courses.size();
        ans = new String[size];
        for (i = 0; i < size; i++) {
            sb = new StringBuilder();
            bit = courses.get(i);
            for (; bit != 0; bit ^= 1 << cnt) {
                cnt = Integer.numberOfTrailingZeros(bit);
                sb.append((char) (cnt + DIFF));
            }
            ans[i] = sb.toString();
        }
        Arrays.sort(ans);
        return ans;
    }
}
