class Solution {
    public int[] solution(int e, int[] starts) {
        int i;
        int j;
        int len;
        int max;
        int prev;
        int[] div;
        int[] exp;
        int[] cnt;
        int[] num;
        int[] ans;

        div = new int[e + 1];
        exp = new int[e + 1];
        cnt = new int[e + 1];
        cnt[1] = 1;
        for (i = 2; i <= e; i++) {
            if (div[i] == 0) {
                div[i] = i;
                exp[i] = 2;
                cnt[i] = 2;
                for (j = i << 1; j <= e; j += i) {
                    if (div[j] == 0) {
                        div[j] = i;
                    }
                }
            } else {
                prev = i / div[i];
                if (div[prev] == div[i]) {
                    exp[i] = exp[prev] + 1;
                    cnt[i] = cnt[prev] / exp[prev] * exp[i];
                } else {
                    exp[i] = 2;
                    cnt[i] = cnt[prev] << 1;
                }
            }
        }
        max = 0;
        num = new int[e + 1];
        for (i = e; i >= 1; i--) {
            if (cnt[i] >= max) {
                max = cnt[i];
                num[i] = i;
            } else {
                num[i] = num[i + 1];
            }
        }
        len = starts.length;
        ans = new int[len];
        for (i = 0; i < len; i++) {
            ans[i] = num[starts[i]];
        }
        return ans;
    }
}