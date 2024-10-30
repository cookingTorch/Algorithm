import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int ONE = '1';
    private static final char SPACE = ' ';

    private static final String getPermutation(int n, long rank) {
        int i;
        int idx;
        int leaf;
        int size;
        int[] tree;
        long fact;
        StringBuilder sb;

        sb = new StringBuilder();
        for (fact = i = 1; i < n; fact *= i++);
        for (leaf = 1; leaf < n; leaf <<= 1);
        tree = new int[leaf << 1];
        size = leaf + n;
        for (i = leaf; i < size; i++) {
            tree[i] = 1;
        }
        for (i = leaf - 1; i > 0; i--) {
            tree[i] = tree[i << 1] + tree[i << 1 | 1];
        }
        rank--;
        while (--n > 0) {
            idx = (int) (rank / fact) + 1;
            for (i = 1; i < leaf;) {
                i <<= 1;
                if (tree[i] < idx) {
                    idx -= tree[i];
                    i |= 1;
                }
            }
            sb.append(i - leaf + 1).append(SPACE);
            for (; i > 0; i >>= 1) {
                tree[i]--;
            }
            rank %= fact;
            fact /= n;
        }
        for (i = 1; i < leaf;) {
            i <<= 1;
            if (tree[i] == 0) {
                i |= 1;
            }
        }
        sb.append(i - leaf + 1).append(SPACE);
        return sb.toString();
    }

    private static final long getRank(int n, StringTokenizer st) {
        int i;
        int cnt;
        int num;
        int size;
        int[] ft;
        long rank;
        long fact;

        rank = 1L;
        for (fact = i = 1; i < n; fact *= i++);
        for (size = 1; size < n; size <<= 1);
        ft = new int[++size];
        while (--n > 0) {
            num = Integer.parseInt(st.nextToken());
            for (cnt = 0, i = num - 1; i != 0; cnt += ft[i], i -= i & -i);
            rank += (num - 1 - cnt) * fact;
            for (i = num; i < size; ft[i]++, i += i & -i);
            fact /= n;
        }
        return rank;
    }

    public static void main(String[] args) throws IOException {
        int n;
        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        if (br.read() == ONE) {
            br.read();
            System.out.print(getPermutation(n, Long.parseLong(br.readLine())));
        } else {
            br.read();
            System.out.print(getRank(n, new StringTokenizer(br.readLine())));
        }
    }
}
