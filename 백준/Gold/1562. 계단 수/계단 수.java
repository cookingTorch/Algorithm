import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int MOD = 1_000_000_000;
    private static final int DIGIT = 10;
    private static final int HALF = DIGIT >> 1;
    private static final int EMPTY = -1;

    private static int n;
    private static long[][] stair;
    private static long[][][][] dp;

    private static long getStair(int idx, int num) {
        long sum;

        if (num >= HALF) {
            num = DIGIT - num - 1;
        }
        if (stair[idx][num] != EMPTY) {
            return stair[idx][num];
        }
        sum = 0;
        if (num > 0) {
            sum = (sum + getStair(idx + 1, num - 1)) % MOD;
        }
        sum = (sum + getStair(idx + 1, num + 1)) % MOD;
        return stair[idx][num] = sum;
    }

    private static long getDp(int idx, int max, int min, int num) {
        int temp;
        long sum;

        if (num >= HALF) {
            num = DIGIT - num - 1;
            temp = max;
            max = DIGIT - min - 1;
            min = DIGIT - temp - 1;
        }
        if (dp[idx][max][min][num] != EMPTY) {
            return dp[idx][max][min][num];
        }
        sum = 0;
        if (num > 0) {
            sum = (sum + getDp(idx + 1, max, Math.min(min, num - 1), num - 1)) % MOD;
        }
        sum = (sum + getDp(idx + 1, Math.max(max, num + 1), min, num + 1)) % MOD;
        return dp[idx][max][min][num] = sum;
    }

    public static void main(String[] args) throws IOException {
        int i;
        int j;
        int k;
        int l;
        long sum;
        long ans;
        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        stair = new long[n][HALF];
        dp = new long[n][DIGIT][][];
        for (i = 0; i < n; i++) {
            for (j = 0; j < HALF; j++) {
                stair[i][j] = EMPTY;
            }
            for (j = 0; j < DIGIT; j++) {
                dp[i][j] = new long[j + 1][HALF];
                for (k = 0; k <= j; k++) {
                    for (l = 0; l < HALF; l++) {
                        dp[i][j][k][l] = EMPTY;
                    }
                }
            }
        }
        for (i = 0; i < HALF; i++) {
            stair[n - 1][i] = 1;
        }
        for (i = 0; i < DIGIT; i++) {
            for (j = 0; j <= i; j++) {
                for (k = 0; k < HALF; k++) {
                    dp[n - 1][i][j][k] = 1;
                }
            }
        }
        for (i = 0; i < n; i++) {
            for (j = 0; j < HALF; j++) {
                dp[i][9][0][j] = 0;
            }
        }
        sum = 0;
        for (i = 0; i < HALF; i++) {
            sum = (sum + getStair(0, i)) % MOD;
        }
        sum = (sum << 1) % MOD;
        ans = (sum - getStair(0, 0)) % MOD;
        sum = 0;
        for (i = 0; i < HALF; i++) {
            sum = (sum + getDp(0, i, i, i)) % MOD;
        }
        sum = (sum << 1) % MOD;
        ans = (ans - ((sum - getDp(0, 0, 0, 0)) % MOD)) % MOD;
        System.out.print(ans < 0 ? ans + MOD : ans);
    }
}