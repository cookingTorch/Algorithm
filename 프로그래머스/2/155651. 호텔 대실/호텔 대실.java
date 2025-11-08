import java.util.Arrays;

class Solution {
    private static final int DIFF = '0';

    private static int timeToInt(String str) {
        return (str.charAt(0) - DIFF) * 600 + (str.charAt(1) - DIFF) * 60 + (str.charAt(3) - DIFF) * 10 + (str.charAt(4) - DIFF);
    }

    public int solution(String[][] book_time) {
        int i;
        int len;
        int cur;
        int max;
        int size;
        int[] arr;

        len = book_time.length;
        size = len << 1;
        arr = new int[size];
        for (i = 0; i < len; i++) {
            arr[i] = timeToInt(book_time[i][0]) << 1 | 1;
            arr[len + i] = timeToInt(book_time[i][1]) + 10 << 1;
        }
        Arrays.sort(arr, 0, size);
        cur = 0;
        max = 0;
        for (i = 0; i < size; i++) {
            if ((arr[i] & 1) == 0) {
                max = Math.max(max, cur--);
            } else {
                cur++;
            }
        }
        return max;
    }
}