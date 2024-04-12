import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    private static final int MIN = Integer.MIN_VALUE;
    private static ArrayList<ArrayList<Integer>> adj;
    
    private static int dijkstra(int n, int k, int[] gpsLog) {
        int dest, last, node, cnt, min, i;
        int[] curr;
        int[][] distance;
        PriorityQueue<int[]> pq;
        
        last = k - 2;
        dest = gpsLog[k - 1];
        distance = new int[n + 1][k];
        distance[gpsLog[0]][0] = MIN;
        pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(distance[o1[0]][o1[1]], distance[o2[0]][o2[1]]);
			}
		});
        pq.offer(new int[] {gpsLog[0], 0});
        while (!pq.isEmpty()) {
            curr = pq.poll();
            node = curr[0];
            cnt = curr[1];
            for (int next : adj.get(node)) {
                if (next == gpsLog[cnt + 1]) {
                    if (distance[node][cnt] < distance[next][cnt + 1]) {
                        distance[next][cnt + 1] = distance[node][cnt];
                        if (cnt < last) {
                        	pq.offer(new int[] {next, cnt + 1});
                        }
                    }
                } else {
                    if (distance[node][cnt] + 1 < distance[next][cnt + 1]) {
                        distance[next][cnt + 1] = distance[node][cnt] + 1;
                        if (cnt < last) {
                        	pq.offer(new int[] {next, cnt + 1});
                        }
                    }
                }
            }
        }
        min = 0;
        for (i = 1; i <= n; i++) {
        	if (adj.get(i).contains(dest)) {
        		if (distance[i][last] < min) {
        			min = distance[i][last];
        		}
        	}
        }
        return min == 0 ? -1 : min - MIN;
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
        return dijkstra(n, k, gps_log);
    }
}