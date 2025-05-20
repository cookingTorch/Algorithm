import java.util.HashMap;

class Solution {
    public int[] solution(String[] gems) {
        int i;
        int l;
        int r;
        int len;
        int sum;
        int total;
        int[] cnt;
        int[] ans;
        int[] display;
        Integer val;
        HashMap<String, Integer> map;

        len = gems.length;
        map = new HashMap<>();
        display = new int[len];
        total = 0;
        for (i = 0; i < len; i++) {
            val = map.get(gems[i]);
            if (val == null) {
                map.put(gems[i], val = total++);
            }
            display[i] = val;
        }
        sum = 0;
        cnt = new int[total];
        ans = new int[2];
        ans[1] = len;
        loop:
        for (l = 0, r = -1;;) {
            while (sum < total) {
                if (r == len - 1) {
                    break loop;
                }
                if (cnt[display[++r]]++ == 0) {
                    sum++;
                }
            }
            while (sum >= total) {
                if (--cnt[display[l++]] == 0) {
                    sum--;
                }
            }
            if (r - (l - 1) < ans[1] - ans[0]) {
                ans[0] = l - 1;
                ans[1] = r;
            }
        }
        ans[0]++;
        ans[1]++;
        return ans;
    }
}
