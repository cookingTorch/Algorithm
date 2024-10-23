import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int WALL = -1;

    private static int[] d;
    private static int[] map;
    private static boolean isPeak;
    private static boolean[] visited;

    private static final void dfs(int pos) {
        int i;
        int npos;

        visited[pos] = true;
        for (i = 0; i < 8; i++) {
            npos = pos + d[i];
            if (isPeak && map[npos] > map[pos]) {
                isPeak = false;
            } else if (map[npos] == map[pos] && !visited[npos]) {
                dfs(npos);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int n;
        int m;
        int i;
        int j;
        int col;
        int thr;
        int cnt;
        int size;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        col = m + 2;
        thr = (n + 1) * col;
        size = thr + col;
        map = new int[size];
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
        visited = new boolean[size];
        d = new int[] {-col, -col + 1, 1, col + 1, col, col - 1, -1, -col - 1};
        cnt = 0;
        for (i = col; i < thr; i += col) {
            for (j = 1; j <= m; j++) {
                if (!visited[i + j]) {
                    isPeak = true;
                    dfs(i + j);
                    if (isPeak) {
                        cnt++;
                    }
                }
            }
        }
        System.out.print(cnt);
    }
}
