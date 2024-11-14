import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        int n;
        int m;
        int l;
        int i;
        int mid;
        int cnt;
        int prev;
        int left;
        int right;
        int[] locs;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        locs = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (i = 0; i < n; i++) {
            locs[i] = Integer.parseInt(st.nextToken());
        }
        locs[n] = l;
        n++;
        Arrays.sort(locs);
        right = locs[0];
        for (i = 1; i < n; i++) {
            right = Math.max(right, locs[i] - locs[i - 1]);
        }
        left = 0;
        while (left < right) {
            mid = left + right >> 1;
            cnt = m;
            prev = 0;
            for (i = 0;;) {
                if ((prev += mid) >= locs[i]) {
                    prev = locs[i];
                    if (++i == n) {
                        right = mid;
                        break;
                    }
                } else {
                    if (--cnt < 0) {
                        left = mid + 1;
                        break;
                    }
                }
            }
        }
        System.out.print(right);
    }
}
