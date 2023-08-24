import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static void qAdd(int[] next, Queue<int[]> q, char[][] map, char color, boolean[][] visited, int n, boolean colorWeakness) {
        if (next[0] < 0 || next[1] < 0 || next[0] >= n || next[1] >= n || visited[next[0]][next[1]])
            return ;
        if (!colorWeakness && map[next[0]][next[1]] != color)
            return ;
        if (colorWeakness && map[next[0]][next[1]] == 'B' && color != 'B')
            return ;
        if (colorWeakness && map[next[0]][next[1]] != 'B' && color == 'B')
            return ;
        visited[next[0]][next[1]] = true;
        q.add(new int[]{next[0], next[1]});
    }

    private static void bfs(char[][] map, char color, boolean[][] visited, int[] start, int n, boolean colorWeakness) {
        int[] node, next;
        Queue<int[]> q = new LinkedList<>();

        visited[start[0]][start[1]] = true;
        q.add(start);
        while (!q.isEmpty()) {
            node = q.poll();
            next = new int[]{node[0] - 1, node[1]};
            qAdd(next, q, map, color, visited, n, colorWeakness);
            next = new int[]{node[0], node[1] - 1};
            qAdd(next, q, map, color, visited, n, colorWeakness);
            next = new int[]{node[0] + 1, node[1]};
            qAdd(next, q, map, color, visited, n, colorWeakness);
            next = new int[]{node[0], node[1] + 1};
            qAdd(next, q, map, color, visited, n, colorWeakness);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n, i, j, cnt;
        char[][] map;
        boolean[][] visited;
        String line;

        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        visited = new boolean[n][n];
        for (i = 0; i < n; i++) {
            line = br.readLine();
            for (j = 0; j < n; j++)
                map[i][j] = line.charAt(j);
        }
        cnt = 0;
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    cnt++;
                    bfs(map, map[i][j], visited, new int[]{i, j}, n, false);
                }
            }
        }
        sb.append(cnt).append(" ");
        visited = new boolean[n][n];
        cnt = 0;
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    cnt++;
                    bfs(map, map[i][j], visited, new int[]{i, j}, n, true);
                }
            }
        }
        sb.append(cnt);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}