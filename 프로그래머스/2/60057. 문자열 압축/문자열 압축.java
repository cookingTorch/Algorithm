class Solution {
    public int solution(String s) {
        int i;
        int j;
        int k;
        int num;
        int len;
        int res;
        int min;
        char[] str;
        
        str = s.toCharArray();
        len = str.length;
        min = len;
        for (i = 1; i <= len >> 1; i++) {
            res = len;
            num = 1;
            for (j = i; j <= len - i; j += i) {
                for (k = 0; k < i; k++) {
                    if (str[j + k] != str[j - i + k]) {
                        break;
                    }
                }
                if (k < i) {
                    if (num > 1) {
                        res = res - ((num - 1) * i) + (num < 10 ? 1 : num < 100 ? 2 : num < 1000 ? 3 : 4);
                        num = 1;
                    }
                } else {
                    num++;
                }
            }
            if (num > 1) {
                res = res - ((num - 1) * i) + (num < 10 ? 1 : num < 100 ? 2 : num < 1000 ? 3 : 4);
            }
            min = Math.min(min, res);
        }
        return min;
    }
}