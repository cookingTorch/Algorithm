import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final char LINE_BREAK = '\n';
    private static final String DELIM = " ";

    private static final class Bar implements Comparable<Bar> {
        int idx;
        int height;

        Bar(int idx, int height) {
            this.idx = idx;
            this.height = height;
        }

        @Override
        public int compareTo(Bar o) {
            return o.height - height;
        }
    }

    private static final class Node {
        int lmax;
        int rmax;
        int full;
        int max;

        final void clear() {
            lmax = 0;
            rmax = 0;
            full = 0;
            max = 0;
        }

        final void init() {
            lmax = 1;
            rmax = 1;
            full = 1;
            max = 1;
        }

        final void merge(Node left, Node right) {
            lmax = Math.max(left.lmax, left.full == 0 ? 0 : left.full + right.lmax);
            rmax = Math.max(right.rmax, right.full == 0 ? 0 : right.full + left.rmax);
            if (left.full != 0 && right.full != 0) {
                max = full = left.full + right.full;
            } else {
                full = 0;
                max = Math.max(Math.max(left.max, right.max), left.rmax + right.lmax);
            }
        }
    }

    private static final class Pbs {
        int l;
        int r;
        int w;
        int left;
        int right;
        int ans;
        Pbs next;

        Pbs(int l, int r, int w, Pbs next) {
            this.l = l;
            this.r = r;
            this.w = w;
            left = 1;
            right = n + 1;
            this.next = next;
        }

        final void validate(int mid) {
            int max;

            max = query(1, 1, n, l, r).max;
            arr[mid] = next;
            if (max >= w) {
                right = mid;
            } else {
                left = mid + 1;
            }
            if (left >= right) {
                ans = sorted[right].height;
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
    private static Bar[] sorted;
    private static Bar[] histogram;
    private static Pbs[] arr;
    private static Node[] tree;
    private static Node[] results;

    private static void insert(int node, int start, int end, int idx) {
        int mid;

        if (start == end) {
            tree[node].init();
            return;
        }
        mid = start + end >>> 1;
        if (idx <= mid) {
            insert(node << 1, start, mid, idx);
        } else {
            insert(node << 1 | 1, mid + 1, end, idx);
        }
        tree[node].merge(tree[node << 1], tree[node << 1 | 1]);
    }

    private static Node query(int node, int start, int end, int left, int right) {
        int mid;

        if (right < start || end < left) {
            results[node].clear();
            return results[node];
        }
        if (left <= start && end <= right) {
            return tree[node];
        }
        mid = start + end >>> 1;
        results[node].merge(query(node << 1, start, mid, left, right), query(node << 1 | 1, mid + 1, end, left, right));
        return results[node];
    }

    public static void main(String[] args) throws IOException {
        int q;
        int i;
        int mid;
        int size;
        Pbs[] ans;
        StringBuilder sb;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        histogram = new Bar[n + 1];
        st = new StringTokenizer(br.readLine(), DELIM, false);
        for (i = 1; i <= n; i++) {
            histogram[i] = new Bar(i, Integer.parseInt(st.nextToken()));
        }
        sorted = new Bar[n + 1];
        System.arraycopy(histogram, 1, sorted, 1, n);
        Arrays.sort(sorted, 1, n + 1);
        q = Integer.parseInt(br.readLine());
        arr = new Pbs[n + 1];
        ans = new Pbs[q];
        mid = n + 2 >>> 1;
        for (i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine(), DELIM, false);
            arr[mid] = new Pbs(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), arr[mid]);
            ans[i] = arr[mid];
        }
        size = n << 2;
        tree = new Node[size];
        results = new Node[size];
        for (i = 0; i < size; i++) {
            tree[i] = new Node();
            results[i] = new Node();
        }
        cnt = 0;
        while (cnt != q) {
            for (i = 0; i < size; i++) {
                tree[i].clear();
            }
            for (i = 1; i <= n; i++) {
                insert(1, 1, n, sorted[i].idx);
                while (arr[i] != null) {
                    arr[i].validate(i);
                }
            }
        }
        sb = new StringBuilder();
        for (i = 0; i < q; i++) {
            sb.append(ans[i].ans).append(LINE_BREAK);
        }
        System.out.print(sb.toString());
    }
}
