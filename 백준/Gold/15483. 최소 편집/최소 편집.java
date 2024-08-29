import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        int i;
        int j;
        int len1;
        int len2;
        int[][] dp;
        String str1;
        String str2;
        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(System.in));
        len1 = (str1 = br.readLine()).length();
        len2 = (str2 = br.readLine()).length();
        dp = new int[len1 + 1][len2 + 1];
        for (i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (i = 0; i <= len2; i++) {
            dp[0][i] = i;
        }
        for (i = 1; i <= len1; i++) {
            for (j = 1; j <= len2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        System.out.print(dp[len1][len2]);
    }
}
