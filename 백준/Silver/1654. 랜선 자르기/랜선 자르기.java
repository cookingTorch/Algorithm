import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
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

    private static final int upperBound(int right) {
        int mid;
        int left;

        left = 1;
        while (left != right) {
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
        int max;
        int ans;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        cables = new int[k];
        max = 0;
        for (i = 0; i < k; i++) {
            cables[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, cables[i]);
        }
        System.out.print(upperBound(max + 1) - 1);
    }
}
