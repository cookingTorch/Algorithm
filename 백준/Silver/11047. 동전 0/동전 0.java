import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n, k, i, cnt, ans;
        int[] coins;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        coins = new int[n];
        for (i = 0; i < n; i++)
            coins[i] = Integer.parseInt(br.readLine());
        ans = 0;
        for (i = n - 1; i >= 0; i--) {
            cnt = k / coins[i];
            ans += cnt;
            k -= cnt * coins[i];
        }
        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}