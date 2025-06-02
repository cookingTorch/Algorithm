class Solution {
    private static final char ONEONEZERO = '\0';
    private static final char ZERO = '0';
    private static final char ONE = '1';

    public String[] solution(String[] s) {
        int i;
        int j;
        int idx;
        int cnt;
        int len;
        int ones;
        int size;
        int last;
        char[] arr;
        String[] ans;

        size = s.length;
        ans = new String[size];
        for (i = 0; i < size; i++) {
            arr = s[i].toCharArray();
            len = arr.length;
            ones = 0;
            cnt = 0;
            last = -1;
            for (j = 0; j < len; j++) {
                if (arr[j] == ONE) {
                    ones++;
                } else {
                    if (ones >= 2) {
                        arr[j] = ONE;
                        arr[j - ones + 2] = ZERO;
                        arr[j - ones] = ONEONEZERO;
                        cnt++;
                        ones -= 2;
                    } else {
                        last = j;
                        ones = 0;
                    }
                }
            }
            idx = 0;
            for (j = 0; j <= last; j++) {
                if (arr[j] == ONEONEZERO) {
                    j += 2;
                    continue;
                }
                arr[idx++] = arr[j];
            }
            while (cnt-- > 0) {
                arr[idx++] = ONE;
                arr[idx++] = ONE;
                arr[idx++] = ZERO;
            }
            while (idx < len) {
                arr[idx++] = ONE;
            }
            ans[i] = new String(arr);
        }
        return ans;
    }
}