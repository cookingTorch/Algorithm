import java.util.ArrayDeque;

class Solution {
    private static final int NIL = -1;
    
    private static final class Edge {
        int to;
        Edge next;
        
        Edge(int to, Edge next) {
            this.to = to;
            this.next = next;
        }
    }
    
    private int[] bfs(int n, int dest, Edge[] adj) {
        int i;
        int cur;
        int dist;
        int size;
        int[] dists;
        Edge edge;
        ArrayDeque<Integer> q;
        
        dists = new int[n + 1];
        for (i = 1; i <= n; i++) {
            dists[i] = NIL;
        }
        q = new ArrayDeque<>();
        dists[dest] = 0;
        q.addLast(dest);
        for (dist = 1; !q.isEmpty(); dist++) {
            size = q.size();
            for (i = 0; i < size; i++) {
                cur = q.pollFirst();
                for (edge = adj[cur]; edge != null; edge = edge.next) {
                    if (dists[edge.to] == NIL) {
                        dists[edge.to] = dist;
                        q.addLast(edge.to);
                    }
                }
            }
        }
        return dists;
    }
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int u;
        int v;
        int i;
        int len;
        int[] ans;
        int[] dists;
        Edge[] adj;
        
        adj = new Edge[n + 1];
        len = roads.length;
        for (i = 0; i < len; i++) {
            u = roads[i][0];
            v = roads[i][1];
            adj[u] = new Edge(v, adj[u]);
            adj[v] = new Edge(u, adj[v]);
        }
        dists = bfs(n, destination, adj);
        len = sources.length;
        ans = new int[len];
        for (i = 0; i < len; i++) {
            ans[i] = dists[sources[i]];
        }
        return ans;
    }
}
