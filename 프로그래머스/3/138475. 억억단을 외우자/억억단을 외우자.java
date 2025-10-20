class Solution {
    public int[] solution(int e, int[] starts) {
        int i;
        int j;
        int len;
        int max;
        int prev;
        int[] dp;
        int[] pf;
        int[] num;
        int[] ans;
        int[] pfExp;

        dp = new int[e + 1];
        pf = new int[e + 1];
        pfExp = new int[e + 1];
        dp[1] = 1;
        for (i = 2; i <= e; i++) {
            if (pf[i] == 0) {
                pf[i] = i;
                pfExp[i] = 2;
                dp[i] = 2;
                for (j = i << 1; j <= e; j += i) {
                    if (pf[j] == 0) {
                        pf[j] = i;
                    }
                }
            } else {
                prev = i / pf[i];
                if (pf[prev] == pf[i]) {
                    pfExp[i] = pfExp[prev] + 1;
                    dp[i] = dp[prev] / pfExp[prev] * pfExp[i];
                } else {
                    pfExp[i] = 2;
                    dp[i] = dp[prev] << 1;
                }
            }
        }
        max = 0;
        num = new int[e + 1];
        for (i = e; i >= 1; i--) {
            if (dp[i] >= max) {
                max = dp[i];
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