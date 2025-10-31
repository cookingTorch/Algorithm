class Solution {
    public long[] solution(long[] numbers) {
        int i;
        int len;

        len = numbers.length;
        for (i = 0; i < len; i++) {
            numbers[i] = (numbers[i] & 1L) == 0 ? numbers[i] | 1L : numbers[i] ^ 3L << Long.numberOfTrailingZeros(~numbers[i] & numbers[i] + 1L) - 1;
        }
        return numbers;
    }
}
