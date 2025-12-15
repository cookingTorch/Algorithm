import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    private static final int DIFF = '0';
    private static final int[] FAIL = {-1};
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    private static final char SEA = 'X';

    private static int r;
    private static int c;
    private static int res;
    private static char[][] map;

    private static void dfs(int x, int y) {
        int i;
        int nx;
        int ny;

        res += map[x][y] - DIFF;
        map[x][y] = SEA;
        for (i = 0; i < 4; i++) {
            nx = x + dx[i];
            ny = y + dy[i];
            if (0 <= nx && nx < r && 0 <= ny && ny < c && map[nx][ny] != SEA) {
                dfs(nx, ny);
            }
        }
    }

    public int[] solution(String[] maps) {
        int i;
        int j;
        int len;
        int[] ans;
        ArrayList<Integer> list;

        r = maps.length;
        map = new char[r][];
        for (i = 0; i < r; i++) {
            map[i] = maps[i].toCharArray();
        }
        c = map[0].length;
        list = new ArrayList<>();
        for (i = 0; i < r; i++) {
            for (j = 0; j < c; j++) {
                if (map[i][j] != SEA) {
                    res = 0;
                    dfs(i, j);
                    list.add(res);
                }
            }
        }
        if ((len = list.size()) == 0) {
            return FAIL;
        }
        ans = new int[len];
        for (i = 0; i < len; i++) {
            ans[i] = list.get(i);
        }
        Arrays.sort(ans, 0, len);
        return ans;
    }
}
