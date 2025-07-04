class Solution {
    private static int len;
    private static int[] diff;
    private static int[] time;
    private static int[] retry;
    private static long lmt;
    private static long tot;

    private static boolean validate(int lv) {
        int i;
        long sum;

        sum = tot;
        for (i = 0; i < len; i++) {
            if (diff[i] > lv && (sum += retry[i] * (diff[i] - lv)) > lmt) {
                return false;
            }
        }
        return true;
    }

    private static int lowerBound(int r) {
        int l;
        int mid;

        l = 1;
        while (l < r) {
            mid = l + r >>> 1;
            if (validate(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return r;
    }

    public int solution(int[] diffs, int[] times, long limit) {
        int i;
        int max;

        len = times.length;
        tot = times[0];
        max = diffs[0];
        retry = new int[len];
        for (i = 1; i < len; i++) {
            tot += times[i];
            max = Math.max(max, diffs[i]);
            retry[i] = times[i - 1] + times[i];
        }
        lmt = limit;
        diff = diffs;
        time = times;
        return lowerBound(max + 1);
    }
}
