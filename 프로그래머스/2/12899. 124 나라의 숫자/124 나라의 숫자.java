class Solution {
    private static final char[] DIGITS = {'1', '2', '4'};

    public String solution(int n) {
        StringBuilder sb;

        sb = new StringBuilder();
        for (--n; n >= 0; n--) {
            sb.append(DIGITS[n % 3]);
            n /= 3;
        }
        return sb.reverse().toString();
    }
}
