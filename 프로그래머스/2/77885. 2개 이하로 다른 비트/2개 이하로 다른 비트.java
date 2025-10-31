class Solution {
    public long[] solution(long[] numbers) {
        int i;
        int len;
        long x;

        len = numbers.length;
        for (i = 0; i < len; i++) {
            x = numbers[i];
            numbers[i] = (x & 1L) == 0 ? x | 1L : x ^ 3L << Long.numberOfTrailingZeros(~x & x + 1L) - 1;
        }
        return numbers;
    }
}
