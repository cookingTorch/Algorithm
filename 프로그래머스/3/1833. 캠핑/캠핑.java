import java.util.Arrays;

class Solution {
    private static int binarySearch(int[] arr, int val) {
        int l;
        int r;
        int mid;
        
        l = 0;
        r = arr.length;
        while (l < r) {
            mid = l + r >>> 1;
            if (arr[mid] < val) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return r;
    }

    public int solution(int n, int[][] data) {
        int r;
        int c;
        int i;
        int j;
        int x1;
        int y1;
        int x2;
        int y2;
        int cnt;
        int[] xs;
        int[] ys;
        int[][] dp;

        xs = new int[n];
        ys = new int[n];
        for (i = 0; i < n; i++) {
            xs[i] = data[i][0];
            ys[i] = data[i][1];
        }
        xs = Arrays.stream(xs).distinct().sorted().toArray();
        ys = Arrays.stream(ys).distinct().sorted().toArray();
        r = xs.length;
        c = ys.length;
        dp = new int[r + 1][c + 1];
        for (int[] pos : data) {
            pos[0] = binarySearch(xs, pos[0]) + 1;
            pos[1] = binarySearch(ys, pos[1]) + 1;
            dp[pos[0]][pos[1]]++;
        }
        for (i = 1; i <= r; i++) {
            for (j = 1; j <= c; j++) {
                dp[i][j] += dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
            }
        }
        cnt = 0;
        for (i = 0; i < n; i++) {
            for (j = i + 1; j < n; j++) {
                if (data[i][0] == data[j][0] || data[i][1] == data[j][1]) {
                    continue;
                }
                x1 = Math.min(data[i][0], data[j][0]);
                y1 = Math.min(data[i][1], data[j][1]);
                x2 = Math.max(data[i][0], data[j][0]);
                y2 = Math.max(data[i][1], data[j][1]);
                if (dp[x2 - 1][y2 - 1] - dp[x1][y2 - 1] - dp[x2 - 1][y1] + dp[x1][y1] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
