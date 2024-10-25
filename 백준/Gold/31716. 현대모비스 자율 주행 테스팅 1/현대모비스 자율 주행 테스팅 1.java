import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final char OBSTACLE = '#';
    private static final char[] FAIL = {'-', '1'};

    public static void main(String[] args) throws IOException {
        int n;
        int i;
        int cnt;
        int add;
        int lane;
        int start;
        long k;
        char[][] map;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new char[2][n];
        br.read(map[0], 0, n);
        br.read();
        br.read(map[1], 0, n);
        if ((map[0][0] == OBSTACLE && map[1][0] == OBSTACLE) || (k > 1
                && ((map[0][n - 1] == OBSTACLE && map[1][0] == OBSTACLE)
                || (map[1][n - 1] == OBSTACLE && map[0][0] == OBSTACLE)))) {
            System.out.print(FAIL);
            return;
        }
        start = lane = 0;
        for (i = 0; i < n; i++) {
            if (map[0][i] == OBSTACLE) {
                start = lane = 1;
                break;
            } else if (map[1][i] == OBSTACLE) {
                start = lane = 0;
                break;
            }
        }
        if (i == n) {
            i--;
        }
        cnt = i;
        for (++i; i < n; i++) {
            if (map[lane][i] != OBSTACLE) {
                cnt++;
            } else if (map[lane ^= 1][i - 1] != OBSTACLE && map[lane][i] != OBSTACLE) {
                cnt += 2;
            } else {
                System.out.print(FAIL);
                return;
            }
        }
        if (lane == start) {
            add = 1;
        } else {
            add = 2;
        }
        System.out.print(cnt * k + add * (k - 1L));
    }
}
