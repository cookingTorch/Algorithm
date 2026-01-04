class Solution {
    public int[] solution(int n) {
        int i;
        int idx;
        int num;
        int len;
        int level;
        int[] ans;

        num = 1;
        idx = 0;
        level = 0;
        ans = new int[n * (n + 1) >>> 1];
        for (len = n; len != 0; len--) {
            for (i = 0; i < len; i++) {
                ans[idx += level++] = num++;
            }
            if (--len == 0) {
                break;
            }
            for (i = 0; i < len; i++) {
                ans[++idx] = num++;
            }
            if (--len == 0) {
                break;
            }
            for (i = len; i > 0; i--) {
                ans[idx -= level--] = num++;
            }
        }
        return ans;
    }
}
