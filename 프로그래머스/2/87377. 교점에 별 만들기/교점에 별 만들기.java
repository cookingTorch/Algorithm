import java.util.HashSet;

class Solution {
    private static final int MIN = Integer.MIN_VALUE;
    private static final long INF = Long.MAX_VALUE;
    private static final char DOT = '.';
    private static final char STAR = '*';

    private static final class Point {
        long x;
        long y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override public int hashCode() {
            return 31 * (int) (x ^ (x >>> 32)) + (int) (y ^ (y >>> 32));
        }

        @Override
        public boolean equals(Object o) {
            return x == ((Point) o).x && y == ((Point) o).y;
        }
    }

    public String[] solution(int[][] line) {
        int i;
        int j;
        int len;
        int maxX;
        int maxY;
        long a;
        long b;
        long c;
        long d;
        long e;
        long f;
        long x;
        long y;
        long div;
        long minX;
        long minY;
        char[][] res;
        Point[] arr;
        String[] ans;
        HashSet<Point> set;

        minX = INF;
        minY = INF;
        set = new HashSet<>();
        len = line.length;
        for (i = 0; i < len; i++) {
            a = line[i][0];
            b = line[i][1];
            e = line[i][2];
            for (j = i + 1; j < len; j++) {
                c = line[j][0];
                d = line[j][1];
                f = line[j][2];
                if ((div = a * d - b * c) == 0 || (x = b * f - e * d) % div != 0 || (y = e * c - a * f) % div != 0) {
                    continue;
                }
                minX = Math.min(minX, x /= div);
                minY = Math.min(minY, y /= div);
                set.add(new Point(x, y));
            }
        }
        len = set.size();
        arr = new Point[len];
        i = 0;
        for (Point p : set) {
            arr[i++] = p;
        }
        maxX = MIN;
        maxY = MIN;
        for (i = 0; i < len; i++) {
            maxX = Math.max(maxX, (int) (arr[i].x -= minX));
            maxY = Math.max(maxY, (int) (arr[i].y -= minY));
        }
        res = new char[maxY + 1][maxX + 1];
        for (i = 0; i <= maxY; i++) {
            for (j = 0; j <= maxX; j++) {
                res[i][j] = DOT;
            }
        }
        for (i = 0; i < len; i++) {
            res[maxY - (int) arr[i].y][(int) arr[i].x] = STAR;
        }
        ans = new String[maxY + 1];
        for (i = 0; i <= maxY; i++) {
            ans[i] = new String(res[i]);
        }
        return ans;
    }
}
