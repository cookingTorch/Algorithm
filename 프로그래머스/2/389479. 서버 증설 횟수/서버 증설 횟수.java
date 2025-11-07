class Solution {
    private static final int DAY = 24;

    public int solution(int[] players, int m, int k) {
        int i;
        int cnt;
        int cur;
        int[] off;

        cnt = 0;
        cur = 0;
        off = new int[DAY << 1];
        for (i = 0; i < DAY; i++) {
            cur -= off[i];
            cur += off[i + k] = Math.max(0, players[i] / m - cur);
            cnt += off[i + k];
        }
        return cnt;
    }
}
