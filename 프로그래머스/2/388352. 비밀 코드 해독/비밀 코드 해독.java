class Solution {
    public int solution(int n, int[][] q, int[] ans) {
        int i;
        int j;
        int c;
        int r;
        int len;
        int thr;
        int cnt;
        int code;
        int[] nums;

        len = q.length;
        nums = new int[len];
        for (i = 0; i < len; i++) {
            for (int num : q[i]) {
                nums[i] |= 1 << num - 1;
            }
        }
        cnt = 0;
        thr = 1 << n;
        for (code = (1 << 5) - 1; code < thr;) {
            for (j = 0; j < len; j++) {
                if (Integer.bitCount(code & nums[j]) != ans[j]) {
                    break;
                }
            }
            if (j == len) {
                cnt++;
            }
            c = code & -code;
            r = code + c;
            code = ((r ^ code) >>> 2) / c | r;
        }
        return cnt;
    }
}
