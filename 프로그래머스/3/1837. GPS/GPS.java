import java.util.ArrayList;
import java.util.ArrayDeque;

class Solution {
    private static final int INF = Integer.MAX_VALUE;
    private static ArrayList<ArrayList<Integer>> adj;
    
    private static int bfs(int n, int k, int[] gpsLog) {
        int node, cnt, i, j;
        int[] curr;
        int[][] distance;
        ArrayDeque<int[]> q;
        
        distance = new int[n + 1][k + 1];
        for (i = 1; i <= n; i++) {
            for (j = 1; j <= k; j++) {
                distance[i][j] = INF;
            }
        }
        distance[gpsLog[0]][1] = 0;
        q = new ArrayDeque<>();
        q.addLast(new int[] {gpsLog[0], 1});
        while (!q.isEmpty()) {
            curr = q.pollFirst();
            node = curr[0];
            cnt = curr[1];
            if (cnt == k) {
                continue;
            }
            for (int next : adj.get(node)) {
                if (next == gpsLog[cnt]) {
                    if (distance[node][cnt] < distance[next][cnt + 1]) {
                        distance[next][cnt + 1] = distance[node][cnt];
                        q.addLast(new int[] {next, cnt + 1});
                    }
                } else {
                    if (distance[node][cnt] + 1 < distance[next][cnt + 1]) {
                        distance[next][cnt + 1] = distance[node][cnt] + 1;
                        q.addLast(new int[] {next, cnt + 1});
                    }
                }
            }
        }
        return distance[gpsLog[k - 1]][k] == INF ? -1 : distance[gpsLog[k - 1]][k];
    }
    
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        int i;
        
        adj = new ArrayList<>(n + 1);
        adj.add(null);
        for (i = 1; i <= n; i++) {
            adj.add(new ArrayList<>());
            adj.get(i).add(i);
        }
        for (int[] edge : edge_list) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        return bfs(n, k, gps_log);
    }
}