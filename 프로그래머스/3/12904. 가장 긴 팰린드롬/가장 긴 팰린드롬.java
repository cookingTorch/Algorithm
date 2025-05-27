class Solution {
    public int solution(String s) {
        int i;
        int l;
        int r;
        int len;
        int max;
        char[] str;

        str = s.toCharArray();
        len = str.length;
        max = 1;
        for (i = 1; i < len; i++) {
            for (l = i - 1, r = i; l >= 0 && r < len; l--, r++) {
                if (str[l] != str[r]) {
                    break;
                }
            }
            max = Math.max(max, r - l - 1);
            for (l = i - 1, r = i + 1; l >= 0 && r < len; l--, r++) {
                if (str[l] != str[r]) {
                    break;
                }
            }
            max = Math.max(max, r - l - 1);
        }
        return max;
    }
}
