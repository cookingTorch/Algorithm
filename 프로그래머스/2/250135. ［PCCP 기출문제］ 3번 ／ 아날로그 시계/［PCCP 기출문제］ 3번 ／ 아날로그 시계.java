class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int res;

        res = ((h2 - h1) * 60 + m2 - m1 << 1) - (h2 - h1);
        if (h1 < 12 && 12 <= h2) {
            res -= 2;
        } else if (h1 == 12 && m1 == 0 && s1 == 0) {
            res--;
        }
        if (h1 == 0 && m1 == 0 && s1 == 0) {
            res--;
        }
        if (s1 > h1 % 12 * 5 + m1 / 12.0 + s1 / 720.0) {
            res--;
        }
        if (s1 > m1) {
            res--;
        }
        if (s2 >= h2 % 12 * 5 + m2 / 12.0 + s2 / 720.0) {
            res++;
        }
        if (s2 > m2 || (m2 == 0 && s2 == 0)) {
            res++;
        }
        return res;
    }
}
