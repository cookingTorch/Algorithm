class Solution {
    private static final int PLUS = -1;

    private int len;
    private int maxCnt;
    private int maxSum;
    private int[] cost;
    private int[] emoticons;
    private int[][] users;

    private int pay(int[] user) {
        int i;
        int res;
        int thr;

        res = 0;
        thr = user[0];
        for (i = 0; i < len; i++) {
            if (cost[i] <= thr) {
                res += emoticons[i] * cost[i];
            }
        }
        return res >= user[1] ? PLUS : res;
    }

    private void calc() {
        int res;
        int sum;
        int cnt;

        sum = 0;
        cnt = 0;
        for (int[] user : users) {
            res = pay(user);
            if (res == PLUS) {
                cnt++;
            } else {
                sum += res;
            }
        }
        if (cnt > maxCnt) {
            maxCnt = cnt;
            maxSum = sum;
        } else if (cnt == maxCnt && sum > maxSum) {
            maxSum = sum;
        }
    }

    private void dfs(int depth) {
        int i;

        if (depth == len) {
            calc();
            return;
        }
        for (i = 90; i >= 60; i -= 10) {
            cost[depth] = i;
            dfs(depth + 1);
        }
    }

    public int[] solution(int[][] users, int[] emoticons) {
        int i;

        this.users = users;
        this.emoticons = emoticons;
        len = emoticons.length;
        for (int[] user : users) {
            user[0] = 100 - user[0];
        }
        for (i = 0; i < len; i++) {
            emoticons[i] /= 100;
        }
        cost = new int[len];
        maxCnt = 0;
        maxSum = 0;
        dfs(0);
        return new int[] {maxCnt, maxSum};
    }
}
