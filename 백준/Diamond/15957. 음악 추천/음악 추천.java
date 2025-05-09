import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int FAIL = -1;
    private static final char LINE_BREAK = '\n';
    private static final String DELIM = " ";

    private static final class Edge {
        int to;
        Edge next;

        Edge(int to, Edge next) {
            this.to = to;
            this.next = next;
        }
    }

    private static final class Ft {
        private final int n;
        private final long[] initTree;
        private final long[] tree;

        Ft(int n) {
            this.n = n;
            initTree = new long[n + 1];
            tree = new long[n + 1];
        }

        final void init() {
            System.arraycopy(initTree, 1, tree, 1, n);
        }

        final void updateRange(int l, int r, long v) {
            int i;

            for (i = l; i <= n; i += i & -i) {
                tree[i] += v;
            }
            if (r < n) {
                for (i = r + 1; i <= n; i += i & -i) {
                    tree[i] -= v;
                }
            }
        }

        final long query(int i) {
            long s;

            s = 0L;
            for (; i > 0; i -= i & -i) {
                s += tree[i];
            }
            return s;
        }
    }

    private static final class Query implements Comparable<Query> {
        int t;
        int l;
        int r;
        long v;

        Query(int t, int p, long s) {
            this.t = t;
            l = ls[p];
            r = rs[p];
            v = s / (r - l + 1);
        }

        final void run() {
            ft.updateRange(l, r, v);
        }

        @Override
        public int compareTo(Query o) {
            return t - o.t;
        }
    }

    private static final class Song {
        int idx;
        Song next;

        Song(int idx, Song next) {
            this.idx = idx;
            this.next = next;
        }
    }

    private static final class Pbs {
        int left;
        int right;
        int ans;
        double songCnt;
        Pbs next;
        Song songs;

        Pbs() {
            left = 0;
            right = k;
        }

        final void addSong(int idx) {
            songs = new Song(idx, songs);
            songCnt++;
        }

        final void insert(int mid) {
            next = arr[mid];
            arr[mid] = this;
        }

        final void validate(int mid) {
            long s;
            Song song;

            s = 0L;
            for (song = songs; song != null; song = song.next) {
                s += ft.query(song.idx);
                if (s / songCnt > j) {
                    break;
                }
            }
            arr[mid] = next;
            if (song != null) {
                right = mid;
            } else {
                left = mid + 1;
            }
            if (left >= right) {
                if (right == k) {
                    ans = FAIL;
                } else {
                    ans = queries[right].t;
                }
                cnt++;
                return;
            }
            insert(left + right >>> 1);
        }
    }

    private static int n;
    private static int k;
    private static int cnt;
    private static int[] ls;
    private static int[] rs;
    private static int eulerCnt;
    private static double j;
    private static Ft ft;
    private static Pbs[] arr;
    private static Edge[] adj;
    private static Query[] queries;

    private static final void eulerTour(int node) {
        Edge edge;

        ls[node] = ++eulerCnt;
        for (edge = adj[node]; edge != null; edge = edge.next) {
            eulerTour(edge.to);
        }
        rs[node] = eulerCnt;
    }

    public static void main(String[] args) throws IOException {
        int i;
        int mid;
        int parent;
        int singer;
        int singerCnt;
        Pbs[] ans;
        Pbs[] singers;
        StringBuilder sb;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), DELIM, false);
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        j = Integer.parseInt(st.nextToken());
        adj = new Edge[n + 1];
        st = new StringTokenizer(br.readLine(), DELIM, false);
        for (i = 2; i <= n; i++) {
            parent = Integer.parseInt(st.nextToken());
            adj[parent] = new Edge(i, adj[parent]);
        }
        ls = new int[n + 1];
        rs = new int[n + 1];
        eulerCnt = 0;
        eulerTour(1);
        singers = new Pbs[n + 1];
        singerCnt = 0;
        arr = new Pbs[k];
        mid = k >>> 1;
        ans = new Pbs[n + 1];
        st = new StringTokenizer(br.readLine(), DELIM, false);
        for (i = 1; i <= n; i++) {
            singer = Integer.parseInt(st.nextToken());
            if (singers[singer] == null) {
                singers[singer] = new Pbs();
                singers[singer].insert(mid);
                singerCnt++;
            }
            singers[singer].addSong(ls[i]);
            ans[i] = singers[singer];
        }
        queries = new Query[k];
        for (i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine(), DELIM, false);
            queries[i] = new Query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(queries);
        ft = new Ft(n);
        cnt = 0;
        while (cnt != singerCnt) {
            ft.init();
            for (i = 0; i < k; i++) {
                queries[i].run();
                while (arr[i] != null) {
                    arr[i].validate(i);
                }
            }
        }
        sb = new StringBuilder();
        for (i = 1; i <= n; i++) {
            sb.append(ans[i].ans).append(LINE_BREAK);
        }
        System.out.print(sb.toString());
    }
}
