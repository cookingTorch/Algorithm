import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static void qAdd(Queue<int[]> q, int[][][] box, boolean[][][] visited, int[] next, int n, int m, int h) {
        if (next[0] < 0 || next[1] < 0 || next[2] < 0 || next[0] >= h || next[1] >= m || next[2] >= n || visited[next[0]][next[1]][next[2]] || box[next[0]][next[1]][next[2]] != 0)
            return ;
        visited[next[0]][next[1]][next[2]] = true;
        box[next[0]][next[1]][next[2]] = 1;
        q.add(new int[]{next[0], next[1], next[2]});
    }

    private static int bfs(int[][][] box, boolean[][][] visited, int n, int m, int h) {
        int cnt, size, i, j, k;
        int[] node, next;
        Queue<int[]> q = new LinkedList<>();

        for (i = 0; i < h; i++) {
            for (j = 0; j < m; j++) {
                for (k = 0; k < n; k++) {
                    if (box[i][j][k] == 1) {
                        visited[i][j][k] = true;
                        q.add(new int[]{i, j, k});
                    }
                }
            }
        }
        if (q.isEmpty())
            return 0;
        cnt = -1;
        while (!q.isEmpty()) {
            size = q.size();
            for (i = 0; i < size; i++) {
                node = q.poll();
                next = new int[]{node[0] - 1, node[1], node[2]};
                qAdd(q, box, visited, next, n, m, h);
                next = new int[]{node[0], node[1] - 1, node[2]};
                qAdd(q, box, visited, next, n, m, h);
                next = new int[]{node[0], node[1], node[2] - 1};
                qAdd(q, box, visited, next, n, m, h);
                next = new int[]{node[0] + 1, node[1], node[2]};
                qAdd(q, box, visited, next, n, m, h);
                next = new int[]{node[0], node[1] + 1, node[2]};
                qAdd(q, box, visited, next, n, m, h);
                next = new int[]{node[0], node[1], node[2] + 1};
                qAdd(q, box, visited, next, n, m, h);
            }
            cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n, m, h, i, j, k, cnt;
        boolean flag = true;
        int[][][] box;
        boolean[][][] visited;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        box = new int[h][m][n];
        visited = new boolean[h][m][n];
        for (i = 0; i < h; i++) {
            for (j = 0; j < m; j++) {
                st = new StringTokenizer(br.readLine());
                for (k = 0; k < n; k++)
                    box[i][j][k] = Integer.parseInt(st.nextToken());
            }
        }
        cnt = bfs(box, visited, n, m, h);
        for (i = 0; i < h; i++) {
            for (j = 0; j < m; j++) {
                for (k = 0; k < n; k++) {
                    if (box[i][j][k] == 0) {
                        sb.append(-1);
                        flag = false;
                        break;
                    }
                }
                if (!flag)
                    break;
            }
            if (!flag)
                break;
        }
        if (flag)
            sb.append(cnt);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}