import java.util.Arrays;

class Solution {
    private static final class Node implements Comparable<Node> {
        int x;
        int y;
        int idx;
        
        Node(int x, int y, int idx) {
            this.x = x;
            this.y = y;
            this.idx = idx;
        }
        
        @Override
        public int compareTo(Node o) {
            return x - o.x;
        }
    }
    
    private int preIdx;
    private int postIdx;
    private int[] pre;
    private int[] post;
    private Node[] arr;

    private void dfs(int from, int to) {
        int i;
        int mid;
        int max;
        int node;

        node = 0;
        mid = 0;
        max = -1;
        for (i = from; i <= to; i++) {
            if (arr[i].y > max) {
                mid = i;
                node = arr[i].idx;
                max = arr[i].y;
            }
        }
        pre[preIdx++] = node;
        if (from < mid) {
            dfs(from, mid - 1);
        }
        if (mid < to) {
            dfs(mid + 1, to);
        }
        post[postIdx++] = node;
    }

    public int[][] solution(int[][] nodeinfo) {
        int i;
        int len;

        len = nodeinfo.length;
        arr = new Node[len];
        for (i = 0; i < len; i++) {
            arr[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1);
        }
        Arrays.sort(arr, 0, len);
        pre = new int[len];
        post = new int[len];
        preIdx = 0;
        postIdx = 0;
        dfs(0, len - 1);
        return new int[][] {pre, post};
    }
}
