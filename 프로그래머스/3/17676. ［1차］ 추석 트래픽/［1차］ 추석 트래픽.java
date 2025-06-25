import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
    private static final int HR = 60;
    private static final int MIN = 60_000;
    private static final int SEC = 1_000;
    private static final double MS = 1_000.0;
    private static final String DELIM = " :s";

    public int solution(String[] lines) {
        int l;
        int r;
        int i;
        int len;
        int max;
        int[] end;
        int[] start;
        StringTokenizer st;

        len = lines.length;
        end = new int[len];
        start = new int[len];
        for (i = 0; i < len; i++) {
            st = new StringTokenizer(lines[i], DELIM, false);
            st.nextToken();
            end[i] = (Integer.parseInt(st.nextToken()) * HR + Integer.parseInt(st.nextToken())) * MIN + (int) (Double.parseDouble(st.nextToken()) * MS);
            start[i] = end[i] - (int) (Double.parseDouble(st.nextToken()) * MS) + 1;
        }
        Arrays.sort(end);
        Arrays.sort(start);
        max = 0;
        for (l = 0, r = 0; r < len; r++) {
            for (; start[r] - end[l] >= SEC; l++);
            max = Math.max(max, r - l + 1);
        }
        return max;
    }
}
