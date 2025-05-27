class Solution {
    public int solution(String s) {
        int i;
        int p;
        int r;
        int thr;
        int len;
        int max;
        int[] manacher;
        char[] str;

        len = s.length();
        str = new char[len << 1 | 1];
        for (i = 0; i < len; i++) {
            str[i << 1 | 1] = s.charAt(i);
        }
        len = len << 1 | 1;
        manacher = new int[len];
        max = 0;
        p = r = -1;
        for (i = 0; i < len; i++) {
            if (i > r) {
                p = i;
                thr = Math.min(len - 1, p << 1);
                for (r = p; r <= thr && str[r] == str[(p << 1) - r]; r++);
                max = Math.max(max, manacher[i] = --r - p);
            } else {
                if (manacher[(p << 1) - i] == r - i) {
                    p = i;
                    thr = Math.min(len - 1, p << 1);
                    for (; r <= thr && str[r] == str[(p << 1) - r]; r++);
                    max = Math.max(max, manacher[i] = --r - p);
                } else {
                    manacher[i] = Math.min(manacher[(p << 1) - i], r - i);
                }
            }
        }
        return max;
    }
}
