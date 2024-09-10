import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int INF = Integer.MAX_VALUE;

    private static int level;
    private static int start;
    private static int dest;
    private static int end;
    private static int jumped;
    private static int[] map;
    private static int[] d;
    private static boolean[][] visited;

    private static boolean dfs(int pos) {
        int i;
        int npos;

        if (visited[pos][jumped]) {
            return false;
        }
        visited[pos][jumped] = true;
        if (map[pos] > level) {
            return false;
        }
        if (pos == dest) {
            return true;
        }
        for (i = 0; i < 4; i++) {
            npos = pos + d[i];
            if (jumped == 0 && map[npos] > level) {
                jumped++;
                if (dfs(npos + d[i])) {
                    jumped--;
                    return true;
                }
                jumped--;
            } else {
                if (dfs(npos)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int lowerBound(int right) {
        int left;

        jumped = 0;
        left = Math.max(map[start], map[dest]);
        while (left < right) {
            level = left + right >> 1;
            visited = new boolean[end][2];
            if (dfs(start)) {
                right = level;
            } else {
                left = level + 1;
            }
        }
        return right;
    }

    public static void main(String[] args) throws IOException {
        int n;
        int m;
        int i;
        int j;
        int max;
        int size;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken()) + 1;
        m = Integer.parseInt(st.nextToken()) + 1;
        size = m + 3;
        start = 2 * size + 2;
        dest = n * size + m;
        end = (n + 3) * size;
        map = new int[end];
        d = new int[] {-size, 1, size, -1};
        for (i = 2; i <= m; i++) {
            map[i] = INF;
        }
        System.arraycopy(map, 2, map, size + 2, m - 1);
        System.arraycopy(map, 2, map, (n + 1) * size + 2, (size << 1) - 4);
        max = 0;
        for (i = 2 * size; i <= dest; i += size) {
            map[i] = INF;
            map[i + 1] = INF;
            st = new StringTokenizer(br.readLine(), " ", false);
            for (j = 2; j <= m; j++) {
                map[i + j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i + j]);
            }
            map[i + m + 1] = INF;
            map[i + m + 2] = INF;
        }
        System.out.print(lowerBound(max));
    }
}
