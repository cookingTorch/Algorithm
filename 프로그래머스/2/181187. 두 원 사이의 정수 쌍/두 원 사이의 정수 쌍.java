class Solution {
    public long solution(int r1, int r2) {
        long i;
        long r1s;
        long r2s;
        long ans;

        r1s = (long) r1 * r1;
        r2s = (long) r2 * r2;
        ans = 0L;
        for (i = 0L; i < r1; i++) {
            ans += (long) Math.sqrt(r2s - i * i) - ((long) Math.ceil(Math.sqrt(r1s - i * i)) - 1L);
        }
        for (; i < r2; i++) {
            ans += (long) Math.sqrt(r2s - i * i);
        }
        return ans << 2;
    }
}
