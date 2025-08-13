import java.util.PriorityQueue;

class Solution {
    private static final int L = 'L';
    private static final int R = 'R';
    private static final int LEFT = 3;
    private static final int RIGHT = 1;
    private static final int STRAIGHT = 0;
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static int r;
    private static int c;

    public int[] solution(String[] grid) {
        int x;
        int y;
        int i;
        int j;
        int k;
        int dir;
        int size;
        int[] ans;
        int[][] map;
        boolean[][][] visited;
        PriorityQueue<Integer> pq;

        r = grid.length;
        c = grid[0].length();
        map = new int[r][c];
        for (i = 0; i < r; i++) {
            for (j = 0; j < c; j++) {
                map[i][j] = switch (grid[i].charAt(j)) {
                    case L -> LEFT;
                    case R -> RIGHT;
                    default -> STRAIGHT;
                };
            }
        }
        pq = new PriorityQueue<>();
        visited = new boolean[r][c][4];
        for (i = 0; i < r; i++) {
            for (j = 0; j < c; j++) {
                for (k = 0; k < 4; k++) {
                    if (visited[i][j][k]) {
                        continue;
                    }
                    x = i;
                    y = j;
                    dir = k;
                    size = 0;
                    do {
                        visited[x][y][dir] = true;
                        size++;
                        x = (x + dx[dir] + r) % r;
                        y = (y + dy[dir] + c) % c;
                        dir = (dir + map[x][y]) & 3;
                    } while (!visited[x][y][dir]);
                    pq.offer(size);
                }
            }
        }
        size = pq.size();
        ans = new int[size];
        for (i = 0; i < size; i++) {
            ans[i] = pq.poll();
        }
        return ans;
    }
}
