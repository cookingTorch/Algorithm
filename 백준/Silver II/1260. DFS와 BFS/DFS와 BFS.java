import java.io.*;
import java.util.*;

public class Main {
    private static void dfs(ArrayList<ArrayList<Integer>> adj, boolean[] visited, int node, StringBuilder sb) {
        int i, size, next;

        visited[node] = true;
        sb.append(node).append(" ");
        size = adj.get(node).size();
        Collections.sort(adj.get(node));
        for (i = 0; i < size; i++) {
            next = adj.get(node).get(i);
            if (!visited[next])
                dfs(adj, visited, next, sb);
        }
    }

    private static void bfs(ArrayList<ArrayList<Integer>> adj, boolean[] visited, int start, StringBuilder sb) {
        int i, node, size, next;
        Queue<Integer> q = new LinkedList<>();

        q.add(start);
        visited[start] = true;
        while (!q.isEmpty()) {
            node = q.poll();
            sb.append(node).append(" ");
            Collections.sort(adj.get(node));
            size = adj.get(node).size();
            for (i = 0; i < size; i++) {
                next = adj.get(node).get(i);
                if (!visited[next]) {
                    visited[next] = true;
                    q.add(next);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n, m, v, i, node1, node2;
        boolean[] visited;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        for (i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            node1 = Integer.parseInt(st.nextToken());
            node2 = Integer.parseInt(st.nextToken());
            adj.get(node1).add(node2);
            adj.get(node2).add(node1);
        }
        visited = new boolean[n + 1];
        dfs(adj, visited, v, sb);
        sb.append("\n");
        visited = new boolean[n + 1];
        bfs(adj, visited, v, sb);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}