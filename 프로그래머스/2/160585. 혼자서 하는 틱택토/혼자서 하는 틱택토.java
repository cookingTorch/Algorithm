class Solution {
    private static final int O = 'O';
    private static final int X = 'X';
    private static final int SIZE = 3;

    public int solution(String[] board) {
        int i;
        int j;
        int ch;
        int cnt;
        boolean hO;
        boolean hX;
        boolean vO;
        boolean vX;
        boolean lO;
        boolean lX;
        boolean rO;
        boolean rX;
        boolean winO;
        boolean winX;

        cnt = 0;
        winO = false;
        winX = false;
        lO = true;
        lX = true;
        rO = true;
        rX = true;
        for (i = 0; i < SIZE; i++) {
            hO = true;
            hX = true;
            vO = true;
            vX = true;
            for (j = 0; j < SIZE; j++) {
                ch = board[i].charAt(j);
                hO &= ch == O;
                hX &= ch == X;
                ch = board[j].charAt(i);
                vO &= ch == O;
                vX &= ch == X;
                if (ch == O) {
                    cnt++;
                } else if (ch == X) {
                    cnt--;
                }
            }
            ch = board[i].charAt(i);
            lO &= ch == O;
            lX &= ch == X;
            ch = board[i].charAt(2 - i);
            rO &= ch == O;
            rX &= ch == X;
            winO |= hO || vO;
            winX |= hX || vX;
        }
        winO |= lO || rO;
        winX |= lX || rX;
        return (cnt == 0 && !winO) || (cnt == 1 && !winX) ? 1 : 0;
    }
}
