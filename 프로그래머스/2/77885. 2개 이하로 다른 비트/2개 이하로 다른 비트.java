class Solution {
    private static long f(long x) {
        return (x & 1L) == 0 ? x | 1L : x ^ 3L << Long.numberOfTrailingZeros(~x & x + 1L) - 1;
    }

    public long[] solution(long[] numbers) {
        int i;
        int len;

        len = numbers.length;
        for (i = 0; i < len; i++) {
            numbers[i] = f(numbers[i]);
        }
        return numbers;
    }
}
