import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        int n;
        int k;
        int i;
        int idx;
        char[] str;
        char[] res;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        str = br.readLine().toCharArray();
        res = new char[n - k];
        res[0] = str[0];
        idx = 0;
        for (i = 1; i < n; i++) {
            while (idx >= 0 && i - idx <= k && str[i] > res[idx]) {
                idx--;
            }
            if (++idx == n - k) {
                break;
            }
            res[idx] = str[i];
        }
        System.out.print(res);
    }
}
