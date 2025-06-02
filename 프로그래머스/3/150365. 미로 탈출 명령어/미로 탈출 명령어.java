class Solution {
    private static final int[] dx = {1, 0, 0, -1};
    private static final int[] dy = {0, -1, 1, 0};
    private static final char[] ch = {'d', 'l', 'r', 'u'};
    private static final String IMPOSSIBLE = "impossible";

    private int n;
    private int m;
    private int r;
    private int c;
    private int k;
    private char[] ans;

    private boolean dfs(int x, int y, int depth) {
        int i;
        int nx;
        int ny;

        if (Math.abs(r - x) + Math.abs(c - y) > k - depth) {
            return false;
        }
        if (depth == k) {
            return true;
        }
        for (i = 0; i < 4; i++) {
            nx = x + dx[i];
            ny = y + dy[i];
            if (nx < 1 || nx > n || ny < 1 || ny > m) {
                continue;
            }
            ans[depth] = ch[i];
            if (dfs(nx, ny, depth + 1)) {
                return true;
            }
        }
        return false;
    }

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n;
        this.m = m;
        this.r = r;
        this.c = c;
        this.k = k;
        ans = new char[k];
        return ((Math.abs(r - x) + Math.abs(c - y) ^ k) & 1) == 0 && dfs(x, y, 0) ? new String(ans) : IMPOSSIBLE;
    }
}
