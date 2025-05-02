import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static final String DELIM = " ";
    private static final Edge NIL = new Edge();

    private static final class Edge implements Comparable<Edge> {
        int len;
        boolean disabled;
        Edge left;
        Edge right;

        Edge() {
        }

        Edge(int len, Edge left) {
            this.len = len;
            left.right = this;
            this.left = left;
        }

        @Override
        public int compareTo(Edge o) {
            return len - o.len;
        }
    }

    public static void main(String[] args) throws IOException {
        int n;
        int k;
        int s;
        int i;
        int ans;
        int prev;
        Edge edge;
        Edge left;
        Edge right;
        BufferedReader br;
        StringTokenizer st;
        PriorityQueue<Edge> pq;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), DELIM, false);
        n = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>(n << 1);
        prev = Integer.parseInt(br.readLine());
        left = NIL;
        for (i = 0; i < n; i++) {
            s = Integer.parseInt(br.readLine());
            edge = new Edge(s - prev, left);
            pq.offer(edge);
            prev = s;
            left = edge;
        }
        left.right = NIL;
        NIL.left = NIL;
        NIL.right = NIL;
        ans = 0;
        while (k-- > 0) {
            while ((edge = pq.poll()).disabled);
            ans += edge.len;
            left = edge.left;
            right = edge.right;
            if (left == NIL) {
                right.disabled = true;
                right.right.left = NIL;
            } else if (right == NIL) {
                left.disabled = true;
                left.left.right = NIL;
            } else {
                left.disabled = true;
                right.disabled = true;
                edge.len = edge.left.len + edge.right.len - edge.len;
                edge.left = edge.left.left;
                edge.right = edge.right.right;
                if (edge.left != NIL) {
                    edge.left.right = edge;
                }
                if (edge.right != NIL) {
                    edge.right.left = edge;
                }
                pq.offer(edge);
            }
        }
        System.out.print(ans);
    }
}
