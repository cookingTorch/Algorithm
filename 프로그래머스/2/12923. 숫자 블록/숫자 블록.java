class Solution {
    private static final int MAX = 10_000_000;

    public int[] solution(long begin, long end) {
        int i;
        int j;
        int k;
        int len;
        int sqrt;
        int num;
        int start;
        int[] ans;

        start = (int) begin;
        len = (int) end - start + 1;
        ans = new int[len];
        for (i = start == 1 ? 1 : 0; i < len; i++) {
            num = start + i;
            sqrt = (int) Math.sqrt(num);
            for (j = 2; j <= sqrt; j++) {
                if (num % j == 0) {
                    if ((k = num / j) > MAX) {
                        ans[i] = j;
                    } else {
                        ans[i] = k;
                        break;
                    }
                }
            }
            if (ans[i] == 0) {
                ans[i]++;
            }
        }
        return ans;
    }
}
