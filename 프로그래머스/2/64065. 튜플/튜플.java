import java.util.StringTokenizer;

class Solution {
    private static final int MAX = 100_000;
    private static final String DELIM = "{,}";

    public int[] solution(String s) {
        int i;
        int size;
        int[] cnt;
        int[] ans;
        StringTokenizer st;

        size = 0;
        cnt = new int[MAX + 1];
        st = new StringTokenizer(s, DELIM, false);
        while (st.hasMoreTokens()) {
            if (cnt[Integer.parseInt(st.nextToken())]++ == 0) {
                size++;
            }
        }
        ans = new int[size];
        for (i = 1; i <= MAX; i++) {
            if (cnt[i] != 0) {
                ans[size - cnt[i]] = i;
            }
        }
        return ans;
    }
}