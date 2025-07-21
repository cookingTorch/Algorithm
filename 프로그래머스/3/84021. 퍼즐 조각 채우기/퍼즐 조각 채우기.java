import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class Solution {
    private static final int INF = Integer.MAX_VALUE;
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static int c;
    private static int[][] board;
    private static ArrayList<int[]> block;

    private static ArrayList<Integer> genKey() {
        int x;
        int y;
        int i;
        int len;
        int tmp;
        int[] pos;
        ArrayList<Integer> key;

        len = block.size();
        x = INF;
        y = INF;
        for (i = 0; i < len; i++) {
            pos = block.get(i);
            tmp = pos[0];
            pos[0] = pos[1];
            pos[1] = -tmp;
            x = Math.min(x, pos[0]);
            y = Math.min(y, pos[1]);
        }
        key = new ArrayList<>();
        for (i = 0; i < len; i++) {
            pos = block.get(i);
            key.add((pos[0] - x) * c + pos[1] - y);
        }
        Collections.sort(key);
        return key;
    }

    private static void dfs(int x, int y, int num) {
        int i;
        int nx;
        int ny;

        board[x][y] ^= 1;
        block.add(new int[] {x, y});
        for (i = 0; i < 4; i++) {
            nx = x + dx[i];
            ny = y + dy[i];
            if (nx < 0 || nx >= c || ny < 0 || ny >= c || board[nx][ny] != num) {
                continue;
            }
            dfs(nx, ny, num);
        }
    }

    public int solution(int[][] game_board, int[][] table) {
        int i;
        int j;
        int k;
        int cnt;
        int slots;
        ArrayList<Integer> key;
        HashMap<ArrayList<Integer>, Integer> map;

        c = game_board.length; // 행 = 열
        board = game_board;
        block = new ArrayList<>();
        map = new HashMap<>();
        for (i = 0; i < c; i++) {
            for (j = 0; j < c; j++) {
                if (game_board[i][j] == 0) {
                    block.clear();
                    dfs(i, j, 0);
                    key = genKey();
                    map.put(key, map.getOrDefault(key, 0) + 1);
                }
            }
        }
        board = table;
        cnt = 0;
        for (i = 0; i < c; i++) {
            for (j = 0; j < c; j++) {
                if (table[i][j] == 1) {
                    block.clear();
                    dfs(i, j, 1);
                    for (k = 0; k < 4; k++) {
                        key = genKey();
                        slots = map.getOrDefault(key, 0);
                        if (slots != 0) {
                            cnt += block.size();
                            if (--slots == 0) {
                                map.remove(key);
                            } else {
                                map.put(key, slots);
                            }
                            break;
                        }
                    }
                }
            }
        }
        return cnt;
    }
}