import java.util.Arrays;

class Solution {
    private static final int NUMS = 6;

    private static int idx;
    private static int[] arr;
    private static int[][] dices;

    private static void dfs(int mask, int sum) {
        int i;
        int[] dice;

        if (mask == 0) {
            arr[idx++] = sum;
            return;
        }
        dice = dices[Integer.numberOfTrailingZeros(mask)];
        mask &= mask - 1;
        for (i = 0; i < NUMS; i++) {
            dfs(mask, sum + dice[i]);
        }
    }

    public int[] solution(int[][] dice) {
        int n;
        int r;
        int c;
        int i;
        int j;
        int thr;
        int cnt;
        int max;
        int ans;
        int half;
        int full;
        int mask;
        int size;
        int[] a;
        int[] b;
        int[] res;

        dices = dice;
        n = dice.length;
        half = n >>> 1;
        size = (int) Math.pow(NUMS, half);
        a = new int[size];
        b = new int[size];
        full = (1 << n) - 1;
        thr = 1 << n >> 1;
        max = 0;
        ans = 0;
        for (mask = (1 << half) - 1; mask < thr;) {
            arr = a;
            idx = 0;
            dfs(mask, 0);
            arr = b;
            idx = 0;
            dfs(~mask & full, 0);
            Arrays.sort(a);
            Arrays.sort(b);
            cnt = 0;
            for (i = 0, j = 0; i < size; i++) {
                for (; j < size && a[i] > b[j]; j++);
                cnt += j;
            }
            if (cnt > max) {
                max = cnt;
                ans = mask;
            }
            cnt = 0;
            for (i = 0, j = 0; i < size; i++) {
                for (; j < size && b[i] > a[j]; j++);
                cnt += j;
            }
            if (cnt > max) {
                max = cnt;
                ans = ~mask & full;
            }
            c = mask & -mask;
            r = mask + c;
            mask = ((r ^ mask) >>> 2) / c | r;
        }
        res = new int[half];
        idx = 0;
        for (i = 0; i < n; i++) {
            if ((ans & 1 << i) != 0) {
                res[idx++] = i + 1;
            }
        }
        return res;
    }
}
