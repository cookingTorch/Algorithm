import java.util.ArrayDeque;

class Solution {
    private static final int EMPTY = 0;
    
    private static int c;
    private static int cnt;
    private static int[][] map;
    private static boolean end;
    private static boolean[][] delete;
    private static ArrayDeque<Integer> q;
    
    private static void init(int n, int m, String[] board) {
        int i;
        int j;

        cnt = 0;
        map = new int[n][m];
        delete = new boolean[n][m];
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                map[i][j] = board[c - j].charAt(i);
            }
        }
        q = new ArrayDeque<>(m);
    }
    
    private static boolean find(int i, int j) {
        int ch;

        ch = map[i][j];
        if (ch == EMPTY) {
            return true;
        }
        if (map[i][j + 1] == ch && map[i + 1][j] == ch && map[i + 1][j + 1] == ch) {
            delete[i][j] = true;
            delete[i][j + 1] = true;
            delete[i + 1][j] = true;
            delete[i + 1][j + 1] = true;
            end = false;
        }
        return false;
    }
    
    private static void move(int[] map, boolean[] delete) {
        int i;
        
        for (i = 0; i <= c; i++) {
            if (map[i] == EMPTY) {
                break;
            }
            if (delete[i]) {
                cnt++;
                delete[i] = false;
            } else {
                q.addLast(map[i]);
            }
        }
        for (i = 0; !q.isEmpty(); i++) {
            map[i] = q.pollFirst();
        }
        for (; i <= c; i++) {
            map[i] = EMPTY;
        }
    }

    public int solution(int m, int n, String[] board) {
        int r;
        int i;
        int j;

        r = n - 1;
        c = m - 1;
        init(n, m, board);
        end = false;
        while (!end) {
            end = true;
            for (i = 0; i < r; i++) {
                for (j = 0; j < c; j++) {
                    if (find(i, j)) {
                        break;
                    }
                }
            }
            for (i = 0; i < n; i++) {
                move(map[i], delete[i]);
            }
        }
        return cnt;
    }
}
