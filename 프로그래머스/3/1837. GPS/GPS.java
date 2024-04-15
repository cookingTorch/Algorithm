import java.util.Arrays;
import java.util.ArrayDeque;

class Solution {
    private static final int MAX_N = 200;
    private static final int INF = Integer.MAX_VALUE;
    
    private static final class Node {
    	int idx;
    	Node next;
    	
    	Node(int idx, Node next) {
    		this.idx = idx;
    		this.next = next;
    	}
    }
    
    private static int n;
    private static int[] level = new int[MAX_N + 1];
    private static ArrayDeque<Integer> q = new ArrayDeque<>();
    private static Node[] adj;
    
    private static void bfs(int end) {
    	int curr, i;
    	Node next;
    	
        for (i = 1; i <= n; i++) {
            level[i] = INF;
        }
    	level[end] = 0;
    	q.addLast(end);
    	while (!q.isEmpty()) {
    		curr = q.pollFirst();
    		for (next = adj[curr]; next != null; next = next.next) {
    			if (level[next.idx] == INF) {
    				level[next.idx] = level[curr] + 1;
    				q.addLast(next.idx);
    			}
    		}
    	}
    }
    
    private static int bfs01(int k, int[] gpsLog) {
        int idx, next, cnt, dist;
        int[] curr;
        boolean[][] visited;
        ArrayDeque<int[]> dq;
        Node node;
        
        visited = new boolean[n + 1][k + 1];
        dq = new ArrayDeque<>();
        dq.addLast(new int[] {gpsLog[0], 1, 0});
        for (;;) {
            curr = dq.pollFirst();
            idx = curr[0];
            cnt = curr[1];
            dist = curr[2];
            if (cnt == k) {
                return dist;
            }
            for (node = adj[idx]; node != null; node = node.next) {
                next = node.idx;
            	if (level[next] < k - cnt && !visited[next][cnt + 1]) {
            		if (next == gpsLog[cnt]) {
            			dq.addFirst(new int[] {next, cnt + 1, dist});
                    } else {
                        dq.addLast(new int[] {next, cnt + 1, dist + 1});
                    }
                    visited[next][cnt + 1] = true;
            	}
            }
        }
    }
    
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        this.n = n;
        adj = new Node[n + 1];
        for (int[] edge : edge_list) {
        	adj[edge[0]] = new Node(edge[1], adj[edge[0]]);
        	adj[edge[1]] = new Node(edge[0], adj[edge[1]]);
        }
        bfs(gps_log[k - 1]);
        if (level[gps_log[0]] > k - 1) {
        	return -1;
        }
        return bfs01(k, gps_log);
    }
}