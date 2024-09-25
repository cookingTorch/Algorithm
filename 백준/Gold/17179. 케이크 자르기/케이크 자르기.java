import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final char LINE_BREAK = '\n';

    private static int m;
    private static int l;
    private static int q;
    private static int[] arr;

    private static boolean validate(int mid) {
        int i;
        int cnt;
        int curr;

        cnt = 0;
        curr = 0;
        for (i = 0; i <= m; i++) {
            curr += arr[i];
            if (curr >= mid) {
                if (++cnt > q) {
                    return true;
                }
                curr = 0;
            }
        }
        return false;
    }

    private static int upperBound(int right) {
        int mid;
        int left;

        left = 0;
        while (left < right) {
            mid = left + right >> 1;
            if (validate(mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) throws IOException {
        int n;
        int i;
        int prev;
        int prefix;
        StringBuilder sb;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        arr = new int[m + 1];
        prefix = 0;
        for (i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(br.readLine()) - prefix;
            prefix += arr[i];
        }
        arr[m] = l - prefix;
        sb = new StringBuilder();
        prev = l;
        while (n-- > 0) {
            q = Integer.parseInt(br.readLine());
            prev = upperBound(prev);
            sb.append(prev - 1).append(LINE_BREAK);
        }
        System.out.print(sb);
    }
}
