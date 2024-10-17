import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int SHIFT = 18;
    private static final long MOD = (1 << SHIFT) - 1;
    private static final long INF = Long.MAX_VALUE;
    private static final char LINE_BREAK = '\n';
    private static final char[] EMPTY = {'0', LINE_BREAK};

    private static int idx;
    private static long[] tree;

    private static final void init(int n) {
        int size;

        for (idx = 1; idx < n; idx <<= 1);
        tree = new long[size = idx << 1];
        while (size-- > 1) {
            tree[size] = INF;
        }
    }

    private static final void add(long val) {
        int i;

        i = idx++;
        tree[i] = val << SHIFT | i;
        for (i >>= 1; i > 0; i >>= 1) {
            tree[i] = Math.min(tree[i << 1], tree[i << 1 | 1]);
        }
    }

    private static final void remove() {
        int i;

        i = (int) (tree[1] & MOD);
        tree[i] = INF;
        for (i >>= 1; i > 0; i >>= 1) {
            tree[i] = Math.min(tree[i << 1], tree[i << 1 | 1]);
        }
    }

    public static void main(String[] args) throws IOException {
        int n;
        int num;
        StringBuilder sb;
        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        init(n);
        sb = new StringBuilder();
        while (n-- > 0) {
            num = Integer.parseInt(br.readLine());
            if (num == 0) {
                if (tree[1] == INF) {
                    sb.append(EMPTY);
                } else {
                    sb.append((int) (tree[1] >> SHIFT)).append(LINE_BREAK);
                    remove();
                }
            } else {
                add(num);
            }
        }
        System.out.print(sb.toString());
    }
}
