import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    private static int n;
    private static int[] level, weight;
    private static ArrayList<ArrayList<Integer>> adj;

    private static int topoSort() {
        int curr, max, i;
        int[] distance;
        Queue<Integer> q;

        distance = new int[n + 1];
        q = new ArrayDeque<>();
        for (i = 1; i <= n; i++) {
            if (level[i] == 0) {
                distance[i] = weight[i];
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            curr = q.poll();
            for (int next : adj.get(curr)) {
                distance[next] = Math.max(distance[next], distance[curr] + weight[next]);
                if (--level[next] == 0) {
                    q.add(next);
                }
            }
        }
        max = 0;
        for (i = 1; i <= n; i++) {
            if (max < distance[i]) {
                max = distance[i];
            }
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int num, i, j;

        n = Integer.parseInt(br.readLine());
        adj = new ArrayList<>(n + 1);
        for (i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        weight = new int[n + 1];
        level = new int[n + 1];
        for (i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            num = level[i] = Integer.parseInt(st.nextToken());
            for (j = 0; j < num; j++) {
                adj.get(Integer.parseInt(st.nextToken())).add(i);
            }
        }
        System.out.print(topoSort());
    }
}