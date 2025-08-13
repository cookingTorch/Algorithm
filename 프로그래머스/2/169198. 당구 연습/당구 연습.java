class Solution {
    private static int x;
    private static int y;
    private static int mm;
    private static int nn;

    private static int dist(int ox, int oy) {
        return (x - ox) * (x - ox) + (y - oy) * (y - oy);
    }

    private static int getDist(int ox, int oy) {
        if ((oy != y && x + ox < mm - ox - x) || (oy == y && ox > x)) {
            if ((ox != x && y + oy < nn - oy - y) || (ox == x && oy > y)) {
                return Math.min(dist(-ox, oy), dist(ox, -oy));
            }
            return Math.min(dist(-ox, oy), dist(ox, nn - oy));
        }
        if ((ox != x && y + oy < nn - oy - y) || (ox == x && oy > y)) {
            return Math.min(dist(mm - ox, oy), dist(ox, -oy));
        }
        return Math.min(dist(mm - ox, oy), dist(ox, nn - oy));
    }

    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int i;
        int len;
        int[] ans;

        x = startX;
        y = startY;
        mm = m << 1;
        nn = n << 1;
        len = balls.length;
        ans = new int[len];
        for (i = 0; i < len; i++) {
            ans[i] = getDist(balls[i][0], balls[i][1]);
        }
        return ans;
    }
}
