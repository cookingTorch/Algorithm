import java.util.ArrayDeque;

class Solution {
    private static final int SIZE = 101;

    private static final class Robot {
        private static int[][] map;
        private static int[][] poses;
        private static int[][] visited;

        int x;
        int y;
        int nx;
        int ny;
        int idx;
        int size;
        int[] route;

        Robot(int[] route) {
            int[] pos;

            this.route = route;
            size = route.length;
            pos = poses[route[0] - 1];
            x = pos[0];
            y = pos[1];
            idx = 1;
            pos = poses[route[idx] - 1];
            nx = pos[0];
            ny = pos[1];
        }

        static void init(int[][] points) {
            poses = points;
            map = new int[SIZE][SIZE];
            visited = new int[SIZE][SIZE];
        }

        boolean move(int time) {
            int[] pos;

            if (map[x][y] == time && visited[x][y] != time) {
                cnt++;
                visited[x][y] = time;
            } else {
                map[x][y] = time;
            }
            if (x == nx && y == ny) {
                if (++idx == size) {
                    return false;
                }
                pos = poses[route[idx] - 1];
                nx = pos[0];
                ny = pos[1];
            }
            if (x < nx) {
                x++;
            } else if (x > nx) {
                x--;
            } else if (y < ny) {
                y++;
            } else if (y > ny) {
                y--;
            }
            return true;
        }
    }

    private static int cnt;

    public int solution(int[][] points, int[][] routes) {
        int i;
        int j;
        int size;
        Robot robot;
        ArrayDeque<Robot> dq;

        Robot.init(points);
        size = routes.length;
        dq = new ArrayDeque<>(size);
        for (i = 0; i < size; i++) {
            dq.addLast(new Robot(routes[i]));
        }
        cnt = 0;
        for (i = 1; !dq.isEmpty(); i++) {
            size = dq.size();
            for (j = 0; j < size; j++) {
                robot = dq.pollFirst();
                if (robot.move(i)) {
                    dq.addLast(robot);
                }
            }
        }
        return cnt;
    }
}
