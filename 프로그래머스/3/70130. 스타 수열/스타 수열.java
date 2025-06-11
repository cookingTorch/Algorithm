class Solution {
    private static final class Star {
        static int max;

        int cnt;
        int last;

        void add(int idx) {
            if (idx - 1 == last) {
                return;
            }
            last = idx;
            if (++cnt > max) {
                max = cnt;
            }
        }
    }

    public int solution(int[] a) {
        int i;
        int len;
        Star[] arr;

        len = a.length;
        if (len == 1) {
            return 0;
        }
        arr = new Star[len];
        for (i = 0; i < len; i++) {
            arr[i] = new Star();
        }
        arr[a[0]].last--;
        arr[a[1]].last--;
        for (i = 1; i < len; i++) {
            if (a[i - 1] != a[i]) {
                arr[a[i - 1]].add(i);
                arr[a[i]].add(i);
            }
        }
        return Star.max << 1;
    }
}
