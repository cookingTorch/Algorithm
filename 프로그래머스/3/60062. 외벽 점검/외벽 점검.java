import java.util.Arrays;

class Solution {
    private static final int INF = Integer.MAX_VALUE >>> 1;
    private static final int FAIL = -1;

    public int solution(int n, int[] weak, int[] dist) {
        int i;
        int j;
        int k;
        int len;
        int bit;
        int temp;
        int size;
        int next;
        int friends;
        int[] dp;
        int[][] bits;

        len = weak.length;
        friends = dist.length;
        size = 1 << len;
        Arrays.sort(dist, 0, friends);
        for (i = 0, j = friends - 1; i < j; i++, j--) {
            temp = dist[i];
            dist[i] = dist[j];
            dist[j] = temp;
        }
        bits = new int[len][friends];
        for (i = 0; i < len; i++) {
            for (j = 0; j < friends; j++) {
                bit = 0;
                for (k = 0; k < len; k++) {
                    if ((weak[(i + k) % len] - weak[i] + n) % n > dist[j]) {
                        break;
                    }
                    bit |= 1 << (i + k) % len;
                }
                bits[i][j] = bit;
            }
        }
        dp = new int[size];
        for (i = 1; i < size; i++) {
            dp[i] = INF;
        }
        for (i = 0; i < size; i++) {
            if (dp[i] >= friends) {
                continue;
            }
            for (j = 0; j < len; j++) {
                if ((i & (1 << j)) == 0) {
                    next = i | bits[j][dp[i]];
                    dp[next] = Math.min(dp[next], dp[i] + 1);
                }
            }
        }
        return dp[size - 1] == INF ? FAIL : dp[size - 1];
    }
}
