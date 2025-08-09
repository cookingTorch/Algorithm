import java.util.Arrays;

class Solution {
    private static int num;
    private static int size;
    private static int dist;
    private static boolean[][] map;
    private static boolean[][] init;

    private static void fill(int x, int y) {
        int i;
        int j;
        int ei;
        int ej;

        ei = Math.min(size, x + dist);
        for (i = Math.max(0, x - dist + 1); i < ei; i++) {
            ej = Math.min(size, y + dist - Math.abs(i - x));
            for (j = Math.max(0, y - dist + 1 + Math.abs(i - x)); j < ej; j++) {
                if (!map[i][j]) {
                    map[i][j] = true;
                }
            }
        }
    }

    private static boolean validate() {
        int i;
        int j;
        int k;
        int cnt;
        int thr;

        thr = size + 1 >>> 1;
        for (i = 0; i < thr; i++) {
            for (j = 0; j < size; j++) {
                System.arraycopy(init[j], 0, map[j], 0, size);
            }
            cnt = num - 1;
            fill(i, 0);
            for (j = 0; j < size; j++) {
                for (k = 0; k < size; k++) {
                    if (!map[j][k]) {
                        if (--cnt == 0) {
                            return true;
                        }
                        fill(j, k);
                    }
                }
            }
        }
        return false;
    }

    public int solution(int n, int m, int[][] timetable) {
        int l;
        int r;
        int i;
        int[] s;
        int[] e;

        s = new int[m];
        e = new int[m];
        for (i = 0; i < m; i++) {
            s[i] = timetable[i][0];
            e[i] = timetable[i][1];
        }
        Arrays.sort(s);
        Arrays.sort(e);
        num = 1;
        for (l = 0, r = 0; r < m;) {
            for (; e[l] < s[r]; l++);
            for (; r < m && e[l] >= s[r]; r++);
            num = Math.max(num, r - l);
        }
        if (num == 1) {
            return 0;
        }
        size = n;
        init = new boolean[n][n];
        map = new boolean[n][n];
        l = 1;
        r = (n - 1 << 1) + 1;
        while (l < r) {
            dist = l + r >>> 1;
            if (validate()) {
                l = dist + 1;
            } else {
                r = dist;
            }
        }
        return r - 1;
    }
}
