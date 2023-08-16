import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static int dfs(ArrayList<ArrayList<Integer>> adj, boolean[] visited, int node) {
        int next, size, cnt, i;

        cnt = 1;
        size = adj.get(node).size();
        for (i = 0; i < size; i++) {
            next = adj.get(node).get(i);
            if (!visited[next]) {
                visited[next] = true;
                cnt += dfs(adj, visited, next);
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int computer, network, node1, node2, i;
        boolean[] visited;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        computer = Integer.parseInt(br.readLine());
        network = Integer.parseInt(br.readLine());
        for (i = 0; i <= computer; i++)
            adj.add(new ArrayList<>());
        for (i = 0; i < network; i++) {
            st = new StringTokenizer(br.readLine());
            node1 = Integer.parseInt(st.nextToken());
            node2 = Integer.parseInt(st.nextToken());
            adj.get(node1).add(node2);
            adj.get(node2).add(node1);
        }
        visited = new boolean[computer + 1];
        visited[1] = true;
        sb.append(dfs(adj, visited, 1) - 1);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}