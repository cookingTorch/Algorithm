import java.util.Arrays;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int i;
        int len;
        int cnt;
        int mid;
        int curr;
        int left;
        int right;
        int[] dists;

        Arrays.sort(rocks);
        len = rocks.length;
        dists = new int[len + 2];
        dists[0] = rocks[0];
        for (i = 1; i < len; i++) {
            dists[i] = rocks[i] - rocks[i - 1];
        }
        dists[len] = distance - rocks[len - 1];
        len++;
        left = 0;
        right = distance + 1;
        loop:
        while (left < right) {
            mid = left + right >> 1;
            cnt = 0;
            curr = dists[0];
            for (i = 1; i <= len; i++) {
                if (curr < mid) {
                    if (++cnt > n) {
                        right = mid;
                        continue loop;
                    }
                    curr += dists[i];
                } else {
                    curr = dists[i];
                }
            }
            left = mid + 1;
        }
        return right - 1;
    }
}
