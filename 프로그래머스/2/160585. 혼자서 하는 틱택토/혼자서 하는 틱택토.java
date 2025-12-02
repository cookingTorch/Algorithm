class Solution {
    private static final int O = 'O';
    private static final int X = 'X';
    private static final int SIZE = 3;

    public int solution(String[] board) {
        int i;
        int j;
        int ch;
        int cnt;
        boolean lO;
        boolean lX;
        boolean rO;
        boolean rX;
        boolean rowO;
        boolean rowX;
        boolean colO;
        boolean colX;
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
            rowO = true;
            rowX = true;
            colO = true;
            colX = true;
            for (j = 0; j < SIZE; j++) {
                ch = board[i].charAt(j);
                rowO &= ch == O;
                rowX &= ch == X;
                ch = board[j].charAt(i);
                colO &= ch == O;
                colX &= ch == X;
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
            winO |= rowO || colO;
            winX |= rowX || colX;
        }
        winO |= lO || rO;
        winX |= lX || rX;
        return (cnt == 0 && !winO) || (cnt == 1 && !winX) ? 1 : 0;
    }
}
