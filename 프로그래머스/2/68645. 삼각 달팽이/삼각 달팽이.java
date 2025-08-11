class Solution {
    private static final int EMPTY = 0;

    private static void fill(int[][] arr, int n) {
        int x;
        int y;
        int i;
        int j;
        int num;

        num = 1;
        for (i = 0; i < n; i++) {
            arr[i][0] = num++;
        }
        for (i = 1; i < n; i++) {
            arr[n - 1][i] = num++;
        }
        for (i = n - 2; i > 0; i--) {
            arr[i][i] = num++;
        }
        if (n <= 3) {
            return;
        }
        for (x = 1, y = 1;;) {
            for (i = x + 1; arr[i][y] == EMPTY; i++) {
                arr[i][y] = num++;
            }
            if (--i == x) {
                break;
            }
            x = i;
            for (j = y + 1; arr[x][j] == EMPTY; j++) {
                arr[x][j] = num++;
            }
            if (--j == y) {
                break;
            }
            y = j;
            for (i = x - 1, j = y - 1; arr[i][j] == EMPTY; i--, j--) {
                arr[i][j] = num++;
            }
            if (++i == x) {
                break;
            }
            x = i;
            y = ++j;
        }
    }

    public int[] solution(int n) {
        int i;
        int j;
        int idx;
        int[] ans;
        int[][] arr;

        arr = new int[n][];
        for (i = 0; i < n; i++) {
            arr[i] = new int[i + 1];
        }
        fill(arr, n);
        ans = new int[n * (n + 1) >>> 1];
        idx = 0;
        for (i = 0; i < n; i++) {
            for (j = 0; j <= i; j++) {
                ans[idx++] = arr[i][j];
            }
        }
        return ans;
    }
}
