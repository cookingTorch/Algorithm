import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int MOD = 1_000_000_000;
    private static final int DIGIT = 10;
    private static final int EMPTY = -1;

    private static int n;
    private static long[][] stair;
    private static long[][][][] dp;

    private static long getStair(int idx, int num) {
        long sum;

        if (stair[idx][num] != EMPTY) {
            return stair[idx][num];
        }
        sum = 0;
        if (num > 0) {
            sum = (sum + getStair(idx + 1, num - 1)) % MOD;
        }
        if (num < 9) {
            sum = (sum + getStair(idx + 1, num + 1)) % MOD;
        }
        return stair[idx][num] = sum;
    }

    private static long getDp(int idx, int max, int min, int num) {
        long sum;

        if (dp[idx][max][min][num] != EMPTY) {
            return dp[idx][max][min][num];
        }
        sum = 0;
        if (num > 0) {
            sum = (sum + getDp(idx + 1, max, Math.min(min, num - 1), num - 1)) % MOD;
        }
        if (num < 9) {
            sum = (sum + getDp(idx + 1, Math.max(max, num + 1), min, num + 1)) % MOD;
        }
        return dp[idx][max][min][num] = sum;
    }

    public static void main(String[] args) throws IOException {
        int i;
        int j;
        int k;
        int l;
        long sum;
        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        stair = new long[n][DIGIT];
        dp = new long[n][DIGIT][][];
        for (i = 0; i < n; i++) {
            for (j = 0; j < DIGIT; j++) {
                stair[i][j] = EMPTY;
                dp[i][j] = new long[j + 1][DIGIT];
                for (k = 0; k <= j; k++) {
                    for (l = 0; l < DIGIT; l++) {
                        dp[i][j][k][l] = EMPTY;
                    }
                }
            }
        }
        for (i = 0; i < DIGIT; i++) {
            stair[n - 1][i] = 1;
            for (j = 0; j <= i; j++) {
                for (k = 0; k < DIGIT; k++) {
                    dp[n - 1][i][j][k] = 1;
                }
            }
        }
        for (i = 0; i < n; i++) {
            for (j = 0; j < DIGIT; j++) {
                dp[i][9][0][j] = 0;
            }
        }
        sum = 0;
        for (i = 1; i < DIGIT; i++) {
            sum = (sum + getStair(0, i)) % MOD;
        }
        for (i = 1; i < DIGIT; i++) {
            sum = (sum - getDp(0, i, i, i)) % MOD;
        }
        if (sum < 0) {
            sum += MOD;
        }
        System.out.print(sum);
    }
}