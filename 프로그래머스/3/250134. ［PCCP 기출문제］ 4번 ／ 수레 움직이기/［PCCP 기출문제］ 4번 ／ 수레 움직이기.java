import java.util.ArrayDeque;

class Solution {
    private static final int EMPTY = 0;
    private static final int RED = 1;
    private static final int BLUE = 2;
    private static final int END_R = 3;
    private static final int END_B = 4;
    private static final int WALL = 5;

    private static final class Node {
        int cnt;
        int red;
        int blue;
        long visR;
        long visB;

        Node(int red, int blue, long visR, long visB, int cnt) {
            this.cnt = cnt;
            this.red = red;
            this.blue = blue;
            this.visR = visR;
            this.visB = visB;
        }
    }

    private static int bfs(int red, int blue, int endR, int endB, long map, int[] d) {
        int i;
        int j;
        int cnt;
        int nred;
        int nblue;
        long visR;
        long visB;
        Node node;
        ArrayDeque<Node> q;

        q = new ArrayDeque<>();
        q.addLast(new Node(red, blue, map | 1L << red, map | 1L << blue, 0));
        while (!q.isEmpty()) {
            node = q.pollFirst();
            red = node.red;
            blue = node.blue;
            visR = node.visR;
            visB = node.visB;
            cnt = node.cnt + 1;
            for (i = 0; i < 4; i++) {
                if (red == endR) {
                    i = 3;
                    nred = red;
                } else {
                    nred = red + d[i];
                    if ((visR & (1L << nred)) != 0L) {
                        continue;
                    }
                }
                for (j = 0; j < 4; j++) {
                    if (blue == endB) {
                        j = 3;
                        nblue = blue;
                    } else {
                        nblue = blue + d[j];
                        if ((visB & (1L << nblue)) != 0L || (nred == blue && nblue == red)) {
                            continue;
                        }
                    }
                    if (nred == nblue) {
                        continue;
                    }
                    if (nred == endR && nblue == endB) {
                        return cnt;
                    }
                    q.addLast(new Node(nred, nblue, visR | 1L << nred, visB | 1L << nblue, cnt));
                }
            }
        }
        return 0;
    }

    public int solution(int[][] maze) {
        int r;
        int c;
        int i;
        int j;
        int red;
        int blue;
        int endR;
        int endB;
        int size;
        long map;

        r = maze.length;
        c = maze[0].length;
        size = c + 2;
        red = 0;
        blue = 0;
        endR = 0;
        endB = 0;
        map = (1L << size) - 1L | (1L << size) - 1L << (r + 1) * size;
        for (i = 1; i <= r; i++) {
            map |= (1L << (size - 1) | 1L) << i * size;
            for (j = 1; j <= c; j++) {
                switch (maze[i - 1][j - 1]) {
                    case EMPTY:
                        break;
                    case RED:
                        red = i * size + j;
                        break;
                    case BLUE:
                        blue = i * size + j;
                        break;
                    case END_R:
                        endR = i * size + j;
                        break;
                    case END_B:
                        endB = i * size + j;
                        break;
                    case WALL:
                        map |= 1L << i * size + j;
                }
            }
        }
        return bfs(red, blue, endR, endB, map, new int[] {-size, 1, size, -1});
    }
}
