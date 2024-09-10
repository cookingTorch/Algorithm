import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int FAIL = -1;

    private static int p;
    private static int thr;
    private static int max;

    private static void dfs(int depth, int prev, int num) {
        if (num > thr) {
            return;
        }
        if (depth == p) {
            if (num > max) {
                max = num;
            }
            return;
        }
        depth++;
        for (; prev > 1; prev--) {
            dfs(depth, prev, num * prev);
        }
    }

    public static void main(String[] args) throws IOException {
        int d;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        d = Integer.parseInt(st.nextToken());
        thr = (int) Math.pow(10.0, d) - 1;
        p = Integer.parseInt(st.nextToken());
        max = FAIL;
        dfs(0, 9, 1);
        System.out.print(max);
    }
}
