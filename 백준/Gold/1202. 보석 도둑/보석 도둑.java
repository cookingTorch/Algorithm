import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static final int FULL = -1;

    private static final class Jewel implements Comparable<Jewel> {
        int weight;
        int val;

        Jewel(int weight, int val) {
            this.weight = weight;
            this.val = val;
        }

        @Override
        public int compareTo(Jewel o) {
            return Integer.compare(o.val, this.val);
        }
    }

    private static int[] caps;
    private static int[] tree;

    private static int initTree(int node, int start, int end) {
        int mid;

        if (start == end) {
            return tree[node] = caps[start];
        }
        mid = start + end >> 1;
        return tree[node] = Math.max(
                initTree(node << 1, start, mid),
                initTree(node << 1 | 1, mid + 1, end));
    }

    private static void update(int node, int start, int end, int weight) {
        int mid;

        if (start == end) {
            tree[node] = FULL;
            return;
        }
        mid = start + end >> 1;
        if (weight <= tree[node << 1]) {
            update(node << 1, start, mid, weight);
        } else {
            update(node << 1 | 1, mid + 1, end, weight);
        }
        tree[node] = Math.max(tree[node << 1], tree[node << 1 | 1]);
    }

    public static void main(String[] args) throws IOException {
        int n;
        int k;
        int i;
        long ans;
        Jewel jewel;
        BufferedReader br;
        StringTokenizer st;
        PriorityQueue<Jewel> pq;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>();
        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            pq.offer(new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        caps = new int[k];
        for (i = 0; i < k; i++) {
            caps[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(caps);
        tree = new int[k-- << 2];
        initTree(1, 0, k);
        ans = 0L;
        while (!pq.isEmpty()) {
            jewel = pq.poll();
            if (jewel.weight > tree[1]) {
                continue;
            }
            update(1, 0, k, jewel.weight);
            ans += jewel.val;
        }
        System.out.print(ans);
    }
}
