class Solution {
    private static final int MAX = 10_000_000;
    
    private static int calc(int n) {
        int i;
        int res;
        
        res = 1;
        for (i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                if (n / i <= MAX) {
                    return n / i;
                } else {
                    res = i;
                }
            }
        }
        return res;
    }

    public int[] solution(long begin, long end) {
        int s;
        int e;
        int i;
        int size;
        int[] ans;

        s = (int) begin;
        e = (int) end;
        size = e - s + 1;
        ans = new int[size];
        for (i = 0; i < size; i++) {
            ans[i] = calc(s + i);
        }
        if (s == 1) {
            ans[0] = 0;
        }
        return ans;
    }
}