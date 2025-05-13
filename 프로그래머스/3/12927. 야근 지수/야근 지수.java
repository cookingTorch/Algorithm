class Solution {
    public long solution(int n, int[] works) {
        int i;
        int l;
        int r;
        int len;
        int mid;
        int sum;
        int work;
        long ans;

        len = works.length;
        r = 0;
        sum = 0;
        for (i = 0; i < len; i++) {
            sum += works[i];
            r = Math.max(r, works[i]);
        }
        if (n >= sum) {
            return 0L;
        }
        work = 0;
        l = 0;
        r++;
        while (l < r) {
            mid = l + r >>> 1;
            sum = 0;
            for (i = 0; i < len; i++) {
                if (works[i] > mid) {
                    sum += works[i] - mid;
                }
            }
            if (sum > n) {
                l = mid + 1;
            } else {
                r = mid;
                work = sum;
            }
        }
        ans = 0L;
        for (i = 0; i < len; i++) {
            if (works[i] >= r) {
                if (work == n) {
                    ans += (long) r * r;
                } else {
                    ans += (long) (r - 1) * (r - 1);
                    work++;
                }
            } else {
                ans += (long) works[i] * works[i];
            }
        }
        return ans;
    }
}
