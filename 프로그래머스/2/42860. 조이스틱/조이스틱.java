class Solution {
    private static final int A = 'A';
    private static final int LEN = A + 26;

    public int solution(String name) {
        int i;
        int h;
        int v;
        int ch;
        int len;
        int prev;

        len = name.length();
        v = 0;
        prev = 0;
        for (i = 0; i < len; i++) {
            if ((ch = name.charAt(i)) != A) {
                v += Math.min(ch - A, LEN - ch);
                prev = i;
                break;
            }
        }
        if (i == len) {
            return 0;
        }
        h = len - prev;
        for (++i; i < len; i++) {
            if ((ch = name.charAt(i)) != A) {
                v += Math.min(ch - A, LEN - ch);
                h = Math.min(h, Math.min((prev << 1) + len - i, ((len - i) << 1) + prev));
                prev = i;
            }
        }
        return v + Math.min(h, prev);
    }
}
