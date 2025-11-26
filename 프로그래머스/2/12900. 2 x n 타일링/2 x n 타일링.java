class Solution {
    private static final long MOD = 1_000_000_007L;
    private static final long[][] FIRST = {{1L, 1L}, {1L, 0L}};

    private static long[][] multiply(long[][] a, long[][] b) {
        long[][] c;

        c = new long[2][2];
        c[0][0] = ((a[0][0] * b[0][0]) + (a[0][1] * b[1][0])) % MOD;
        c[0][1] = ((a[0][0] * b[0][1]) + (a[0][1] * b[1][1])) % MOD;
        c[1][0] = ((a[1][0] * b[0][0]) + (a[1][1] * b[1][0])) % MOD;
        c[1][1] = ((a[1][0] * b[0][1]) + (a[1][1] * b[1][1])) % MOD;
        return c;
    }

    private static long[][] power(long[][] matrix, long n) {
        long[][] sqrt;

        if (n == 1) {
            return matrix;
        }
        if ((n & 1) == 0) {
            sqrt = power(matrix, n >> 1);
            return multiply(sqrt, sqrt);
        }
        return multiply(power(matrix, n - 1), matrix);
    }

    public int solution(int n) {
        return (int) power(FIRST, n + 1)[0][1];
    }
}
