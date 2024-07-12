import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int SIZE = 30;
    private static final int[] dx = {-1, 1, 0, 0, 0, 0};
    private static final int[] dy = {0, 0, -1, 1, 0, 0};
    private static final int[] dz = {0, 0, 0, 0, -1, 1};
    private static final char END = 'E';
    private static final char WALL = '#';
    private static final char START = 'S';
    private static final String FAIL = "Trapped!\n";
    private static final String PREFIX = "Escaped in ";
    private static final String SUFFIX = " minute(s).\n";

    private static char[][][] map;
    private static boolean[][][] visited;
    private static BufferedReader br;

    private static int bfs(int sx, int sy, int sz, int ex, int ey, int ez) {
        int i;
        int x;
        int y;
        int z;
        int nx;
        int ny;
        int nz;
        int dist;
        int[] curr;
        ArrayDeque<int[]> q;

        q = new ArrayDeque<>();
        q.addLast(new int[] {sx, sy, sz, 0});
        visited[sx][sy][sz] = true;
        while (!q.isEmpty()) {
            curr = q.pollFirst();
            x = curr[0];
            y = curr[1];
            z = curr[2];
            dist = curr[3] + 1;
            for (i = 0; i < 6; i++) {
                nx = x + dx[i];
                ny = y + dy[i];
                nz = z + dz[i];
                if (map[nx][ny][nz] == WALL || visited[nx][ny][nz]) {
                    continue;
                }
                visited[nx][ny][nz] = true;
                if (nx == ex && ny == ey && nz == ez) {
                    return dist;
                } else {
                    q.addLast(new int[] {nx, ny, nz, dist});
                }
            }
        }
        return 0;
    }

    private static int solution(int l, int r, int c) throws IOException {
        int i;
        int j;
        int k;
        int sx;
        int sy;
        int sz;
        int ex;
        int ey;
        int ez;
        String str;

        for (i = 1; i <= l; i++) {
            System.arraycopy(map[0][0], 0, map[i][r + 1], 0, c + 2);
            for (j = 1; j <= r; j++) {
                map[i][j][c + 1] = WALL;
                System.arraycopy(visited[0][0], 0, visited[i][j], 0, c + 2);
            }
        }
        for (i = 0; i <= r + 1; i++) {
            System.arraycopy(map[0][0], 0, map[l + 1][i], 0, c + 2);
        }
        sx = 0;
        sy = 0;
        sz = 0;
        ex = 0;
        ey = 0;
        ez = 0;
        for (i = 1; i <= l; i++) {
            for (j = 1; j <= r; j++) {
                str = br.readLine();
                for (k = 1; k <= c; k++) {
                    map[i][j][k] = str.charAt(k - 1);
                    if (map[i][j][k] == START) {
                        sx = i;
                        sy = j;
                        sz = k;
                    } else if (map[i][j][k] == END) {
                        ex = i;
                        ey = j;
                        ez = k;
                    }
                }
            }
            br.readLine();
        }
        return bfs(sx, sy, sz, ex, ey, ez);
    }

    public static void main(String[] args) throws IOException {
        int l;
        int r;
        int c;
        int i;
        int j;
        int ans;
        StringBuilder sb;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[SIZE + 2][SIZE + 2][SIZE + 2];
        Arrays.fill(map[0][0], WALL);
        Arrays.fill(map[0], map[0][0]);
        for (i = 1; i <= SIZE + 1; i++) {
            map[i][0] = map[0][0];
            for (j = 1; j <= SIZE + 1; j++) {
                map[i][j][0] = WALL;
            }
        }
        visited = new boolean[SIZE + 2][SIZE + 2][SIZE + 2];
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        while (l != 0) {
            ans = solution(l, r, c);
            if (ans == 0) {
                sb.append(FAIL);
            } else {
                sb.append(PREFIX).append(ans).append(SUFFIX);
            }
            st = new StringTokenizer(br.readLine());
            l = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
        }
        System.out.println(sb);
    }
}