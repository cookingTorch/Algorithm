class Solution {
    private int max;
    private int[] size;
    private int[][] adj;
    private boolean[] isSheep;

    private void dfs(int node, int state, int cnt, int[] prev, int idx) {
        int i;
        int len;
        int[] next;

        if (isSheep[node]) {
            state++;
            max = Math.max(max, ++cnt);
        } else if (--state <= 0) {
            return;
        }
        len = prev.length;
        next = new int[len + size[node] - 1];
        System.arraycopy(prev, 0, next, 0, idx);
        if (len > idx + 1) {
            System.arraycopy(prev, idx + 1, next, idx, len - idx - 1);
        }
        System.arraycopy(adj[node], 0, next, len - 1, size[node]);
        len += size[node] - 1;
        for (i = 0; i < len; i++) {
            dfs(next[i], state, cnt, next, i);
        }
    }

    public int solution(int[] info, int[][] edges) {
        int i;
        int len;

        len = info.length;
        isSheep = new boolean[len];
        for (i = 0; i < len; i++) {
            isSheep[i] = info[i] == 0;
        }
        adj = new int[len][len];
        size = new int[len];
        for (int[] edge : edges) {
            adj[edge[0]][size[edge[0]]++] = edge[1];
        }
        max = 0;
        dfs(0, 0, 0, new int[] {0}, 0);
        return max;
    }
}
