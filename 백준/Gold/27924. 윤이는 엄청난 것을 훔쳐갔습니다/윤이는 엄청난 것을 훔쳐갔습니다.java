import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final char[] YES = {'Y', 'E', 'S'};
    private static final char[] NO = {'N', 'O'};

    private static final class Edge {
        int to;
        Edge next;

        Edge(int to, Edge next) {
            this.to = to;
            this.next = next;
        }
    }

    private static int[] dists;
    private static Edge[] adj;

    private static final void bDfs(int parent, int node, int dist) {
        Edge edge;

        if ((edge = adj[node]).next == null) {
            dists[node] = dist;
        }
        dist++;
        for (; edge != null; edge = edge.next) {
            if (edge.to == parent) {
                continue;
            }
            bDfs(node, edge.to, dist);
        }
    }

    private static final void cDfs(int parent, int node, int dist) {
        Edge edge;

        if ((edge = adj[node]).next == null) {
            dists[node] = Math.min(dists[node], dist);
        }
        dist++;
        for (; edge != null; edge = edge.next) {
            if (edge.to == parent) {
                continue;
            }
            cDfs(node, edge.to, dist);
        }
    }

    private static final boolean aDfs(int parent, int node, int dist) {
        Edge edge;

        if ((edge = adj[node]).next == null) {
            if (dist < dists[node]) {
                return true;
            }
        }
        dist++;
        for (; edge != null; edge = edge.next) {
            if (edge.to == parent) {
                continue;
            }
            if (aDfs(node, edge.to, dist)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        int n;
        int u;
        int v;
        int a;
        int i;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        adj = new Edge[n + 1];
        dists = new int[n + 1];
        while (--n > 0) {
            st = new StringTokenizer(br.readLine(), " ", false);
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            adj[u] = new Edge(v, adj[u]);
            adj[v] = new Edge(u, adj[v]);
        }
        st = new StringTokenizer(br.readLine(), " ", false);
        a = Integer.parseInt(st.nextToken());
        bDfs(0, Integer.parseInt(st.nextToken()), 0);
        cDfs(0, Integer.parseInt(st.nextToken()), 0);
        if (aDfs(0, a, 0)) {
            System.out.print(YES);
        } else {
            System.out.print(NO);
        }
    }
}
