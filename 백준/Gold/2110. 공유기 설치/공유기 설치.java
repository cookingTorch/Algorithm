import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        int n;
        int c;
        int i;
        int cnt;
        int mid;
        int prev;
        int left;
        int right;
        int[] locs;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken()) - 1;
        locs = new int[n];
        for (i = 0; i < n; i++) {
            locs[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(locs);
        left = 1;
        right = locs[n - 1] - locs[0] + 1;
        loop:
        while (left < right) {
            mid = left + right >> 1;
            cnt = c;
            prev = locs[0];
            for (i = 1; i < n; i++) {
                if (locs[i] - prev >= mid) {
                    if (--cnt == 0) {
                        left = mid + 1;
                        continue loop;
                    }
                    prev = locs[i];
                }
            }
            right = mid;
        }
        System.out.print(right - 1);
    }
}
