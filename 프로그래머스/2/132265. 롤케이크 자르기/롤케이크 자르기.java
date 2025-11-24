class Solution {
    private static final int MAX = 10_001;

    public int solution(int[] topping) {
        int i;
        int len;
        int ans;
        int preCnt;
        int sufCnt;
        int[] pre;
        int[] suf;

        len = topping.length;
        sufCnt = 0;
        suf = new int[MAX];
        for (i = len - 1; i >= 0; i--) {
            if (suf[topping[i]]++ == 0) {
                sufCnt++;
            }
        }
        ans = 0;
        preCnt = 0;
        pre = new int[MAX];
        for (i = 0; i < len; i++) {
            if (pre[topping[i]]++ == 0) {
                preCnt++;
            }
            if (--suf[topping[i]] == 0) {
                sufCnt--;
            }
            if (preCnt == sufCnt) {
                ans++;
            }
        }
        return ans;
    }
}
