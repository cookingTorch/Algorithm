import java.util.StringTokenizer;

class Solution {
    private static final int MAX = 24 * 60;
    private static final int DIFF = '0' * 671;
    private static final int UPPER = 12;
    private static final int SHARP = '#';
    private static final String NONE = "(None)";
    private static final String DELIM = ",";

    private static int timeToInt(String time) {
        return time.charAt(0) * 600 + time.charAt(1) * 60 + time.charAt(3) * 10 + time.charAt(4) - DIFF;
    }

    private static int genPat(String str, int len, int[] pat) {
        int i;
        int ch;
        int idx;

        idx = 0;
        for (i = 0; i < len; i++) {
            if ((ch = str.charAt(i)) == SHARP) {
                pat[idx - 1] += UPPER;
            } else {
                pat[idx++] = ch;
            }
        }
        return idx;
    }

    private static void genTxt(String str, int[] txt, int txtLen) {
        int i;
        int ch;
        int len;
        int idx;
        int size;

        len = str.length();
        idx = 0;
        for (i = 0; i < len && idx < txtLen; i++) {
            ch = str.charAt(i);
            if (ch == SHARP) {
                txt[idx - 1] += UPPER;
            } else {
                txt[idx++] = ch;
            }
        }
        if (i < len && str.charAt(i) == SHARP) {
            txt[idx - 1] += UPPER;
        }
        for (size = idx; idx < txtLen; idx += size) {
            System.arraycopy(txt, 0, txt, idx, Math.min(size, txtLen - idx));
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
            } else if (len == 0) {
                i++;
            } else {
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
            } else if (j == 0) {
                i++;
            } else {
                j = lps[j - 1];
            }
        }
        return false;
    }

    public String solution(String m, String[] musicinfos) {
        int i;
        int max;
        int len;
        int patLen;
        int txtLen;
        int[] pat;
        int[] txt;
        int[] lps;
        String ans;
        String name;
        StringTokenizer st;

        pat = new int[m.length()];
        patLen = genPat(m, m.length(), pat);
        lps = genLps(pat, patLen);
        txt = new int[MAX];
        max = 0;
        ans = NONE;
        len = musicinfos.length;
        for (i = 0; i < len; i++) {
            st = new StringTokenizer(musicinfos[i], DELIM, false);
            if ((txtLen = -timeToInt(st.nextToken()) + timeToInt(st.nextToken())) < patLen) {
                continue;
            }
            name = st.nextToken();
            genTxt(st.nextToken(), txt, txtLen);
            if (kmp(pat, patLen, txt, txtLen, lps)) {
                if (txtLen > max) {
                    max = txtLen;
                    ans = name;
                }
            }
        }
        return ans;
    }
}
