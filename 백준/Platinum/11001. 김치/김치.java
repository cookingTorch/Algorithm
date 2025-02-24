import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final String DELIM = " ";

    private static int d;
    private static int[] v;
    private static long ans;

    private static final void dncOpt(int start, int end, int left, int right, long[] t) {
        int i;
        int mid;
        int min;
        int max;
        int opt;
        long tmp;
        long val;

        if (start > end) {
            return;
        }
        mid = start + end >> 1;
        min = Math.max(mid, left);
        max = Math.min(mid + d, right);
        val = 0;
        opt = min;
        for (i = min; i <= max; i++) {
            tmp = (i - mid) * t[i];
            if (tmp > val) {
                val = tmp;
                opt = i;
            }
        }
        ans = Math.max(ans, val + v[mid]);
        dncOpt(start, mid - 1, left, opt, t);
        dncOpt(mid + 1, end, opt, right, t);
    }

    public static void main(String[] args) throws IOException {
        int n;
        int i;
        long[] t;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), DELIM, false);
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        t = new long[n];
        st = new StringTokenizer(br.readLine(), DELIM, false);
        for (i = 0; i < n; i++) {
            t[i] = Integer.parseInt(st.nextToken());
        }
        v = new int[n];
        st = new StringTokenizer(br.readLine(), DELIM, false);
        for (i = 0; i < n; i++) {
            v[i] = Integer.parseInt(st.nextToken());
        }
        ans = 0;
        dncOpt(0, n - 1, 0, n - 1, t);
        System.out.print(ans);
    }
}
