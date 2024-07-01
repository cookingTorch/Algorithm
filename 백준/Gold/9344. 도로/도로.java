import java.io.*;
import java.util.*;

public class Main {
    private static final String NO = "NO\n";
    private static final String YES = "YES\n";
    
    private static final class Edge implements Comparable<Edge> {
        int u;
        int v;
        int weight;
        
        Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    
    private static int[] roots;
    private static BufferedReader br;
    
    private static final int find(int v) {
        if (roots[v] <= 0) {
            return v;
        }
        return roots[v] = find(roots[v]);
    }
    
    private static final boolean union(int u, int v) {
        int ru;
        int rv;
        
        ru = find(u);
        rv = find(v);
        if (ru == rv) {
            return false;
        }
        if (roots[ru] > roots[rv]) {
            roots[ru] = rv;
        } else {
            if (roots[ru] == roots[rv]) {
                roots[ru]--;
            }
            roots[rv] = ru;
        }
        return true;
    }
    
    private static final String solution() throws IOException {
        int n;
        int m;
        int p;
        int q;
        int i;
        Edge edge;
        StringTokenizer st;
        PriorityQueue<Edge> pq;
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>();
        for (i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            pq.offer(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        roots = new int[n + 1];
        for (i = 0; i < n - 1;) {
            edge = pq.poll();
            if (union(edge.u, edge.v)) {
                if ((edge.u == p && edge.v == q) || (edge.u == q && edge.v == p)) {
                    return YES;
                }
                i++;
            }
        }
        return NO;
    }
    
    public static void main(String args[]) throws IOException {
        int t;
        StringBuilder sb;
        
        br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        while (t-- > 0) {
            sb.append(solution());
        }
        System.out.print(sb);
    }
}