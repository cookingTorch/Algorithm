class Solution {
    private static final int A = 0;
    private static final int B = 1;
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    
    private static int r, c;
    private static int[][] board;
    
    private static final int[] dfsA(int depth, int ax, int ay, int bx, int by) {
        int nx, ny, win, num, i;
        int[] result;
        boolean flag;
        
        flag = false;
        win = B;
        num = depth;
        for (i = 0; i < 4; i++) {
            nx = ax + dx[i];
            ny = ay + dy[i];
            if (nx < 0 || nx >= r || ny < 0 || ny >= c || board[nx][ny] == 0) {
                continue;
            }
            flag = true;
            if (ax == bx && ay == by) {
                return new int[] {depth + 1, A};
            }
            board[ax][ay] = 0;
            result = dfsB(depth + 1, nx, ny, bx, by);
            board[ax][ay] = 1;
            if (win == A) {
                if (result[1] == B) {
                    continue;
                }
                num = Math.min(num, result[0]);
            } else {
                if (result[1] == A) {
                    win = A;
                    num = result[0];
                    continue;
                }
                num = Math.max(num, result[0]);
            }
        }
        if (!flag) {
            return new int[] {depth, win};
        }
        return new int[] {num, win};
    }
    
    private static final int[] dfsB(int depth, int ax, int ay, int bx, int by) {
        int nx, ny, win, num, i;
        int[] result;
        boolean flag;
        
        flag = false;
        win = A;
        num = depth;
        for (i = 0; i < 4; i++) {
            nx = bx + dx[i];
            ny = by + dy[i];
            if (nx < 0 || nx >= r || ny < 0 || ny >= c || board[nx][ny] == 0) {
                continue;
            }
            flag = true;
            if (bx == ax && by == ay) {
                return new int[] {depth + 1, B};
            }
            board[bx][by] = 0;
            result = dfsA(depth + 1, ax, ay, nx, ny);
            board[bx][by] = 1;
            if (win == B) {
                if (result[1] == A) {
                    continue;
                }
                num = Math.min(num, result[0]);
            } else {
                if (result[1] == B) {
                    win = B;
                    num = result[0];
                    continue;
                }
                num = Math.max(num, result[0]);
            }
        }
        if (!flag) {
            return new int[] {depth, win};
        }
        return new int[] {num, win};
    }
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        this.board = board;
        r = board.length;
        c = board[0].length;
        return dfsA(0, aloc[0], aloc[1], bloc[0], bloc[1])[0];
    }
}