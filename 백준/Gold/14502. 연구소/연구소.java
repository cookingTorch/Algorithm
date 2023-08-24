import java.io.*;
import java.util.*;

public class Main {
    private static void combi(ArrayList<int[]> empty, ArrayList<int[][]> walls, int[][] wall, int start, int depth) {
        int i, size;
        int[][] temp;

        if (depth == 3) {
            temp = new int[3][2];
            for (i = 0; i < 3; i++)
                temp[i] = Arrays.copyOf(wall[i], 2);
            walls.add(temp);
            return ;
        }
        size = empty.size();
        for (i = start; i < size; i++) {
            wall[depth] = empty.get(i);
            combi(empty, walls, wall, i + 1, depth + 1);
        }
    }

    private static void qAdd(Queue<int[]> q, int[] next, int[][] map, int n, int m) {
        if (next[0] < 0 || next[1] < 0 || next[0] >= n || next[1] >= m || map[next[0]][next[1]] != 0)
            return ;
        map[next[0]][next[1]] = 2;
        q.add(new int[]{next[0], next[1]});
    }

    private static void infect(int[][] map, int[] virus, int n, int m) {
        int[] node, next;
        Queue<int[]> q = new LinkedList<>();

        q.add(virus);
        while (!q.isEmpty()) {
            node = q.poll();
            next = new int[]{node[0] - 1, node[1]};
            qAdd(q, next, map, n, m);
            next = new int[]{node[0], node[1] - 1};
            qAdd(q, next, map, n, m);
            next = new int[]{node[0] + 1, node[1]};
            qAdd(q, next, map, n, m);
            next = new int[]{node[0], node[1] + 1};
            qAdd(q, next, map, n, m);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n, m, i, j, k, size, max, safe;
        int[][] wall = new int[3][2];
        int[][] map, temp;
        ArrayList<int[]> virus = new ArrayList<>();
        ArrayList<int[]> empty = new ArrayList<>();
        ArrayList<int[][]> walls = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        temp = new int[n][m];
        for (i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2)
                    virus.add(new int[]{i, j});
                else if (map[i][j] == 0)
                    empty.add(new int[]{i, j});
            }
        }
        combi(empty, walls, wall, 0, 0);
        max = 0;
        size = walls.size();
        for (k = 0; k < size; k++) {
            for (i = 0; i < n; i++) {
                for (j = 0; j < m; j++)
                    temp[i][j] = map[i][j];
            }
            for (i = 0; i < 3; i++)
                temp[walls.get(k)[i][0]][walls.get(k)[i][1]] = 1;
            for (i = 0; i < virus.size(); i++)
                infect(temp, virus.get(i), n, m);
            safe = 0;
            for (i = 0; i < n; i++) {
                for (j = 0; j < m; j++) {
                    if (temp[i][j] == 0)
                        safe++;
                }
            }
            max = Math.max(max, safe);
        }
        sb.append(max);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}