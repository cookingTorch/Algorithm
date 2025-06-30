class Solution {
    private static final char P = 'P';
    private static final char X = 'X';
    private static final int SIZE = 5;
    private static final int MAX = SIZE + 2;
    private static final int TRUE = 1;
    private static final int FALSE = 0;
    private static final int[] dx1 = {-1, 0, 1, 0};
    private static final int[] dy1 = {0, 1, 0, -1};
    private static final int[][] dx2 = {{-1, -2, -1}, {-1, 0, 1}, {1, 2, 1}, {-1, 0, 1}};
    private static final int[][] dy2 = {{-1, 0, 1}, {1, 2, 1}, {-1, 0, 1}, {-1, -2, -1}};

    private static char[][] map;

    private static boolean validate(int x, int y) {
        int i;
        int j;
        char ch;

        for (i = 0; i < 4; i++) {
            ch = map[x + dx1[i]][y + dy1[i]];
            if (ch == X) {
                continue;
            }
            if (ch == P) {
                return false;
            }
            for (j = 0; j < 3; j++) {
                if (map[x + dx2[i][j]][y + dy2[i][j]] == P) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[] solution(String[][] places) {
        int i;
        int j;
        int k;
        int len;
        int[] ans;
        String[] place;

        len = places.length;
        ans = new int[len];
        map = new char[SIZE + 4][SIZE + 4];
        loop:
        for (i = 0; i < len; i++) {
            place = places[i];
            for (j = 0; j < SIZE; j++) {
                place[j].getChars(0, SIZE, map[j + 2], 2);
            }
            ans[i] = TRUE;
            for (j = 2; j <= MAX; j++) {
                for (k = 2; k <= MAX; k++) {
                    if (map[j][k] == P) {
                        if (!validate(j, k)) {
                            ans[i] = FALSE;
                            continue loop;
                        }
                    }
                }
            }
        }
        return ans;
    }
}
