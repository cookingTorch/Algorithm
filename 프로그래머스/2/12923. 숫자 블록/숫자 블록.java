class Solution {
    private static final int MAX = 10_000_000;

    public int[] solution(long begin, long end) {
        int s;
        int e;
        int i;
        int j;
        int num;
        int sqrt;
        int size;
        int[] ans;

        s = (int) begin;
        e = (int) end;
        size = e - s + 1;
        ans = new int[size];
        sqrt = (int) Math.sqrt(e);
        for (i = 2; i <= sqrt; i++) {
            num = (s - 1) / i + 1;
            for (j = num * i; j <= e && num <= MAX; j += i, num++) {
                if (ans[j - s] == 0) {
                    ans[j - s] = num;
                }
            }
        }
        for (i = sqrt; i >= 2; i--) {
            for (j = ((s - 1) / i + 1) * i - s; j < size; j += i) {
                if (ans[j] == 0) {
                    ans[j] = i;
                }
            }
        }
        for (i = 0; i < size; i++) {
            if (ans[i] == 0) {
                ans[i] = 1;
            }
        }
        if (s == 1) {
            ans[0] = 0;
        }
        return ans;
    }
}
