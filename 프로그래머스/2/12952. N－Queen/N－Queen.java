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
        e = ~(l | m | r) & range;
        for (i = e & -e; e != 0; e ^= i, i = e & -e) {
            dfs((l | i) << 1, m | i, (r | i) >>> 1, depth + 1);
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