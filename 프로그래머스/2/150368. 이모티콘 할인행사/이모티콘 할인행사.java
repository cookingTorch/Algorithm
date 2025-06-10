class Solution {
    private int len;
    private int maxCnt;
    private int maxSum;
    private int[] discount;
    private int[] emoticons;
    private int[][] users;

    private void calc() {
        int i;
        int res;
        int sum;
        int cnt;
        int ratio;

        sum = 0;
        cnt = 0;
        for (int[] user : users) {
            ratio = user[0];
            res = 0;
            for (i = 0; i < len; i++) {
                if (discount[i] >= ratio) {
                    res += emoticons[i] / 100 * (100 - discount[i]);
                }
            }
            if (res >= user[1]) {
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
        for (i = 10; i <= 40; i += 10) {
            discount[depth] = i;
            dfs(depth + 1);
        }
    }

    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;
        len = emoticons.length;
        discount = new int[len];
        maxCnt = 0;
        maxSum = 0;
        dfs(0);
        return new int[] {maxCnt, maxSum};
    }
}
