class Solution {
    private static final char O = 'O';
    private static final char X = 'X';

    public int solution(String[] board) {
        int i;
        int j;
        int lO;
        int lX;
        int rO;
        int rX;
        int rowO;
        int rowX;
        int colO;
        int colX;
        int cntO;
        int cntX;
        boolean winO;
        boolean winX;

        lO = 0;
        lX = 0;
        rO = 0;
        rX = 0;
        cntO = 0;
        cntX = 0;
        winO = false;
        winX = false;
        for (i = 0; i < 3; i++) {
            rowO = 0;
            rowX = 0;
            colO = 0;
            colX = 0;
            for (j = 0; j < 3; j++) {
                if (board[i].charAt(j) == O) {
                    rowO++;
                } else if (board[i].charAt(j) == X) {
                    rowX++;
                }
                if (board[j].charAt(i) == O) {
                    colO++;
                } else if (board[j].charAt(i) == X) {
                    colX++;
                }
            }
            if (board[i].charAt(i) == O) {
                lO++;
            } else if (board[i].charAt(i) == X) {
                lX++;
            }
            if (board[i].charAt(2 - i) == O) {
                rO++;
            } else if (board[i].charAt(2 - i) == X) {
                rX++;
            }
            cntO += rowO;
            cntX += rowX;
            winO |= rowO == 3 || colO == 3;
            winX |= rowX == 3 || colX == 3;
        }
        winO |= lO == 3 || rO == 3;
        winX |= lX == 3 || rX == 3;
        return (cntO == cntX && !winO) || (cntO - cntX == 1 && !winX) ? 1 : 0;
    }
}
