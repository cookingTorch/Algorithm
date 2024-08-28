import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static final int INF = Integer.MAX_VALUE >> 1;
    private static final int FORWARD = 1 << 1;
    private static final int BACKWARD = 1;
    private static final int TWO_WAY = FORWARD | BACKWARD;
    private static final char LEFT = '<';
    private static final char RIGHT = '>';
    private static final char LINE_BREAK = '\n';

    private static final class Edge {
        int to;
        int dir;
        Edge next;

        Edge(int to, int dir, Edge next) {
            this.to = to;
            this.dir = dir;
            this.next = next;
        }
    }

    private static final class Range {
        int left;
        int right;
        Range next;

        Range(int left, Range next) {
            this.left = left;
            this.right = INF;
            this.next = next;
        }
    }

    private static final class Query {
        int left;
        int right;

        Query(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    private static final class UndoLog {
        int idx;
        int val;

        UndoLog(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }

        void undo() {
            tree[idx] = val;
        }
    }

    private static int n;
    private static int q;
    private static int idx;
    private static int[] dirs;
    private static int[] sIdx;
    private static int[] eIdx;
    private static int[] tree;
    private static Edge[] adj;
    private static ArrayList<Query>[] queries;
    private static StringBuilder sb;

    private static int getDir(String arrow) {
        if (arrow.charAt(0) == LEFT) {
            return BACKWARD;
        }
        if (arrow.charAt(1) == RIGHT) {
            return FORWARD;
        }
        return TWO_WAY;
    }

    private static int getReverse(int dir) {
        if (dir == FORWARD) {
            return BACKWARD;
        }
        if (dir == BACKWARD) {
            return FORWARD;
        }
        return TWO_WAY;
    }

    private static int eulerTour(int parent, int curr) {
        Edge edge;

        sIdx[curr] = idx++;
        eIdx[curr] = sIdx[curr];
        for (edge = adj[curr]; edge != null; edge = edge.next) {
            if (edge.to == parent) {
                continue;
            }
            dirs[edge.to] = edge.dir;
            eIdx[curr] = eulerTour(curr, edge.to);
        }
        return eIdx[curr];
    }

    private static void setRange(int v, Range[] ranges, int way) {
        if ((dirs[v] & way) == 0) {
            ranges[v] = new Range(-1, ranges[v]);
        }
    }

    private static void setRange(int v, int dir, int num, Range[] ranges, int way) {
        if ((dirs[v] & way) == 0) {
            if ((dir & way) != 0) {
                ranges[v].right = num - 1;
                dirs[v] ^= way;
            }
        } else if ((dir & way) == 0) {
            ranges[v] = new Range(num, ranges[v]);
            dirs[v] ^= way;
        }
    }

    private static void addQuery(int node, int start, int end, int left, int right, Query query) {
        int mid;

        if (right < start || end < left) {
            return;
        }
        if (left <= start && end <= right) {
            if (queries[node] == null) {
                queries[node] = new ArrayList<>();
            }
            queries[node].add(query);
            return;
        }
        mid = start + end >> 1;
        addQuery(node << 1, start, mid, left, right, query);
        addQuery(node << 1 | 1, mid + 1, end, left, right, query);
    }

    private static void addRange(int v, Range[] ranges, boolean in) {
        Range range;

        for (range = ranges[v]; range != null; range = range.next) {
            if (range.left > range.right) {
                continue;
            }
            if (in) {
                addQuery(1, 0, q - 1, range.left, range.right, new Query(sIdx[v], eIdx[v]));
            } else {
                if (sIdx[v] != 0) {
                    addQuery(1, 0, q - 1, range.left, range.right, new Query(0, sIdx[v] - 1));
                }
                if (eIdx[v] != n - 1) {
                    addQuery(1, 0, q - 1, range.left, range.right, new Query(eIdx[v] + 1, n - 1));
                }
            }
        }
    }

    private static void initTree(int node, int start, int end) {
        int mid;

        if (start == end) {
            tree[node] = 1;
            return;
        }
        mid = start + end >> 1;
        initTree(node << 1, start, mid);
        initTree(node << 1 | 1, mid + 1, end);
        tree[node] = tree[node << 1] + tree[node << 1 | 1];
    }

    private static void update(int node, int start, int end, int left, int right, ArrayDeque<UndoLog> undoLogs) {
        int mid;

        if (right < start || end < left || tree[node] == 0) {
            return;
        }
        undoLogs.addFirst(new UndoLog(node, tree[node]));
        if (left <= start && end <= right) {
            tree[node] = 0;
            return;
        }
        mid = start + end >> 1;
        update(node << 1, start, mid, left, right, undoLogs);
        update(node << 1 | 1, mid + 1, end, left, right, undoLogs);
        tree[node] = tree[node << 1] + tree[node << 1 | 1];
    }

    private static void divide(int node, int start, int end) {
        int mid;
        ArrayDeque<UndoLog> undoLogs;

        undoLogs = new ArrayDeque<>();
        if (queries[node] != null) {
            for (Query query : queries[node]) {
                update(1, 0, n - 1, query.left, query.right, undoLogs);
            }
        }
        if (start == end) {
            sb.append(tree[1]).append(LINE_BREAK);
        } else {
            mid = start + end >> 1;
            divide(node << 1, start, mid);
            divide(node << 1 | 1, mid + 1, end);
        }
        while (!undoLogs.isEmpty()) {
            undoLogs.pollFirst().undo();
        }
    }

    public static void main(String[] args) throws IOException {
        int u;
        int v;
        int dir;
        int i;
        Range[] inRanges;
        Range[] outRanges;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        adj = new Edge[n + 1];
        for (i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            dir = getDir(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            adj[u] = new Edge(v, dir, adj[u]);
            adj[v] = new Edge(u, getReverse(dir), adj[v]);
        }
        dirs = new int[n + 1];
        idx = 0;
        sIdx = new int[n + 1];
        eIdx = new int[n + 1];
        eulerTour(-1, 1);
        inRanges = new Range[n + 1];
        outRanges = new Range[n + 1];
        for (v = 2; v <= n; v++) {
            setRange(v, outRanges, FORWARD);
            setRange(v, inRanges, BACKWARD);
        }
        q = Integer.parseInt(br.readLine());
        for (i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            dir = getDir(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            if (sIdx[u] > sIdx[v]) {
                v = u;
                dir = getReverse(dir);
            }
            setRange(v, dir, i, outRanges, FORWARD);
            setRange(v, dir, i, inRanges, BACKWARD);
        }
        queries = new ArrayList[q << 2];
        for (v = 2; v <= n; v++) {
            addRange(v, outRanges, false);
            addRange(v, inRanges, true);
        }
        tree = new int[n << 2];
        initTree(1, 0, n - 1);
        sb = new StringBuilder();
        divide(1, 0, q - 1);
        System.out.print(sb);
    }
}
