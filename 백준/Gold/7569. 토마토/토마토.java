import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    private static final int FAIL = -1;
    private static final int RIPE = 2;
    private static final int UNRIPE = 1;

    private static int cnt;
    private static int dimension;
    private static int[] arr;
    private static int[] delta;
    private static int[] map;
    private static ArrayDeque<Integer> q;
    private static BufferedReader br;

    private static void input(int pos, int depth) throws IOException {
        int i;
        StringTokenizer st;

        if (depth == dimension - 1) {
            st = new StringTokenizer(br.readLine());
            for (i = 0; i < arr[depth]; i++) {
                map[pos] = Integer.parseInt(st.nextToken());
                if (++map[pos] == RIPE) {
                    q.addLast(pos);
                } else if (map[pos] == UNRIPE) {
                    cnt++;
                }
                pos += delta[depth];
            }
            return;
        }
        for (i = 0; i < arr[depth]; i++) {
            input(pos, depth + 1);
            pos += delta[depth];
        }
    }

    private static int bfs() {
        int i;
        int d;
        int pos;
        int size;
        int next;
        int time;

        for (time = 0; cnt > 0 && !q.isEmpty(); time++) {
            size = q.size();
            for (i = 0; i < size; i++) {
                pos = q.pollFirst();
                for (d = 0; d < dimension; d++) {
                    if ((d == 0 ? pos : pos % delta[d - 1]) / delta[d] > 0) {
                        next = pos - delta[d];
                        if (map[next] == UNRIPE) {
                            q.addLast(next);
                            map[next]++;
                            cnt--;
                        }
                    }
                    if ((d == 0 ? pos : pos % delta[d - 1]) / delta[d] < arr[d] - 1) {
                        next = pos + delta[d];
                        if (map[next] == UNRIPE) {
                            q.addLast(next);
                            map[next]++;
                            cnt--;
                        }
                    }
                }
            }
        }
        return cnt == 0 ? time : FAIL;
    }

    public static void main(String[] args) throws IOException {
        int i;
        int size;
        StringTokenizer st;
        ArrayDeque<Integer> stack;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        stack = new ArrayDeque<>();
        while (st.hasMoreTokens()) {
            stack.addFirst(Integer.parseInt(st.nextToken()));
        }
        dimension = stack.size();
        arr = new int[dimension];
        size = 1;
        for (i = 0; i < dimension; i++) {
            arr[i] = stack.pollFirst();
            size *= arr[i];
        }
        delta = new int[dimension];
        delta[dimension - 1] = 1;
        for (i = dimension - 1; i > 0; i--) {
            delta[i - 1] = delta[i] * arr[i];
        }
        map = new int[size];
        cnt = 0;
        q = new ArrayDeque<>();
        input(0, 0);
        System.out.print(bfs());
    }
}
