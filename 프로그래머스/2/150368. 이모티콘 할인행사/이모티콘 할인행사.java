class Solution {
    private static final int PLUS = -1;

    private static final class User {
        int ratio;
        int cost;

        User(int ratio, int cost) {
            this.ratio = ratio;
            this.cost = cost;
        }

        int pay() {
            int i;
            int res;

            res = 0;
            for (i = 0; i < len; i++) {
                if (discount[i] >= ratio) {
                    res += emoticons[i] / 100 * (100 - discount[i]);
                }
            }
            return res >= cost ? PLUS : res;
        }
    }

    private static int len;
    private static int[] discount;
    private static int[] emoticons;

    private int maxCnt;
    private int maxSum;
    private User[] users;
    
    private void calc() {
        int res;
        int sum;
        int cnt;

        sum = 0;
        cnt = 0;
        for (User user : users) {
            res = user.pay();
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
        for (i = 10; i <= 40; i += 10) {
            discount[depth] = i;
            dfs(depth + 1);
        }
    }

    public int[] solution(int[][] users, int[] emoticons) {
        int i;
        int size;

        this.emoticons = emoticons;
        len = emoticons.length;
        discount = new int[len];
        size = users.length;
        this.users = new User[size];
        for (i = 0; i < size; i++) {
            this.users[i] = new User(users[i][0], users[i][1]);
        }
        maxCnt = 0;
        maxSum = 0;
        dfs(0);
        return new int[] {maxCnt, maxSum};
    }
}
