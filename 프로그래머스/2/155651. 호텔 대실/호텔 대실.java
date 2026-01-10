class Solution {
    private static final int DIFF = '0' * 671;
    private static final int SIZE = timeToInt("23:59");
    
    private static int timeToInt(String time) {
        return time.charAt(0) * 600 + time.charAt(1) * 60 + time.charAt(3) * 10 + time.charAt(4) - DIFF;
    }

    public int solution(String[][] book_time) {
        int i;
        int len;
        int max;
        int[] cnt;
        
        cnt = new int[SIZE + 11];
        len = book_time.length;
        for (i = 0; i < len; i++) {
            cnt[timeToInt(book_time[i][0])]++;
            cnt[timeToInt(book_time[i][1]) + 10]--;
        }
        max = cnt[0];
        for (i = 1; i < SIZE; i++) {
            max = Math.max(max, cnt[i] += cnt[i - 1]);
        }
        return max;
    }
}
