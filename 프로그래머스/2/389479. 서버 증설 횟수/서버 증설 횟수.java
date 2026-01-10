class Solution {
    public int solution(int[] players, int m, int k) {
        int i;
        int on;
        int len;
        int cnt;
        int[] off;

        on = 0;
        cnt = 0;
        len = players.length;
        off = new int[len + k];
        for (i = 0; i < len; i++) {
            on -= off[i];
            on += off[i + k] = Math.max(0, players[i] / m - on);
            cnt += off[i + k];
        }
        return cnt;
    }
}
