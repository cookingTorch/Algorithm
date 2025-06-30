import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    private static final char A = 'A';
    private static final char Z = 'Z';
    private static final char a = 'a';
    private static final char z = 'z';
    private static final char NULL = '\0';
    private static final char BRACE = '>';
    private static final char QUOTE = '"';
    private static final char[] META = "<meta ".toCharArray();
    private static final char[] HREF = "<a href=\"".toCharArray();
    private static final char[] CONTENT = "content=\"".toCharArray();
    private static final int NONE = -1;
    private static final int DIFF = a - A;
    private static final int MAX_LEN = 15_001;
    private static final int META_LEN = META.length;
    private static final int HREF_LEN = HREF.length;
    private static final int CONTENT_LEN = CONTENT.length;

    private static int strLen;
    private static int pageLen;
    private static int strStart;
    private static char[] str;
    private static char[] page;

    private static char lower(char ch) {
        if (ch < a) {
            return (char) (ch + DIFF);
        }
        return ch;
    }

    private static boolean isLetter(char ch) {
        return (A <= ch && ch <= Z) || (a <= ch && ch <= z);
    }

    private static boolean isStr(int i) {
        int j;

        if (strStart == NONE) {
            if (isLetter(page[i])) {
                strStart = i;
            }
            return false;
        } else if (!isLetter(page[i])) {
            if (i - strStart != strLen) {
                strStart = NONE;
                return false;
            }
            i = strStart;
            strStart = NONE;
            for (j = 0; j < strLen; j++) {
                if (lower(page[i + j]) != str[j]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static boolean isPattern(int i, char[] pat, int patLen) {
        int j;

        if (i + patLen > pageLen) {
            return false;
        }
        for (j = 0; j < patLen; j++) {
            if (page[i + j] != pat[j]) {
                return false;
            }
        }
        return true;
    }

    private static String getUrl(int i) {
        StringBuilder sb;

        sb = new StringBuilder();
        for (; i < pageLen; i++) {
            if (page[i] == QUOTE) {
                break;
            }
            sb.append(page[i]);
        }
        return sb.toString();
    }

    public int solution(String word, String[] pages) {
        int i;
        int j;
        int idx;
        int ans;
        int pagesLen;
        int[] strCnt;
        double max;
        double linkScore;
        double[] scores;
        ArrayList<String>[] links;
        HashMap<String, Integer> map;

        str = word.toCharArray();
        strLen = str.length;
        for (i = 0; i < strLen; i++) {
            str[i] = lower(str[i]);
        }
        pagesLen = pages.length;
        strCnt = new int[pagesLen];
        links = new ArrayList[pagesLen];
        map = new HashMap<>();
        page = new char[MAX_LEN];
        for (idx = 0; idx < pagesLen; idx++) {
            pageLen = pages[idx].length();
            pages[idx].getChars(0, pageLen, page, 0);
            page[pageLen++] = NULL;
            links[idx] = new ArrayList<>();
            strStart = NONE;
            for (i = 0; i < pageLen; i++) {
                if (isStr(i)) {
                    strCnt[idx]++;
                }
                if (isPattern(i, META, META_LEN)) {
                    for (j = i + META_LEN; j < pageLen; j++) {
                        if (page[j] == BRACE) {
                            break;
                        }
                        if (isPattern(j, CONTENT, CONTENT_LEN)) {
                            map.put(getUrl(j + CONTENT_LEN), idx);
                            break;
                        }
                    }
                }
                if (isPattern(i, HREF, HREF_LEN)) {
                    links[idx].add(getUrl(i + HREF_LEN));
                }
            }
        }
        scores = new double[pagesLen];
        for (idx = 0; idx < pagesLen; idx++) {
            scores[idx] += strCnt[idx];
            linkScore = (double) strCnt[idx] / links[idx].size();
            for (String link : links[idx]) {
                if (map.containsKey(link)) {
                    scores[map.get(link)] += linkScore;
                }
            }
        }
        ans = 0;
        max = 0.0;
        for (idx = 0; idx < pagesLen; idx++) {
            if (scores[idx] > max) {
                ans = idx;
                max = scores[idx];
            }
        }
        return ans;
    }
}
