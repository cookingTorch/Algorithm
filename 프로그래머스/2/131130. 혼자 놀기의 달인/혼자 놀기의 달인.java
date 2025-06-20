class Solution {
    public int solution(int[] cards) {
        int i;
        int j;
        int len;
        int cnt;
        int max1;
        int max2;
        int next;

        len = cards.length;
        max1 = 0;
        max2 = 0;
        for (i = 0; i < len; i++) {
            if (cards[i] == 0) {
                continue;
            }
            cnt = 1;
            next = cards[i] - 1;
            cards[i] = 0;
            for (j = next; j != i; j = next) {
                cnt++;
                next = cards[j] - 1;
                cards[j] = 0;
            }
            if (cnt > max1) {
                max2 = max1;
                max1 = cnt;
            } else if (cnt > max2) {
                max2 = cnt;
            }
        }
        return max1 * max2;
    }
}
