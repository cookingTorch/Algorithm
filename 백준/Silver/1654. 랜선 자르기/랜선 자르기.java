import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int INF = Integer.MAX_VALUE;

    private static int k;
    private static int n;
    private static int[] cables;

    private static final boolean valid(int len) {
        int i;
        int cnt;

        cnt = 0;
        for (i = 0; i < k; i++) {
            if ((cnt += cables[i] / len) >= n) {
                return true;
            }
        }
        return false;
    }

    private static final int upperBound() {
        int mid;
        int left;
        int right;

        left = 0;
        right = INF;
        while (left < right) {
            mid = left + right >>> 1;
            if (valid(mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) throws IOException {
        int i;
        int ans;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        cables = new int[k];
        for (i = 0; i < k; i++) {
            cables[i] = Integer.parseInt(br.readLine());
        }
        ans = upperBound() - 1;
        if (ans != INF - 1 || !valid(INF)) {
            System.out.print(ans);
        } else {
            System.out.print(INF);
        }
    }
}
