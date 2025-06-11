class Solution {
    private static int max;

    private void add(int[] star, int idx) {
        if (idx - 1 == star[0]) {
            return;
        }
        star[0] = idx;
        max = Math.max(max, ++star[1]);
    }

    public int solution(int[] a) {
        int i;
        int len;
        int[][] stars;

        len = a.length;
        if (len == 1) {
            return 0;
        }
        max = 0;
        stars = new int[len][2];
        stars[a[0]][0]--;
        stars[a[1]][0]--;
        for (i = 1; i < len; i++) {
            if (a[i - 1] != a[i]) {
                add(stars[a[i - 1]], i);
                add(stars[a[i]], i);
            }
        }
        return max << 1;
    }
}
