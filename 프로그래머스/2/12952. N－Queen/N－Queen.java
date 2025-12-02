class Solution {
    private static int cnt;
    private static int size;
    private static int range;

    private static void dfs(int l, int m, int r, int depth) {
        int e;
        int i;

        if (depth == size) {
            cnt++;
            return;
        }
        e = range & ~(l | m | r);
        for (i = e & -e; e != 0; e ^= i, i = e & -e) {
            dfs((l | i) << 1, m | i, (r | i) >> 1, depth + 1);
        }
    }

    public int solution(int n) {
        int i;
        int mid;

        cnt = 0;
        size = n;
        range = (1 << n) - 1;
        mid = 1 << (n >> 1);
        for (i = 1; i < mid; i++) {
            dfs(i << 1, i, i >> 1, 1);
        }
        cnt <<= 1;
        if ((n & 1) != 0) {
            dfs(mid << 1, mid, mid >> 1, 1);
        }
        return cnt;
    }
}
