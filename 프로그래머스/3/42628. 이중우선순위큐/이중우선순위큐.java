import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution {
    private static final char MAX = '1';
    private static final char INSERT = 'I';
    private static final String DELIM = " ";

    private final Node MAX_NIL = new Node(Integer.MIN_VALUE >> 1);
    private final Node MIN_NIL = new Node(Integer.MAX_VALUE >>> 1);

    private final class Node implements Comparable<Node> {
        boolean del;
        int val;

        Node(int val) {
            this.val = val;
        }

        @Override
        public int compareTo(Node o) {
            return o.val - val;
        }
    }

    public int[] solution(String[] operations) {
        int i;
        int len;
        int[] ans;
        Node node;
        StringTokenizer st;
        PriorityQueue<Node> maxPq;
        PriorityQueue<Node> minPq;

        len = operations.length;
        maxPq = new PriorityQueue<>(len + 1);
        minPq = new PriorityQueue<>(len + 1, (o1, o2) -> o1.val - o2.val);
        maxPq.add(MAX_NIL);
        minPq.add(MIN_NIL);
        for (i = 0; i < len; i++) {
            st = new StringTokenizer(operations[i], DELIM, false);
            if (st.nextToken().charAt(0) == INSERT) {
                node = new Node(Integer.parseInt(st.nextToken()));
                minPq.offer(node);
                maxPq.offer(node);
            } else if (st.nextToken().charAt(0) == MAX) {
                do {
                    node = maxPq.poll();
                } while (node.del);
                if (node == MAX_NIL) {
                    maxPq.offer(node);
                } else {
                    node.del = true;
                }
            } else {
                do {
                    node = minPq.poll();
                } while (node.del);
                if (node == MIN_NIL) {
                    minPq.offer(node);
                } else {
                    node.del = true;
                }
            }
        }
        ans = new int[2];
        do {
            node = maxPq.poll();
        } while (node.del);
        if (node == MAX_NIL) {
            return ans;
        }
        ans[0] = node.val;
        do {
            node = minPq.poll();
        } while (node.del);
        ans[1] = node.val;
        return ans;
    }
}
