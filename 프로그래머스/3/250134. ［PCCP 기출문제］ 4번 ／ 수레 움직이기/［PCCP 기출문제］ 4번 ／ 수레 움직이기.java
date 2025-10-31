import java.util.ArrayDeque;

class Solution {
    private static final int FAIL = 0;

    private static int bfs(long sr, long sb, long er, long eb, long wall, int c) {
        int i;
        int j;
        int[] d;
        long r;
        long b;
        long nr;
        long nb;
        long vr;
        long vb;
        long dist;
        long[] cur;
        ArrayDeque<long[]> q;

        d = new int[] {-c, 1, c, -1, 0};
        q = new ArrayDeque<>();
        q.add(new long[] {sr, sb, wall, wall, 0});
        while (!q.isEmpty()) {
            cur = q.pollFirst();
            r = cur[0];
            b = cur[1];
            vr = r == er ? cur[2] : cur[2] | r;
            vb = b == eb ? cur[3] : cur[3] | b;
            dist = cur[4] + 1L;
            for (i = 0; i < 4; i++) {
                if (r == er) {
                    i = 4;
                }
                nr = d[i] < 0 ? r >> -d[i] : r << d[i];
                if ((nr & vr) != 0) {
                    continue;
                }
                for (j = 0; j < 4; j++) {
                    if (b == eb) {
                        j = 4;
                    }
                    nb = d[j] < 0 ? b >> -d[j] : b << d[j];
                    if ((nb & vb) != 0 || (nr == nb) || (nr == b && nb == r)) {
                        continue;
                    }
                    if (nr == er && nb == eb) {
                        return (int) dist;
                    }
                    q.addLast(new long[] {nr, nb, vr, vb, dist});
                }
            }
        }
        return FAIL;
    }

    public int solution(int[][] maze) {
        int i;
        int j;
        int n;
        int m;
        int c;
        long sr;
        long sb;
        long er;
        long eb;
        long side;
        long wall;

        n = maze.length;
        m = maze[0].length;
        c = m + 2;
        side = 1L << m + 1 | 1L;
        wall = (1L << c) - 1L;
        for (i = 0; i < n; i++) {
            wall = wall << c | side;
        }
        wall = wall << c | (1L << c) - 1L;
        sr = sb = er = eb = 0;
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                switch (maze[i][j]) {
                    case 1:
                        sr = 1L << (i + 1) * c + j + 1;
                        break;
                    case 2:
                        sb = 1L << (i + 1) * c + j + 1;
                        break;
                    case 3:
                        er = 1L << (i + 1) * c + j + 1;
                        break;
                    case 4:
                        eb = 1L << (i + 1) * c + j + 1;
                        break;
                    case 5:
                        wall |= 1L << (i + 1) * c + j + 1;
                        break;
                    default:
                }
            }
        }
        return bfs(sr, sb, er, eb, wall, c);
    }
}