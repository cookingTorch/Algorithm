class Solution {
    private static final int MAX = 10_000_000;
    
    private static int calc(long n) {
        int i;
        int res;
        
        res = 1;
        for (i = 2; i <= MAX && i * i <= n; i++) {
            if (n % i == 0) {
                if (n / i <= MAX) {
                    return (int) (n / i);
                } else {
                    res = i;
                }
            }
        }
        return res;
    }

    public int[] solution(long begin, long end) {
        int i;
        int size;
        int[] ans;

        size = (int) (end - begin + 1);
        ans = new int[size];
        for (i = 0; i < size; i++) {
            ans[i] = calc(begin + i);
        }
        if (begin == 1L) {
            ans[0] = 0;
        }
        return ans;
    }
}