import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX = 100_000;
    private static final char LINE_FEED = '\n';
    private static final String DELIM = " ";

    private static final class Drink implements Comparable<Drink> {
        int d;
        long p;
        long l;

        Drink(int d, long p, long l) {
            this.d = d;
            this.p = p;
            this.l = l;
        }

        @Override
        public int compareTo(Drink o) {
            return o.d - this.d;
        }
    }

    private static final class Pbs {
        int left;
        int right;
        int ans;
        long g;
        long l;
        Pbs next;

        Pbs(long g, long l, Pbs next) {
            this.g = g;
            this.l = l;
            left = 0;
            right = n;
            this.next = next;
        }

        final void validate(int mid) {
            arr[mid] = next;
            if (lTree[1] >= l && query(1, 0, MAX, l) <= g) {
                right = mid;
            } else {
                left = mid + 1;
            }
            if (left >= right) {
                if (right == n) {
                    ans = -1;
                } else {
                    ans = drinks[right].d;
                }
                cnt++;
                return;
            }
            mid = left + right >>> 1;
            next = arr[mid];
            arr[mid] = this;
        }
    }

    private static int n;
    private static int cnt;
    private static long[] initTree;
    private static long[] gTree;
    private static long[] lTree;
    private static Pbs[] arr;
    private static Drink[] drinks;

    private static final void add(int node, int start, int end, Drink drink) {
        int mid;

        gTree[node] += drink.p * drink.l;
        lTree[node] += drink.l;
        if (start == end) {
            return;
        }
        mid = start + end >>> 1;
        if (drink.p <= mid) {
            add(node << 1, start, mid, drink);
        } else {
            add(node << 1 | 1, mid + 1, end, drink);
        }
    }

    private static final long query(int node, int start, int end, long l) {
        int mid;

        if (start == end) {
            return gTree[node] / lTree[node] * l;
        }
        mid = start + end >>> 1;
        if (lTree[node << 1] >= l) {
            return query(node << 1, start, mid, l);
        } else {
            return gTree[node << 1] + query(node << 1 | 1, mid + 1, end, l - lTree[node << 1]);
        }
    }

    public static void main(String[] args) throws IOException {
        int m;
        int i;
        int mid;
        Pbs[] ans;
        StringBuilder sb;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), DELIM, false);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        drinks = new Drink[n];
        for (i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), DELIM, false);
            drinks[i] = new Drink(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(drinks);
        arr = new Pbs[n];
        ans = new Pbs[m];
        mid = n >>> 1;
        for (i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), DELIM, false);
            arr[mid] = new Pbs(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()), arr[mid]);
            ans[i] = arr[mid];
        }
        initTree = new long[MAX << 2];
        gTree = new long[MAX << 2];
        lTree = new long[MAX << 2];
        cnt = 0;
        while (cnt != m) {
            System.arraycopy(initTree, 0, gTree, 0, MAX << 2);
            System.arraycopy(initTree, 0, lTree, 0, MAX << 2);
            for (i = 0; i < n; i++) {
                add(1, 0, MAX, drinks[i]);
                while (arr[i] != null) {
                    arr[i].validate(i);
                }
            }
        }
        sb = new StringBuilder();
        for (i = 0; i < m; i++) {
            sb.append(ans[i].ans).append(LINE_FEED);
        }
        System.out.print(sb.toString());
    }
}
