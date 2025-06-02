class Solution {
    private static final char COLON = ':';
    private static final String FORMAT = "%02d:%02d:%02d";

    private int getSec(String s, int i) {
        return (((s.charAt(i) - '0') * 10 + s.charAt(i + 1) - '0') * 60 + (s.charAt(i + 3) - '0') * 10 + s.charAt(i + 4) - '0') * 60 + (s.charAt(i + 6) - '0') * 10 + s.charAt(i + 7) - '0';
    }

    public String solution(String play_time, String adv_time, String[] logs) {
        int l;
        int r;
        int i;
        int ans;
        int advTime;
        int playTime;
        int[] diff;
        long sum;
        long max;

        playTime = getSec(play_time, 0);
        diff = new int[playTime + 1];
        for (String log : logs) {
            diff[getSec(log, 0)]++;
            diff[getSec(log, 9)]--;
        }
        advTime = getSec(adv_time, 0);
        sum = 0L;
        r = 0;
        for (i = 0; i < advTime; i++) {
            sum += r += diff[i];
        }
        max = sum;
        ans = 0;
        l = 0;
        for (; i < playTime; i++) {
            if ((sum += (r += diff[i]) - (l += diff[i - advTime])) > max) {
                max = sum;
                ans = i - advTime + 1;
            }
        }
        return String.format(FORMAT, ans / 3600, ans % 3600 / 60, ans % 60);
    }
}
