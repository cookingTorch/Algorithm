class Solution {
    private static final int LEN = 24;

    public int solution(int[] players, int m, int k) {
        int i;
        int cnt;
        int cur;
        int player;
        int[] servers;

        cnt = 0;
        cur = 0;
        servers = new int[LEN + k];
        for (i = 0; i < LEN; i++) {
            if ((player = players[i] / m) > (cur -= servers[i])) {
                player -= cur;
                servers[i + k] = player;
                cur += player;
                cnt += player;
            }
        }
        return cnt;
    }
}
