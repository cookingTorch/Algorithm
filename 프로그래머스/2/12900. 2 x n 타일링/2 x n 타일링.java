class Solution {
    private static final long MOD = 1_000_000_007L;

    public int solution(int n) {
        int i;
        long fk;
        long fk1;
        long tmp;
        long tmp1;

        fk = 0L;
        fk1 = 1L;
        for (i = Integer.highestOneBit(n); i != 0; i >>= 1) {
            tmp = fk * ((fk1 << 1) - fk) % MOD;
            tmp1 = (fk1 * fk1 + fk * fk) % MOD;
            fk = tmp;
            fk1 = tmp1;
            if ((n & i) != 0) {
                tmp = fk1 + fk;
                fk = fk1;
                fk1 = tmp;
            }
        }
        return (int) (fk1 % MOD);
    }
}
