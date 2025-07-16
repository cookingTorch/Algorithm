import java.util.StringTokenizer;

class Solution {
    private static final int MAX = 24 * 60;
    private static final int DIFF = 12;
    private static final int ZERO = '0';
    private static final int SHARP = '#';
    private static final String NONE = "(None)";
    private static final String DELIM = ",";

    private static int len;

    private static int getTime(String time) {
        return ((time.charAt(0) - ZERO) * 10 + time.charAt(1) - ZERO) * 60 + (time.charAt(3) - ZERO) * 10 + time.charAt(4) - ZERO;
    }

    private static int[] genMelody(String str, int size) {
        int i;
        int j;
        int ch;
        int[] melody;

        len = size;
        melody = new int[size];
        for (i = 0, j = 0; i < len; i++, j++) {
            ch = str.charAt(j);
            if (ch == SHARP) {
                melody[--i] += DIFF;
                len--;
            } else {
                melody[i] = ch;
            }
        }
        return melody;
    }

    private static void fillMusic(String str, int[] music, int time) {
        int i;
        int j;
        int ch;
        int len;

        len = str.length();
        for (i = 0, j = 0; i < time; i++, j++) {
            if (j == len) {
                j = 0;
            }
            ch = str.charAt(j);
            if (ch == SHARP) {
                music[--i] += DIFF;
            } else {
                music[i] = ch;
            }
        }
        if (j < str.length() && str.charAt(j) == SHARP) {
            music[time - 1] += DIFF;
        }
    }

    private static int[] genLps(int[] pat, int patLen) {
        int i;
        int len;
        int[] lps;

        i = 1;
        len = 0;
        lps = new int[patLen];
        while (i < patLen) {
            if (pat[i] == pat[len]) {
                len++;
                lps[i] = len;
                i++;
            }
            else if (len == 0) {
                i++;
            }
            else {
                len = lps[len - 1];
            }
        }
        return lps;
    }

    private static boolean kmp(int[] pat, int patLen, int[] txt, int txtLen, int[] lps) {
        int i;
        int j;

        i = 0;
        j = 0;
        while (i < txtLen) {
            if (txt[i] == pat[j]) {
                i++;
                if (++j == patLen) {
                    return true;
                }
            }
            else if (j == 0) {
                i++;
            }
            else {
                j = lps[j - 1];
            }
        }
        return false;
    }

    public String solution(String m, String[] musicinfos) {
        int max;
        int time;
        int[] lps;
        int[] music;
        int[] melody;
        String ans;
        String name;
        StringTokenizer st;

        melody = genMelody(m, m.length());
        lps = genLps(melody, len);
        music = new int[MAX];
        max = 0;
        ans = NONE;
        for (String musicinfo : musicinfos) {
            st = new StringTokenizer(musicinfo, DELIM, false);
            time = -getTime(st.nextToken()) + getTime(st.nextToken());
            if (time < len) {
                continue;
            }
            name = st.nextToken();
            fillMusic(st.nextToken(), music, time);
            if (kmp(melody, len, music, time, lps)) {
                if (time > max) {
                    max = time;
                    ans = name;
                }
            }
        }
        return ans;
    }
}
