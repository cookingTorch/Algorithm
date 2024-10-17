import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int WALL = -1;
    private static final int APPLE = 1;
    private static final int EMPTY = 0;
    private static final int LEFT_TURN = 3;
    private static final int RIGHT_TURN = 1;
    private static final int MAX_TIME = 10_100;
    private static final char LEFT = 'L';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n;
        int k;
        int x;
        int l;
        int i;
        int dir;
        int max;
        int cnt;
        int pos;
        int col;
        int end;
        int time;
        int head;
        int tail;
        int turn;
        int[] d;
        int[] map;
        int[] snake;

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        col = n + 2;
        end = n + 1;
        max = col * col;
        d = new int[] {-col, 1, col, -1};
        map = new int[max];
        for (i = 1; i <= n; i++) {
            map[i] = WALL;
            map[end * col + i] = WALL;
            map[i * col] = WALL;
            map[i * col + end] = WALL;
        }
        for (i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken()) * col + Integer.parseInt(st.nextToken())] = APPLE;
        }
        dir = 1;
        l = Integer.parseInt(br.readLine());
        cnt = 1;
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        if (st.nextToken().charAt(0) == LEFT) {
            turn = LEFT_TURN;
        } else {
            turn = RIGHT_TURN;
        }
        snake = new int[MAX_TIME];
        head = 0;
        tail = 0;
        pos = col + 1;
        snake[head] = pos;
        for (time = 1;; time++) {
            pos += d[dir];
            switch (map[pos]) {
                case WALL:
                    System.out.print(time);
                    return;
                case EMPTY:
                    map[snake[tail++]] = EMPTY;
                case APPLE:
                    map[pos] = WALL;
                    snake[++head] = pos;
            }
            if (time == x) {
                dir = dir + turn & 3;
                if (cnt++ == l) {
                    x = MAX_TIME;
                } else {
                    st = new StringTokenizer(br.readLine());
                    x = Integer.parseInt(st.nextToken());
                    if (st.nextToken().charAt(0) == LEFT) {
                        turn = LEFT_TURN;
                    } else {
                        turn = RIGHT_TURN;
                    }
                }
            }
        }
    }
}
