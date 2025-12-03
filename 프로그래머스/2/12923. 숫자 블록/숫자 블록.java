class Solution {
    private static final int MAX = 10_000_000;

    public int[] solution(long begin, long end) {
        int s;
        int e;
        int i;
        int j;
        int size;
        int[] ans;

        s = (int) begin;
        e = (int) end;
        size = e - s + 1;
        ans = new int[size];
        for (i = 2; i * i <= e; i++) {
            for (j = ((s - 1) / i + 1) * i; j <= e; j += i) {
                if (ans[j - s] == 0 && j / i <= MAX) {
                    ans[j - s] = j / i;
                }
            }
        }
        for (--i; i >= 2; i--) {
            for (j = ((s - 1) / i + 1) * i; j <= e; j += i) {
                if (ans[j - s] == 0) {
                    ans[j - s] = i;
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
