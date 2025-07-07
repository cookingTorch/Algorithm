class Solution {
    private static final int INF = Integer.MAX_VALUE >>> 1;
    private static final int NUM = 10;
    private static final int DIFF = '0';
    private static final int[] NIL = new int[] {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF};
    private static final int[][] POS = {
            {3, 1},
            {0, 0}, {0, 1}, {0, 2},
            {1, 0}, {1, 1}, {1, 2},
            {2, 0}, {2, 1}, {2, 2},
    };

    public int solution(String numbers) {
        int i;
        int j;
        int x;
        int y;
        int nx;
        int ny;
        int len;
        int ans;
        int cur;
        int prev;
        int[] tmp;
        int[] curRes;
        int[] prevRes;
        char[] nums;

        nums = numbers.toCharArray();
        len = nums.length;
        prev = 4;
        prevRes = new int[NUM];
        System.arraycopy(NIL, 0, prevRes, 0, NUM);
        prevRes[6] = 0;
        curRes = new int[NUM];
        for (i = 0; i < len; i++) {
            cur = nums[i] - DIFF;
            if (cur == prev) {
                for (j = 0; j < NUM; j++) {
                    if (prevRes[j] != INF) {
                        prevRes[j]++;
                    }
                }
                continue;
            }
            nx = POS[cur][0];
            ny = POS[cur][1];
            System.arraycopy(NIL, 0, curRes, 0, NUM);
            for (j = 0; j < NUM; j++) {
                if (prevRes[j] == INF) {
                    continue;
                }
                if (j == cur) {
                    curRes[prev] = Math.min(curRes[prev], prevRes[j] + 1);
                    continue;
                }
                x = POS[j][0];
                y = POS[j][1];
                curRes[prev] = Math.min(curRes[prev], prevRes[j] + (Math.abs(nx - x) + Math.abs(ny - y) << 1) - Math.min(Math.abs(nx - x), Math.abs(ny - y)));
                x = POS[prev][0];
                y = POS[prev][1];
                curRes[j] = Math.min(curRes[j], prevRes[j] + (Math.abs(nx - x) + Math.abs(ny - y) << 1) - Math.min(Math.abs(nx - x), Math.abs(ny - y)));
            }
            prev = cur;
            tmp = prevRes;
            prevRes = curRes;
            curRes = tmp;
        }
        ans = prevRes[0];
        for (i = 1; i < NUM; i++) {
            ans = Math.min(ans, prevRes[i]);
        }
        return ans;
    }
}
