import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        int n;
        int m;
        int i;
        int j;
        int row;
        int col;
        int size;
        char ch;
        char[][] map;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][++m];
        for (i = 0; i < n; i++) {
            br.read(map[i], 0, m);
        }
        m--;
        for (size = Math.min(n, m) - 1; size >= 0; size--) {
            row = n - size;
            for (i = 0; i < row; i++) {
                col = m - size;
                for (j = 0; j < col; j++) {
                    ch = map[i][j];
                    if (map[i + size][j] == ch && map[i][j + size] == ch && map[i + size][j + size] == ch) {
                        System.out.print((size + 1) * (size + 1));
                        return;
                    }
                }
            }
        }
    }
}
