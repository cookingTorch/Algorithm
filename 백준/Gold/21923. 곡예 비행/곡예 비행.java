import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int MIN = Integer.MIN_VALUE >> 1;

    public static void main(String[] args) throws IOException {
        int n;
        int m;
        int i;
        int j;
        int max;
        int[][] up;
        int[][] map;
        int[][] down;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m + 1];
        for (i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ", false);
            for (j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        up = new int[n + 1][m + 1];
        down = new int[n + 1][m + 2];
        if (m > 1) {
            up[n][m] = MIN;
            down[n][1] = MIN;
        }
        for (i = 2; i < m; i++) {
            up[n][i] = MIN;
            down[n][i] = MIN;
        }
        for (i = n - 1; i >= 0; i--) {
            up[i][0] = MIN;
            for (j = 1; j <= m; j++) {
                up[i][j] = Math.max(up[i + 1][j], up[i][j - 1]) + map[i][j];
            }
        }
        max = MIN;
        for (i = n - 1; i >= 0; i--) {
            down[i][m + 1] = MIN;
            for (j = m; j > 0; j--) {
                down[i][j] = Math.max(down[i + 1][j], down[i][j + 1]) + map[i][j];
                max = Math.max(max, up[i][j] + down[i][j]);
            }
        }
        System.out.print(max);
    }
}
