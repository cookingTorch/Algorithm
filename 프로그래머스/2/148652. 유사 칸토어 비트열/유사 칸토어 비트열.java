class Solution {
    private static long left;
    private static long right;

    private static int dnc(int n, long start, long end) {
        long len;

        if (end < left || right < start) {
            return 0;
        }
        if (left <= start && end <= right) {
            return 1 << (n << 1);
        }
        len = (end - start) / 5;
        n--;
        return dnc(n, start, start += len) + dnc(n, start, start += len) + dnc(n, start += len, start += len) + dnc(n, start, start + len);
    }

    public int solution(int n, long l, long r) {
        left = l - 1;
        right = r;
        return dnc(n, 0L, (long) Math.pow(5, n));
    }
}