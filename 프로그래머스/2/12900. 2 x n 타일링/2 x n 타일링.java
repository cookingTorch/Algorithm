class Solution {
    private static final long MOD = 1_000_000_007;
    private static final long[][] MATRIX = {{1L, 1L}, {1L, 0L}};

    private static long[][] multiply(long[][] mtx1, long[][] mtx2) {
        long[][] res;

        res = new long[2][2];
        res[0][0] = (mtx1[0][0] * mtx2[0][0] + mtx1[0][1] * mtx2[1][0]) % MOD;
        res[0][1] = (mtx1[0][0] * mtx2[0][1] + mtx1[0][1] * mtx2[1][1]) % MOD;
        res[1][0] = (mtx1[1][0] * mtx2[0][0] + mtx1[1][1] * mtx2[1][0]) % MOD;
        res[1][1] = (mtx1[1][0] * mtx2[0][1] + mtx1[1][1] * mtx2[1][1]) % MOD;
        return res;
    }

    private static long[][] power(long n) {
        long[][] sqrt;

        if (n == 1) {
            return MATRIX;
        }
        sqrt = power(n >>> 1);
        if ((n & 1) == 0) {
            return multiply(sqrt, sqrt);
        }
        return multiply(multiply(sqrt, sqrt), MATRIX);
    }

    public int solution(int n) {
        return (int) power(n)[0][0];
    }
}
