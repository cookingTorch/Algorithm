class Solution {
    public int solution(String s) {
        int i;
        int j;
        int k;
        int max;
        int thr;
        int cnt;
        int len;
        int ans;
        int size;
        int prev;
        char[] str;

        str = s.toCharArray();
        size = str.length;
        ans = size;
        max = size >>> 1;
        for (i = 1; i <= max; i++) {
            thr = size - size % i;
            len = size;
            cnt = 1;
            prev = 0;
            for (j = i; j < thr; j += i) {
                for (k = 0; k < i; k++) {
                    if (str[prev + k] != str[j + k]) {
                        break;
                    }
                }
                if (k == i) {
                    cnt++;
                } else {
                    if (cnt > 1) {
                        len += (cnt < 10 ? 1 : cnt < 100 ? 2 : cnt < 1000 ? 3 : 4) - (cnt - 1) * i;
                        cnt = 1;
                    }
                    prev = j;
                }
            }
            if (cnt > 1) {
                len += (cnt < 10 ? 1 : cnt < 100 ? 2 : cnt < 1000 ? 3 : 4) - (cnt - 1) * i;
            }
            ans = Math.min(ans, len);
        }
        return ans;
    }
}