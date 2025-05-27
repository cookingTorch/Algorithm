import java.util.Arrays;

class Solution {
    private static final int START = 540;

    private int toMinute(String time) {
        return ((time.charAt(0) - '0') * 10 + time.charAt(1) - '0') * 60 + (time.charAt(3) - '0') * 10 + time.charAt(4) - '0';
    }

    private String toTime(int minute) {
        return String.format("%02d:%02d", minute / 60, minute % 60);
    }

    private int upperBound(int[] arr, int left, int right, int key) {
        int mid;

        while (left < right) {
            mid = left + right >>> 1;
            if (arr[mid] > key) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    public String solution(int n, int t, int m, String[] timetable) {
        int i;
        int len;
        int ans;
        int time;
        int left;
        int last;
        int[] timeTable;

        len = timetable.length;
        timeTable = new int[len];
        for (i = 0; i < len; i++) {
            timeTable[i] = toMinute(timetable[i]);
        }
        Arrays.sort(timeTable, 0, len);
        n--;
        left = 0;
        for (i = 0, time = START; i < n; i++, time += t) {
            left = Math.min(left + m, upperBound(timeTable, left, len, time));
        }
        last = Math.min(left + m, upperBound(timeTable, left, len, time)) - 1;
        if (last < left + m - 1) {
            ans = time;
        } else {
            ans = timeTable[last] - 1;
        }
        return toTime(ans);
    }
}
