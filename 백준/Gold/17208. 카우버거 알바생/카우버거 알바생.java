import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        int n;
        int m;
        int k;
        int i;
        int j;
        int burgers;
        int fries;
        int[][] dp;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dp = new int[m + 1][k + 1];
        while (--n > 0) {
            st = new StringTokenizer(br.readLine());
            burgers = Integer.parseInt(st.nextToken());
            fries = Integer.parseInt(st.nextToken());
            for (i = m; i >= burgers; i--) {
                for (j = k; j >= fries; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - burgers][j - fries] + 1);
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        burgers = Integer.parseInt(st.nextToken());
        fries = Integer.parseInt(st.nextToken());
        if (m >= burgers && k >= fries) {
            System.out.print(Math.max(dp[m][k], dp[m - burgers][k - fries] + 1));
        } else {
            System.out.print(dp[m][k]);
        }
    }
}
