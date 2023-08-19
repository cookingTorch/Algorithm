import java.io.*;
import java.util.*;

public class Main {
    private static boolean isValid(int[][] map, int[][] dist, int[] next, int n, int m) {
        if (next[0] < 0 || n <= next[0] || next[1] < 0 || m <= next[1])
            return false;
        if (map[next[0]][next[1]] == 1 && dist[next[0]][next[1]] == 0)
            return true;
        return false;
    }

    private static void bfs(int[][] map, int[][] dist, int[] start, int n, int m) {
        int[] node, next;
        Queue<int[]> q = new LinkedList<>();

        q.add(start);
        while (!q.isEmpty()) {
            node = q.poll();
            if (isValid(map, dist, next = new int[]{node[0] - 1, node[1]}, n, m)) {
                dist[next[0]][next[1]] = dist[node[0]][node[1]] + 1;
                q.add(next);
            }
            if (isValid(map, dist, next = new int[]{node[0], node[1] - 1}, n, m)) {
                dist[next[0]][next[1]] = dist[node[0]][node[1]] + 1;
                q.add(next);
            }
            if (isValid(map, dist, next = new int[]{node[0] + 1, node[1]}, n, m)) {
                dist[next[0]][next[1]] = dist[node[0]][node[1]] + 1;
                q.add(next);
            }
            if (isValid(map, dist, next = new int[]{node[0], node[1] + 1}, n, m)) {
                dist[next[0]][next[1]] = dist[node[0]][node[1]] + 1;
                q.add(next);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n, m, i, j;
        int[] start;
        int[][] map, dist;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        start = new int[2];
        map = new int[n][m];
        dist = new int[n][m];
        for (i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2)
                    start = new int[]{i, j};
            }
        }
        bfs(map, dist, start, n, m);
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                if (dist[i][j] == 0 && map[i][j] == 1)
                    sb.append(-1);
                else
                    sb.append(dist[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}