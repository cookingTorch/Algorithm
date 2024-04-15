import java.util.ArrayList;
import java.util.Arrays;
import java.util.ArrayDeque;

class Solution {
    private static final int INF = Integer.MAX_VALUE;
    
    private static int[] level;
    private static ArrayList<ArrayList<Integer>> adj;
    
    private static void bfs(int start) {
    	int curr;
    	ArrayDeque<Integer> q;
    	
    	Arrays.fill(level, INF);
    	level[start] = 0;
    	q = new ArrayDeque<>();
    	q.addLast(start);
    	while (!q.isEmpty()) {
    		curr = q.pollFirst();
    		for (int next : adj.get(curr)) {
    			if (level[next] == INF) {
    				level[next] = level[curr] + 1;
    				q.addLast(next);
    			}
    		}
    	}
    }
    
    private static int bfs01(int n, int k, int[] gpsLog) {
        int node, cnt, dist;
        int[] curr;
        boolean[][] visited;
        ArrayDeque<int[]> dq;
        
        visited = new boolean[n + 1][k + 1];
        dq = new ArrayDeque<>();
        dq.addLast(new int[] {gpsLog[0], 1, 0});
        while (!dq.isEmpty()) {
            curr = dq.pollFirst();
            node = curr[0];
            cnt = curr[1];
            dist = curr[2];
            if (cnt == k) {
                return dist;
            }
            for (int next : adj.get(node)) {
            	if (level[next] < k - cnt && !visited[next][cnt + 1]) {
            		if (next == gpsLog[cnt]) {
            			dq.addFirst(new int[] {next, cnt + 1, dist});
                    } else {
                        dq.addLast(new int[] {next, cnt + 1, dist + 1});
                    }
            	}
            	visited[next][cnt + 1] = true;
            }
        }
        return -1;
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
        level = new int[n + 1];
        bfs(gps_log[k - 1]);
        if (level[gps_log[0]] > k - 1) {
        	return -1;
        }
        return bfs01(n, k, gps_log);
    }
}