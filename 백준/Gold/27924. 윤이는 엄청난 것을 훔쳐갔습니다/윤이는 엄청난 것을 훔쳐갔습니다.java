import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    private static final int IS_A = Integer.MIN_VALUE;
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
        boolean[] visited;
        Edge edge;
        ArrayDeque<Integer> q;

        visited = new boolean[n + 1];
        q = new ArrayDeque<>(n);
        visited[a] = visited[b] = visited[c] = true;
        q.addLast(b);
        q.addLast(c);
        q.addLast(a | IS_A);
        while (!q.isEmpty()) {
            curr = q.pollFirst();
            flag = curr & IS_A;
            curr ^= flag;
            if (adj[curr].next == null && flag == IS_A) {
                return true;
            }
            for (edge = adj[curr]; edge != null; edge = edge.next) {
                if (!visited[edge.to]) {
                    visited[edge.to] = true;
                    q.addLast(flag | edge.to);
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
