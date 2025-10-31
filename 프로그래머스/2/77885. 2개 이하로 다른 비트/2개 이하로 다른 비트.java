class Solution {
    private static long f(long x) {
        return x ^ (~x & x + 1L) * 3L >>> 1;
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