class Solution {
    private static final int COMMA = ',';
    private static final int END = '}';
    private static final int DIFF = '0';
    private static final int MAX = 100_000;

    private static int size;

    private static int[] count(String s) {
        int i;
        int ch;
        int num;
        int len;
        int[] cnt;

        size = 0;
        cnt = new int[MAX + 1];
        len = s.length() - 1;
        num = 0;
        for (i = 2; i < len; i++) {
            ch = s.charAt(i);
            switch (ch) {
                case END:
                    i += 2;
                case COMMA:
                    if (cnt[num]++ == 0) {
                        size++;
                    }
                    num = 0;
                    break;
                default:
                    num = num * 10 + ch - DIFF;
            }
        }
        return cnt;
    }

    public int[] solution(String s) {
        int i;
        int[] cnt;
        int[] ans;

        cnt = count(s);
        ans = new int[size];
        for (i = 1; i <= MAX; i++) {
            if (cnt[i] != 0) {
                ans[size - cnt[i]] = i;
            }
        }
        return ans;
    }
}
