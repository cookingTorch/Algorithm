import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int MOD = 1_000_000_000;
    private static final int DIGIT = 10;
    private static final int HALF = DIGIT >> 1;
    private static final int EMPTY = -1;
    private static final int NONE = 0;
    private static final int ZERO = 1;
    private static final int NINE = 1 << 1;
    private static final int BOTH = ZERO | NINE;

    private static long[][] stair;
    private static long[][][] dp;

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

    private static long getDp(int idx, int num, int bit) {
        long sum;

        if (num >= HALF) {
            num = DIGIT - num - 1;
            bit = ((bit & ZERO) << 1) | ((bit & NINE) >> 1);
        }
        if (dp[idx][num][bit] != EMPTY) {
            return dp[idx][num][bit];
        }
        sum = 0;
        if (num == 1) {
            sum = (sum + getDp(idx + 1, 0, bit | ZERO)) % MOD;
        } else if (num > 1) {
            sum = (sum + getDp(idx + 1, num - 1, bit)) % MOD;
        }
        sum = (sum + getDp(idx + 1, num + 1, bit)) % MOD;
        return dp[idx][num][bit] = sum;
    }

    public static void main(String[] args) throws IOException {
        int n;
        int i;
        int j;
        int k;
        int end;
        long sum;
        long ans;
        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        stair = new long[n][HALF];
        dp = new long[n][HALF][BOTH + 1];
        end = n - 1;
        for (i = 0; i < end; i++) {
            for (j = 0; j < HALF; j++) {
                stair[i][j] = EMPTY;
                for (k = 0; k < BOTH; k++) {
                    dp[i][j][k] = EMPTY;
                }
            }
        }
        dp[end][0][ZERO] = 1;
        stair[end][0] = 1;
        for (i = 1; i < HALF; i++) {
            stair[end][i] = 1;
            for (j = 0; j < BOTH; j++) {
                dp[end][i][j] = 1;
            }
        }
        sum = 0;
        for (i = 0; i < HALF; i++) {
            sum = (sum + getStair(0, i)) % MOD;
        }
        sum = (sum << 1) % MOD;
        ans = (sum - getStair(0, 0)) % MOD;
        sum = getDp(0, 0, ZERO) % MOD;
        for (i = 1; i < HALF; i++) {
            sum = (sum + getDp(0, i, NONE)) % MOD;
        }
        sum = (sum << 1) % MOD;
        ans = (ans - ((sum - getDp(0, 0, ZERO)) % MOD)) % MOD;
        System.out.print(ans < 0 ? ans + MOD : ans);
    }
}
