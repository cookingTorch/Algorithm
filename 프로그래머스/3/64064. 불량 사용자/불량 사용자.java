class Solution {
    private static final int NIL = -1;
    private static final char WILD = '*';

    private int banCnt;
    private int[] dp;
    private int[] cnts;
    private int[][] usrs;

    private boolean isConnected(char[] banId, char[] usrId) {
        int i;
        int len;

        if ((len = banId.length) != usrId.length) {
            return false;
        }
        for (i = 0; i < len; i++) {
            if (banId[i] != WILD && usrId[i] != banId[i]) {
                return false;
            }
        }
        return true;
    }

    private int getDp(int bit, int depth) {
        int i;
        int cnt;
        int ret;
        int[] usr;

        if (dp[bit] != NIL) {
            return dp[bit];
        }
        if (depth == banCnt) {
            return dp[bit] = 1;
        }
        cnt = cnts[depth];
        usr = usrs[depth];
        ret = 0;
        depth++;
        for (i = 0; i < cnt; i++) {
            if ((bit & usr[i]) == 0) {
                ret += getDp(bit | usr[i], depth);
            }
        }
        return dp[bit] = ret;
    }

    public int solution(String[] user_id, String[] banned_id) {
        int i;
        int j;
        int usrCnt;
        int bitField;
        char[][] usrId;
        char[][] banId;

        banCnt = banned_id.length;
        usrCnt = user_id.length;
        banId = new char[banCnt][];
        usrId = new char[usrCnt][];
        for (i = 0; i < banCnt; i++) {
            banId[i] = banned_id[i].toCharArray();
        }
        for (j = 0; j < usrCnt; j++) {
            usrId[j] = user_id[j].toCharArray();
        }
        cnts = new int[banCnt];
        usrs = new int[banCnt][usrCnt];
        for (i = 0; i < banCnt; i++) {
            for (j = 0; j < usrCnt; j++) {
                if (isConnected(banId[i], usrId[j])) {
                    usrs[i][cnts[i]++] = 1 << j;
                }
            }
        }
        bitField = 1 << usrCnt;
        dp = new int[bitField];
        for (i = 0; i < bitField; i++) {
            dp[i] = NIL;
        }
        return getDp(0, 0);
    }
}
