import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] parent, depth, dist, ancestors;
    static ArrayList<ArrayList<int[]>> edges;
    static boolean[] visited;

    private static void dfs(int node, int parentNode, int currentDepth, int currentDist) {
        parent[node] = parentNode;
        depth[node] = currentDepth;
        dist[node] = currentDist;
        visited[node] = true;
        for (int[] edge : edges.get(node)) {
            if (edge[0] != parentNode) {
                dfs(edge[0], node, currentDepth + 1, currentDist + edge[1]);
            }
        }
    }

    private static int lca(int a, int b) {
        while (a != b) {
            if (depth[a] > depth[b]) {
                a = parent[a];
            } 
            else if (depth[b] > depth[a]) {
                b = parent[b];
            } 
            else {
                a = parent[a];
                b = parent[b];
            }
        }
        return a;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        String str;
        StringTokenizer st;

        int m, i, a, b, tempDistance, ancestor, distance;

        // 간선 입력
        n = Integer.parseInt(br.readLine());
        edges = new ArrayList<>(n + 1);
        for (i = 0; i <= n; i++) {
            edges.add(new ArrayList<>());
        }
        for (i = 0; i < n - 1; i++) {
            str = br.readLine();
            st = new StringTokenizer(str, " ");
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            tempDistance = Integer.parseInt(st.nextToken());
            edges.get(a).add(new int[]{b, tempDistance});
            edges.get(b).add(new int[]{a, tempDistance});
        }

        // 트리 구성
        parent = new int[n + 1];
        for (i = 0; i <= n; i++) {
            parent[i] = i;
        }
        depth = new int[n + 1];
        dist = new int[n + 1];
        ancestors = new int[n + 1];
        visited = new boolean[n + 1];
        dfs(1, 0, 0, 0);

        // 쿼리 수행
        m = Integer.parseInt(br.readLine());
        for (i = 0; i < m; i++) {
            str = br.readLine();
            st = new StringTokenizer(str, " ");
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            ancestor = lca(a, b);
            distance = dist[a] + dist[b] - 2 * dist[ancestor];
            sb.append(distance).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}