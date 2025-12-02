class Solution {
    private static int cnt;
    private static int size;
    private static int range;

    private static void dfs(int l, int m, int r, int depth) {
        int i;
        int e;

        if (depth == size) {
            cnt++;
            return;
        }
        l = l << 1 & range;
        r >>= 1;
        e = range ^ (l | m | r);
        for (i = e & -e; e != 0; e ^= i, i = e & -e) {
            dfs(l | i, m | i, r | i, depth + 1);
        }
    }

    public int solution(int n) {
        cnt = 0;
        size = n;
        range = (1 << n) - 1;
        dfs(0, 0, 0, 0);
        return cnt;
    }
}
