import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int FAIL = -1;

    private static int p;
    private static int thr;
    private static int[][][][][] dp;

    private static int dfs(int depth, int prev, int num) {
        int max;

        if (num > thr) {
            return FAIL;
        }
        if (depth == p) {
            return num;
        }
        depth++;
        max = FAIL;
        for (; prev > 1; prev--) {
            max = Math.max(max, dfs(depth, prev, num * prev));
        }
        return max;
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
        System.out.print(dfs(0, 9, 1));
    }
}
