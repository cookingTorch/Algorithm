class Solution {
    public long solution(int k, int d) {
        long i;
        long r;
        long sum;
        double rr;

        r = d / k;
        rr = ((double) d / k) * ((double) d / k);
        sum = r + 1L;
        for (i = 0L; i <= r; i++) {
            sum += (long) Math.sqrt(rr - i * i);
        }
        return sum;
    }
}
