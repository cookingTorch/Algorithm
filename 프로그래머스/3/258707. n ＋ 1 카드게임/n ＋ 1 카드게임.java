class Solution {
    public int solution(int coin, int[] cards) {
        int i, len, size, num, pair1, pair2, end;
        int[] cnt;
        
        len = cards.length;
        num = len + 1;
        cnt = new int[num];
        size = len / 3;
        end = size + 2;
        for (i = 0; i < size; i++) {
            if (cnt[cards[i]] == 0) {
                cnt[cards[i]]--;
                cnt[num - cards[i]]--;
            } else {
                end += 2;
            }
        }
        pair1 = 0;
        pair2 = 0;
        for (;; i++) {
            if (i >= end) {
                if (coin > 0 && pair1 != 0) {
                    pair1--;
                    coin--;
                    end += 2;
                } else if (coin > 1 && pair2 != 0) {
                    pair2--;
                    coin -= 2;
                    end += 2;
                } else {
                    return end - size >> 1;
                }
                if (end > len) {
                    return (num - size >> 1) + 1;
                }
            }
            if (cnt[cards[i]] == 0) {
                cnt[cards[i]]++;
                cnt[num - cards[i]]++;
            } else if (cnt[cards[i]] == 1) {
                pair2++;
            } else {
                pair1++;
            }
        }
    }
}