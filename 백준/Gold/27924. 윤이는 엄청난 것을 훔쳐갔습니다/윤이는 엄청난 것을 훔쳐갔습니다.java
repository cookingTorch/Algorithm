import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    private static final int A = Integer.MIN_VALUE;
    private static final int BC = A >>> 1;
    private static final int GET_FLAG = A | BC;
    private static final int FALSE = 0;
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

    private static int n;
    private static Edge[] adj;

    private static final boolean bfs(int a, int b, int c) {
        int curr;
        int flag;
        int[] visited;
        Edge edge;
        ArrayDeque<Integer> q;

        visited = new int[n + 1];
        q = new ArrayDeque<>(n);
        visited[a] = A;
        visited[b] = BC;
        visited[c] = BC;
        q.addLast(b | BC);
        q.addLast(c | BC);
        q.addLast(a | A);
        while (!q.isEmpty()) {
            curr = q.pollFirst();
            flag = curr & GET_FLAG;
            curr ^= flag;
            if (flag == A) {
                if (adj[curr].next == null) {
                    return true;
                }
                if (visited[curr] == BC) {
                    continue;
                }
            }
            for (edge = adj[curr]; edge != null; edge = edge.next) {
                if (visited[edge.to] == FALSE) {
                    visited[edge.to] = flag;
                    q.addLast(edge.to | flag);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        int u;
        int v;
        int i;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        adj = new Edge[n + 1];
        for (i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ", false);
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            adj[u] = new Edge(v, adj[u]);
            adj[v] = new Edge(u, adj[v]);
        }
        st = new StringTokenizer(br.readLine(), " ", false);
        if (bfs(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))) {
            System.out.print(YES);
        } else {
            System.out.print(NO);
        }
    }
}
