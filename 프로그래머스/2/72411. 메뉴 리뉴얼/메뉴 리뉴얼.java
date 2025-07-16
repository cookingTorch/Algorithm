import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class Solution {
    private static final int A = 'A';
    private static final int MIN = 2;
    private static final int MAX = 11;

    public String[] solution(String[] orders, int[] course) {
        int i;
        int len;
        int sub;
        int cnt;
        int mask;
        int bitCnt;
        int[] cnts;
        String[] ans;
        HashMap<Integer, Integer> map;
        ArrayList<String> res;
        ArrayList<Integer>[] lists;
        StringBuilder sb;

        cnts = new int[MAX];
        map = new HashMap<>();
        lists = new ArrayList[MAX];
        for (int num : course) {
            cnts[num] = MIN;
            lists[num] = new ArrayList<>();
        }
        for (String order : orders) {
            mask = 0;
            len = order.length();
            for (i = 0; i < len; i++) {
                mask |= 1 << order.charAt(i) - A;
            }
            for (sub = mask; sub != 0; sub = sub - 1 & mask) {
                bitCnt = Integer.bitCount(sub);
                if (lists[bitCnt] != null) {
                    cnt = map.getOrDefault(sub, 0) + 1;
                    map.put(sub, cnt);
                    if (cnt > cnts[bitCnt]) {
                        cnts[bitCnt] = cnt;
                        lists[bitCnt].clear();
                        lists[bitCnt].add(sub);
                    } else if (cnt == cnts[bitCnt]) {
                        lists[bitCnt].add(sub);
                    }
                }
            }
        }
        res = new ArrayList<>();
        for (int num : course) {
            for (int menu : lists[num]) {
                sb = new StringBuilder();
                for (; menu != 0; menu = menu & menu - 1) {
                    sb.append((char) (Integer.numberOfTrailingZeros(menu) + A));
                }
                res.add(sb.toString());
            }
        }
        res.toArray(ans = new String[res.size()]);
        Arrays.sort(ans);
        return ans;
    }
}
