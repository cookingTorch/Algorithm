import java.util.Arrays;

class Solution {
    private static final long DIFF = 'a';
    private static final long ALPHA = 26L;

    private static long toNum(String str) {
        int i;
        int len;
        long res;

        res = 0L;
        len = str.length();
        for (i = 0; i < len; i++) {
            res = res * ALPHA + str.charAt(i) - DIFF + 1;
        }
        return res;
    }

    private static String toStr(long n) {
        int i;
        int len;
        long tmp;
        char[] res;

        len = 0;
        tmp = n;
        while (tmp > 0L) {
            tmp = (tmp - 1L) / ALPHA;
            len++;
        }
        res = new char[len];
        for (i = len - 1; i >= 0; i--) {
            res[i] = (char) (--n % ALPHA + DIFF);
            n /= ALPHA;
        }
        return new String(res);
    }

    public String solution(long n, String[] bans) {
        int i;
        int len;
        long[] arr;

        len = bans.length;
        arr = new long[len];
        for (i = 0; i < len; i++) {
            arr[i] = toNum(bans[i]);
        }
        Arrays.sort(arr, 0, len);
        for (i = 0; i < len; i++) {
            if (arr[i] > n) {
                break;
            }
            n++;
        }
        return toStr(n);
    }
}