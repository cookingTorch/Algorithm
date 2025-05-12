class Solution {
    private static final int CONNECTED = 1;

    private static int[] roots;

    private static int find(int v) {
        if (roots[v] <= 0) {
            return v;
        }
        return roots[v] = find(roots[v]);
    }

    private static boolean union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) {
            return false;
        }
        if (roots[u] > roots[v]) {
            roots[u] = v;
        } else {
            if (roots[u] == roots[v]) {
                roots[u]--;
            }
            roots[v] = u;
        }
        return true;
    }

    public int solution(int n, int[][] computers) {
        int i;
        int j;
        int cnt;

        cnt = n;
        roots = new int[n + 1];
        for (i = 0; i < n; i++) {
            for (j = 0; j < i; j++) {
                if (computers[i][j] == CONNECTED && union(i + 1, j + 1)) {
                    cnt--;
                }
            }
        }
        return cnt;
    }
}