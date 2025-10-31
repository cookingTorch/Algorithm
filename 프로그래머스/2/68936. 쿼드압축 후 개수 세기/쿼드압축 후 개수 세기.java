class Solution {
    private static final int ONE = (1 << 4) - 1;
    private static final int ZERO = 0;
    private static final int FAIL = -1;

    private static int[] ans;
    private static int[][] map;

    private static int dfs(int x, int y, int size) {
        int res;

        if (size == 1) {
            ans[map[x][y]]++;
            return map[x][y];
        }
        size >>= 1;
        res = ((dfs(x, y, size) << 1 | dfs(x, y + size, size)) << 1 | dfs(x + size, y, size)) << 1 | dfs(x + size, y + size, size);
        if (res == ZERO) {
            ans[0] -= 3;
            return 0;
        } else if (res == ONE) {
            ans[1] -= 3;
            return 1;
        } else {
            return FAIL;
        }
    }

    public int[] solution(int[][] arr) {
        map = arr;
        ans = new int[2];
        dfs(0, 0, arr.length);
        return ans;
    }
}
