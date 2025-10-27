class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        int i;
        int x1;
        int y1;
        int x2;
        int y2;
        int dx;
        int len;

        n--;
        m--;
        x1 = x2 = x;
        y1 = y2 = y;
        len = queries.length;
        loop:
        for (i = len - 1; i >= 0; i--) {
            dx = queries[i][1];
            switch (queries[i][0]) {
                case 0:
                    if (y1 != 0) {
                        if ((y1 += dx) > m) {
                            break loop;
                        }
                    }
                    y2 = Math.min(y2 + dx, m);
                    break;
                case 1:
                    if (y2 != m) {
                        if ((y2 -= dx) < 0) {
                            break loop;
                        }
                    }
                    y1 = Math.max(y1 - dx, 0);
                    break;
                case 2:
                    if (x1 != 0) {
                        if ((x1 += dx) > n) {
                            break loop;
                        }
                    }
                    x2 = Math.min(x2 + dx, n);
                    break;
                case 3:
                    if (x2 != n) {
                        if ((x2 -= dx) < 0) {
                            break loop;
                        }
                    }
                    x1 = Math.max(x1 - dx, 0);
            }
        }
        return Math.max((x2 - x1 + 1L) * (y2 - y1 + 1L), 0L);
    }
}