import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int bfs(int[][] maze, int n, int m) {
        int[] node;
        int[][] distance = new int[n][m];
        Queue<int[]> q = new LinkedList<>();

        distance[0][0] = 1;
        q.add(new int[]{0, 0});
        while (!q.isEmpty()) {
            node = q.poll();
            if (Arrays.equals(node, new int[]{n - 1, m - 1}))
                return distance[n - 1][m - 1];
            if (node[0] > 0 && maze[node[0] - 1][node[1]] == 1 && distance[node[0] - 1][node[1]] == 0) {
                distance[node[0] - 1][node[1]] = distance[node[0]][node[1]] + 1;
                q.add(new int[]{node[0] - 1, node[1]});
            }
            if (node[1] > 0 && maze[node[0]][node[1] - 1] == 1 && distance[node[0]][node[1] - 1] == 0) {
                distance[node[0]][node[1] - 1] = distance[node[0]][node[1]] + 1;
                q.add(new int[]{node[0], node[1] - 1});
            }
            if (node[0] < n - 1 && maze[node[0] + 1][node[1]] == 1 && distance[node[0] + 1][node[1]] == 0) {
                distance[node[0] + 1][node[1]] = distance[node[0]][node[1]] + 1;
                q.add(new int[]{node[0] + 1, node[1]});
            }
            if (node[1] < m - 1 && maze[node[0]][node[1] + 1] == 1 && distance[node[0]][node[1] + 1] == 0) {
                distance[node[0]][node[1] + 1] = distance[node[0]][node[1]] + 1;
                q.add(new int[]{node[0], node[1] + 1});
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n, m, i, j;
        int[][] maze;
        String str;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        maze = new int[n][m];
        for (i = 0; i < n; i++) {
            str = br.readLine();
            for (j = 0; j < m; j++)
                maze[i][j] = str.charAt(j) - '0';
        }
        sb.append(bfs(maze, n, m));

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}