import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final char LINE_BREAK = '\n';
    private static final String NIE = "NIE\n";
    private static final String DELIM = " ";

    private static final class Fenwick {
        private final int n;
        private final long[] tree;
        private final long[] initTree;

        Fenwick(int n) {
            this.n = n;
            initTree = new long[n + 1];
            tree = new long[n + 1];
        }

        final void init() {
            System.arraycopy(initTree, 1, tree, 1, n);
        }

        final void updateRange(int l, int r, long val) {
            int i;

            for (i = l; i <= n; i += i & -i) {
                tree[i] += val;
            }
            if (r < n) {
                for (i = r + 1; i <= n; i += i & -i) {
                    tree[i] -= val;
                }
            }
        }

        final long query(int i) {
            long sum;

            sum = 0L;
            for (; i > 0; i -= i & -i) {
                sum += tree[i];
            }
            return sum;
        }
    }

    private static final class Area {
        int idx;
        Area next;

        Area(int idx, Area next) {
            this.idx = idx;
            this.next = next;
        }
    }

    private static final class Query {
        int l;
        int r;
        long a;

        Query(int l, int r, long a) {
            this.l = l;
            this.r = r;
            this.a = a;
        }

        void run() {
            if (l <= r) {
                ft.updateRange(l, r, a);
            } else {
                ft.updateRange(l, m, a);
                ft.updateRange(1, r, a);
            }
        }
    }

    private static final class Pbs {
        int left;
        int right;
        int ans;
        long p;
        Pbs next;
        Area areas;

        Pbs(long p, Pbs next) {
            this.p = p;
            this.next = next;
            left = 1;
            right = q + 1;
        }

        void validate(int mid) {
            long sum;
            Area area;

            sum = 0L;
            for (area = areas; area != null; area = area.next) {
                sum += ft.query(area.idx);
                if (sum >= p) {
                    break;
                }
            }
            arr[mid] = next;
            if (sum < p) {
                left = mid + 1;
            } else {
                right = mid;
            }
            if (left >= right) {
                ans = right;
                cnt ++;
                return;
            }
            mid = left + right >>> 1;
            next = arr[mid];
            arr[mid] = this;
        }
    }

    private static int m;
    private static int q;
    private static int cnt;
    private static Fenwick ft;
    private static Pbs[] arr;

    public static void main(String[] args) throws IOException {
        int n;
        int o;
        int i;
        int mid;
        Pbs[] ans;
        Query[] queries;
        String ps;
        String areas;
        StringBuilder sb;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), DELIM, false);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        areas = br.readLine();
        ps = br.readLine();
        q = Integer.parseInt(br.readLine());
        queries = new Query[q + 1];
        for (i = 1; i <= q; i++) {
            st = new StringTokenizer(br.readLine(), DELIM, false);
            queries[i] = new Query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        arr = new Pbs[q + 1];
        ans = new Pbs[n + 1];
        mid = q + 2 >>> 1;
        st = new StringTokenizer(ps, DELIM, false);
        for (i = 1; i <= n; i++) {
            arr[mid] = new Pbs(Integer.parseInt(st.nextToken()), arr[mid]);
            ans[i] = arr[mid];
        }
        st = new StringTokenizer(areas, DELIM, false);
        for (i = 1; i <= m; i++) {
            o = Integer.parseInt(st.nextToken());
            ans[o].areas = new Area(i, ans[o].areas);
        }
        ft = new Fenwick(m);
        while (cnt != n) {
            ft.init();
            for (i = 1; i <= q; i++) {
                queries[i].run();
                while (arr[i] != null) {
                    arr[i].validate(i);
                }
            }
        }
        sb = new StringBuilder();
        for (i = 1; i <= n; i++) {
            if (ans[i].ans <= q) {
                sb.append(ans[i].ans).append(LINE_BREAK);
            } else {
                sb.append(NIE);
            }
        }
        System.out.println(sb.toString());
    }
}
