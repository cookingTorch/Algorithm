class Solution {
    public long solution(int k, int d) {
        long i;
        long r;
        long sum;
        double rr;

        sum = 0;
        r = d / k;
        rr = ((double) d / k) * ((double) d / k);
        for (i = 0L; i <= r; i++) {
            sum += (long) Math.sqrt(rr - i * i) + 1L;
        }
        return sum;
    }
}
