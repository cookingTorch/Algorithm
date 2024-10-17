import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int upperBound(int k, int n, int[] cables, int right) {
        int i;
        int cnt;
        int mid;
        int left;

        left = 1;
        loop:
        while (left != right) {
            mid = left + right >>> 1;
            cnt = 0;
            for (i = 0; i < k; i++) {
                if ((cnt += cables[i] / mid) >= n) {
                    left = mid + 1;
                    continue loop;
                }
            }
            right = mid;
        }
        return left;
    }

    public static void main(String[] args) throws IOException {
        int k;
        int n;
        int i;
        int max;
        int[] cables;
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
        System.out.print(upperBound(k, n, cables, max + 1) - 1);
    }
}
