import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int WALL = -1;
    private static final byte TRUE = 1;
    private static final byte FALSE = 0;
    private static final byte FAIL = -1;

    private static int[] d;
    private static int[] map;
    private static byte[] visited;

    private static final boolean dfs(int pos) {
        int i;
        int npos;

        visited[pos] = TRUE;
        for (i = 0; i < 8; i++) {
            npos = pos + d[i];
            if (map[npos] < map[pos]) {
                visited[npos] = FAIL;
                continue;
            } else if (map[npos] == map[pos]) {
                if (visited[npos] == FALSE && dfs(npos)) {
                    continue;
                } else if (visited[npos] == TRUE) {
                    continue;
                }
            }
            visited[pos] = FAIL;
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        int n;
        int m;
        int i;
        int j;
        int col;
        int thr;
        int cnt;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        col = m + 2;
        thr = (n + 1) * col;
        map = new int[(n + 2) * col];
        for (i = 0; i < col; i++) {
            map[i] = WALL;
        }
        for (i = col; i < thr; i += col) {
            map[i] = WALL;
            st = new StringTokenizer(br.readLine(), " ", false);
            for (j = 1; j <= m; j++) {
                map[i + j] = Integer.parseInt(st.nextToken());
            }
            map[i + m + 1] = WALL;
        }
        System.arraycopy(map, 0, map, thr, col);
        visited = new byte[(n + 2) * col];
        d = new int[] {-col, -col + 1, 1, col + 1, col, col - 1, -1, -col - 1};
        cnt = 0;
        for (i = col; i < thr; i += col) {
            for (j = 1; j <= m; j++) {
                if (visited[i + j] == FALSE && dfs(i + j)) {
                    cnt++;
                }
            }
        }
        System.out.print(cnt);
    }
}
