import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    private static int dfs(int[][] map, boolean[][] visited, int x, int y, int n) {
        int size, cnt;

        cnt = 1;
        if (x > 0 && map[x - 1][y] == 1 && !visited[x - 1][y]) {
            visited[x - 1][y] = true;
            cnt += dfs(map, visited, x - 1, y, n);
        }
        if (y > 0 && map[x][y - 1] == 1 && !visited[x][y - 1]) {
            visited[x][y - 1] = true;
            cnt += dfs(map, visited, x, y - 1, n);
        }
        if (x < n - 1 && map[x + 1][y] == 1 && !visited[x + 1][y]) {
            visited[x + 1][y] = true;
            cnt += dfs(map, visited, x + 1, y, n);
        }
        if (y < n - 1 && map[x][y + 1] == 1 && !visited[x][y + 1]) {
            visited[x][y + 1] = true;
            cnt += dfs(map, visited, x, y + 1, n);
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n, i, j, cnt;
        int[][] map;
        boolean[][] visited;
        ArrayList<Integer> ans = new ArrayList<>();
        String str;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];
        for (i = 0; i < n; i++) {
            str = br.readLine();
            for (j = 0; j < n; j++)
                map[i][j] = str.charAt(j) - '0';
        }
        cnt = 0;
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    visited[i][j] = true;
                    cnt++;
                    ans.add(dfs(map, visited, i, j, n));
                }
            }
        }
        Collections.sort(ans);
        sb.append(cnt).append("\n");
        for (i = 0; i < cnt; i++)
            sb.append(ans.get(i)).append("\n");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}