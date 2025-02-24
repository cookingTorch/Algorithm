import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final long INF = Long.MAX_VALUE;
    private static final String DELIM = " ";

    private static final void dncOpt(int idx, int start, int end, int left, int right, long[] prefix, long[][] dp) {
        int i;
        int mid;
        int thr;
        int opt;
        long val;

        if (start > end) {
            return;
        }
        mid = start + end >> 1;
        thr = Math.min(mid - 1, right);
        opt = left;
        dp[idx][mid] = INF;
        for (i = left; i <= thr; i++) {
            val = dp[idx - 1][i] + (mid - i) * (prefix[mid] - prefix[i]);
            if (val < dp[idx][mid]) {
                dp[idx][mid] = val;
                opt = i;
            }
        }
        dncOpt(idx, start, mid - 1, left, opt, prefix, dp);
        dncOpt(idx, mid + 1, end, opt, right, prefix, dp);
    }

    public static void main(String[] args) throws IOException {
        int l;
        int g;
        int i;
        long[] prefix;
        long[][] dp;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), DELIM, false);
        l = Integer.parseInt(st.nextToken());
        g = Math.min(l, Integer.parseInt(st.nextToken()));
        prefix = new long[l];
        dp = new long[g][l];
        st = new StringTokenizer(br.readLine(), DELIM, false);
        dp[0][0] = prefix[0] = Integer.parseInt(st.nextToken());
        for (i = 1; i < l; i++) {
            prefix[i] = prefix[i - 1] + Integer.parseInt(st.nextToken());
            dp[0][i] = (i + 1) * prefix[i];
        }
        for (i = 1; i < g; i++) {
            dncOpt(i, i, l - 1, i - 1, l - 1, prefix, dp);
        }
        System.out.print(dp[g - 1][l - 1]);
    }
}
