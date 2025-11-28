class Solution {
    private static final int SIZE = 101;

    private static int ans;
    private static int time;
    private static int[][] point;

    private static final class Robot {
        private static int[][] map = new int[SIZE][SIZE];
        private static int[][] visited = new int[SIZE][SIZE];

        int x;
        int y;
        int ex;
        int ey;
        int idx;
        int len;
        int[] route;

        Robot(int[] route) {
            x = point[route[0] - 1][0];
            y = point[route[0] - 1][1];
            ex = point[route[1] - 1][0];
            ey = point[route[1] - 1][1];
            idx = 1;
            len = route.length;
            this.route = route;
        }

        boolean move() {
            if (map[x][y] == time) {
                if (visited[x][y] != time) {
                    ans++;
                    visited[x][y] = time;
                }
            } else {
                map[x][y] = time;
            }
            if (x == ex && y == ey) {
                if (++idx == len) {
                    return true;
                }
                ex = point[route[idx] - 1][0];
                ey = point[route[idx] - 1][1];
            }
            if (x < ex) {
                x++;
            } else if (x > ex) {
                x--;
            } else if (y < ey) {
                y++;
            } else if (y > ey) {
                y--;
            }
            return false;
        }
    }

    public int solution(int[][] points, int[][] routes) {
        int i;
        int len;
        int cnt;
        Robot[] robots;

        ans = 0;
        point = points;
        len = routes.length;
        robots = new Robot[len];
        for (i = 0; i < len; i++) {
            robots[i] = new Robot(routes[i]);
        }
        for (time = 1, cnt = len; cnt > 1; time++) {
            for (i = 0; i < len; i++) {
                if (robots[i] != null && robots[i].move()) {
                    cnt--;
                    robots[i] = null;
                }
            }
        }
        return ans;
    }
}
