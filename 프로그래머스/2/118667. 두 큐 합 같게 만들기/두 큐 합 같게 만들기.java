class Solution {
    private static final int FAIL = -1;

    public int solution(int[] queue1, int[] queue2) {
        int l;
        int r;
        int i;
        int len;
        int thr;
        int[] arr;
        long diff;

        len = queue1.length;
        arr = new int[len << 1];
        System.arraycopy(queue1, 0, arr, 0, len);
        System.arraycopy(queue2, 0, arr, len, len);
        diff = 0L;
        for (i = 0; i < len; i++) {
            diff += queue1[i];
        }
        for (i = 0; i < len; i++) {
            diff -= queue2[i];
        }
        if ((diff & 1) != 0) {
            return FAIL;
        }
        l = 0;
        r = len;
        thr = len * 3;
        len <<= 1;
        for (i = 0; i < thr; i++) {
            if (diff > 0L) {
                diff -= arr[l++] << 1;
                if (l == len) {
                    l = 0;
                }
            } else if (diff < 0L) {
                diff += arr[r++] << 1;
                if (r == len) {
                    r = 0;
                }
            } else {
                break;
            }
        }
        return i == thr ? FAIL : i;
    }
}
