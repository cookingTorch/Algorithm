import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
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
            end[i] = (Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken())) * 60000 + (int) (Double.parseDouble(st.nextToken()) * 1000.0);
            start[i] = end[i] - (int) (Double.parseDouble(st.nextToken()) * 1000.0) + 1;
        }
        Arrays.sort(end);
        Arrays.sort(start);
        max = 0;
        for (l = 0, r = 0; r < len; r++) {
            for (; start[r] - end[l] >= 1000; l++);
            max = Math.max(max, r - l + 1);
        }
        return max;
    }
}
