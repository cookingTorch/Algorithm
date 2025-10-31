class Solution {
    public long[] solution(long[] numbers) {
        int i;
        int len;

        len = numbers.length;
        for (i = 0; i < len; i++) {
            numbers[i] ^= (~numbers[i] & numbers[i] + 1L) * 3L >>> 1;
        }
        return numbers;
    }
}