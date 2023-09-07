import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n, m, i, j, size, ans = 0;
        int[] node, snakeLadder = new int[101];
        boolean[] visited = new boolean[101];
        ArrayList<ArrayList<Integer>> dice = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (i = 1; i <= 100; i++) {
            snakeLadder[i] = i;
        }
        for (i = 0; i < n + m; i++) {
            st = new StringTokenizer(br.readLine());
            snakeLadder[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }
        for (i = 0; i <= 100; i++) {
            dice.add(new ArrayList<>());
            if (snakeLadder[i] == i && 0 < i && i < 100) {
                for (j = 6; j >= 1; j--) {
                    if (i + j <= 100) {
                        dice.get(i).add(i + j);
                    }
                }
            }
        }
        q.add(new int[]{1, 0});
        visited[1] = true;
        while (!q.isEmpty()) {
            node = q.poll();
            size = dice.get(node[0]).size();
            for (i = 0; i < size; i++) {
                if (snakeLadder[dice.get(node[0]).get(i)] == 100) {
                    ans = node[1] + 1;
                    q.clear();
                    break;
                }
                if (!visited[snakeLadder[dice.get(node[0]).get(i)]]) {
                    visited[snakeLadder[dice.get(node[0]).get(i)]] = true;
                    q.add(new int[]{snakeLadder[dice.get(node[0]).get(i)], node[1] + 1});
                }
            }
        }
        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}