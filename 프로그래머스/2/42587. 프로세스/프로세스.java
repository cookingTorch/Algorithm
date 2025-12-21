class Solution {
    private static final int RANGE = 10;

    public int solution(int[] a, int t) {
        int i;
        int len;
        int num;
        int max;
        int idx;
        int rank;
        int target;
        int[] cnt;
        int[] pos;
        int[] first;
        int[] lower;
        int[] prevSame;
        int[] nextLower;

        target = a[t];
        cnt = new int[RANGE];
        first = new int[RANGE];
        len = a.length;
        for (i = len - 1; i >= 0; i--) {
            num = a[i];
            if (num >= target) {
                first[num] = i;
                cnt[num]++;
            }
        }
        lower = new int[RANGE];
        max = target;
        for (num = target + 1; num < RANGE; num++) {
            if (cnt[num] != 0) {
                lower[num] = max;
                max = num;
            }
        }
        idx = cnt[target] - 1;
        pos = new int[len];
        prevSame = new int[len];
        nextLower = new int[len];
        for (i = len - 1; i >= 0; i--) {
            num = a[i];
            if (num == target) {
                pos[i] = idx--;
                first[num] = i;
            } else if (num > target) {
                prevSame[first[num]] = i;
                nextLower[i] = first[lower[num]];
                first[num] = i;
            }
        }
        rank = 1;
        i = first[max];
        for (num = max; num > target; num = lower[num]) {
            i = nextLower[prevSame[i]];
            rank += cnt[num];
        }
        return rank + (pos[t] - pos[i] + cnt[target]) % cnt[target];
    }
}