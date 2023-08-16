import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static void dfs(ArrayList<ArrayList<Integer>> adj, boolean[] visited, int node) {
        int next, size, i;

        size = adj.get(node).size();
        for (i = 0; i < size; i++) {
            next = adj.get(node).get(i);
            if (!visited[next]) {
                visited[next] = true;
                dfs(adj, visited, next);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n, m, node1, node2, cnt, i;
        boolean[] visited;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (i = 0; i < n + 1; i++)
            adj.add(new ArrayList<>());
        for (i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            node1 = Integer.parseInt(st.nextToken());
            node2 = Integer.parseInt(st.nextToken());
            adj.get(node1).add(node2);
            adj.get(node2).add(node1);
        }
        visited = new boolean[n + 1];
        cnt = 0;
        for (i = 1; i <= n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                cnt++;
                dfs(adj, visited, i);
            }
        }
        sb.append(cnt);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}