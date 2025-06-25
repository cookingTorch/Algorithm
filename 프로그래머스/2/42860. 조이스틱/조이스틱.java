class Solution {
    private static final int ALPH = 26;
    private static final char A = 'A';

    public int solution(String name) {
        int l;
        int r;
        int i;
        int len;
        int cnt;
        int min;
        int ans;
        int diff;

        len = name.length();
        for (i = 0; i < len; i++) {
            if (name.charAt(i) != A) {
                break;
            }
        }
        l = i;
        if (l == len) {
            return 0;
        }
        for (i = len - 1; i > l; i--) {
            if (name.charAt(i) != A) {
                break;
            }
        }
        r = i;
        diff = name.charAt(l) - A;
        ans = Math.min(diff, ALPH - diff);
        min = Math.min(l, len - r) + r - l;
        cnt = 0;
        for (i = l + 1; i <= r; i++) {
            if (name.charAt(i) == A) {
                cnt++;
            } else {
                min = Math.min(min, Math.min(i - 1 - cnt, len - i) + len - 1 - cnt);
                cnt = 0;
                diff = name.charAt(i) - A;
                ans += Math.min(diff, ALPH - diff);
            }
        }
        return ans + min;
    }
}
