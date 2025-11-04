class Solution {
    private static final int SIZE = 1_000_001;

    private static int[] same;
    private static int[] diff;
    private static int[] roots;
    private static boolean[] isSame;

    private static int find(int v) {
        if (roots[v] <= 0) {
            return v;
        }
        return roots[v] = find(roots[v]);
    }

    private static int addEdge(int v) {
        if (isSame[v]) {
            isSame[v] = false;
            v = find(v);
            same[v]--;
            diff[v]++;
        } else {
            isSame[v] = true;
            v = find(v);
            diff[v]--;
            same[v]++;
        }
        return v;
    }

    private static void union(int u, int v) {
        u = addEdge(u);
        v = addEdge(v);
        if (roots[u] > roots[v]) {
            roots[u] = v;
            same[v] += same[u];
            diff[v] += diff[u];
        } else {
            if (roots[u] == roots[v]) {
                roots[u]--;
            }
            roots[v] = u;
            same[u] += same[v];
            diff[u] += diff[v];
        }
    }

    public int[] solution(int[] nodes, int[][] edges) {
        int i;
        int len;
        int size;
        int[] idx;
        int[] ans;

        idx = new int[SIZE];
        len = nodes.length;
        same = new int[len + 1];
        diff = new int[len + 1];
        isSame = new boolean[len + 1];
        for (i = 1; i <= len; i++) {
            idx[nodes[i - 1]] = i;
            if ((nodes[i - 1] & 1) == 0) {
                same[i]++;
                isSame[i] = true;
            } else {
                diff[i]++;
            }
        }
        roots = new int[len + 1];
        size = edges.length;
        for (i = 0; i < size; i++) {
            union(idx[edges[i][0]], idx[edges[i][1]]);
        }
        ans = new int[2];
        for (i = 1; i <= len; i++) {
            if (roots[i] <= 0) {
                if (same[i] == 1) {
                    ans[0]++;
                }
                if (diff[i] == 1) {
                    ans[1]++;
                }
            }
        }
        return ans;
    }
}
