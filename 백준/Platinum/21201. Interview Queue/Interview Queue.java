import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static final char SPACE = ' ';
    private static final char LINE_FEED = '\n';
    private static final String DELIM = " ";

    private static final class Node {
        int val;
        boolean deleted;
        boolean inQueue;
        Node prev;
        Node next;

        Node() {
            deleted = true;
        }

        Node(int val, Node prev) {
            this.val = val;
            prev.next = this;
            this.prev = prev;
        }

        void delete() {
            prev.next = next;
            next.prev = prev;
        }
    }

    public static void main(String[] args) throws IOException {
        int n;
        int i;
        int size;
        Node node;
        Node head;
        ArrayDeque<Node> q;
        ArrayList<Node> list;
        ArrayDeque<ArrayList<Node>> ans;
        StringBuilder sb;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), DELIM, false);
        head = node = new Node();
        for (i = 0; i < n; i++) {
            node = new Node(Integer.parseInt(st.nextToken()), node);
        }
        node.next = head;
        head.prev = node;
        ans = new ArrayDeque<>();
        q = new ArrayDeque<>();
        for (node = head.next; node != head; node = node.next) {
            q.addLast(node);
        }
        for (;;) {
            list = new ArrayList<>();
            while (!q.isEmpty()) {
                node = q.pollFirst();
                node.inQueue = false;
                if (node.prev.val > node.val || node.val < node.next.val) {
                    list.add(node);
                    node.deleted = true;
                }
            }
            if (list.isEmpty()) {
                break;
            }
            for (Node e : list) {
                e.delete();
                node = e.prev;
                if (!node.deleted && !node.inQueue) {
                    q.addLast(node);
                    node.inQueue = true;
                }
                node = e.next;
                if (!node.deleted && !node.inQueue) {
                    q.addLast(node);
                    node.inQueue = true;
                }
            }
            ans.addLast(list);
        }
        sb = new StringBuilder();
        sb.append(ans.size()).append(LINE_FEED);
        while (!ans.isEmpty()) {
            list = ans.pollFirst();
            size = list.size();
            sb.append(list.get(0).val);
            for (i = 1; i < size; i++) {
                sb.append(SPACE).append(list.get(i).val);
            }
            sb.append(LINE_FEED);
        }
        for (node = head.next; node != head; node = node.next) {
            sb.append(node.val).append(SPACE);
        }
        System.out.print(sb.toString());
    }
}
