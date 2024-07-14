import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final char SPACE = ' ';

    private static final class Edge {
        int to;
        Edge next;

        Edge(int to, Edge next) {
            this.to = to;
            this.next = next;
        }
    }

    private static int[] arr;
    private static Edge[] adj;

    private static void dfs(int curr, int parent, int multiplier) {
        Edge edge;

        arr[curr] *= multiplier;
        for (edge = adj[curr]; edge != null; edge = edge.next) {
            if (edge.to == parent) {
                continue;
            }
            dfs(edge.to, curr, multiplier);
        }
    }

    private static int getGcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return getGcd(b, a % b);
    }

    public static void main(String[] args) throws IOException {
        int n;
        int a;
        int b;
        int i;
        int gcd;
        int temp;
        StringBuilder sb;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        for (i = 0; i < n; i++) {
            arr[i] = 1;
        }
        adj = new Edge[n];
        for (i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            temp = arr[a];
            dfs(a, -1, arr[b] * Integer.parseInt(st.nextToken()));
            dfs(b, -1, temp * Integer.parseInt(st.nextToken()));
            adj[a] = new Edge(b, adj[a]);
            adj[b] = new Edge(a, adj[b]);
        }
        gcd = arr[0];
        sb = new StringBuilder();
        for (i = 1; i < n; i++) {
            gcd = getGcd(arr[i], gcd);
        }
        for (i = 0; i < n; i++) {
            sb.append(arr[i] / gcd).append(SPACE);
        }
        System.out.print(sb);
    }
}
