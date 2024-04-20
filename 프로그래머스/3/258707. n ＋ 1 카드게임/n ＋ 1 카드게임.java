import java.util.ArrayDeque;

class Solution {
    private static final class Pair implements Comparable<Pair> {
        int left, right;
        
        Pair(int left) {
            this.left = left;
        }
        
        @Override
        public int compareTo(Pair o) {
            return Integer.compare(this.right, o.right);
        }
    }
    
    private static ArrayDeque<Pair> q, cost1, cost2;
    
    public int solution(int coin, int[] cards) {
        int i, n, num, turn;
        Pair pair;
        Pair[] pairs;
        
        n = cards.length;
        pairs = new Pair[n >> 1];
        turn = n / 3 + 1;
        for (i = 0; i < n / 3; i++) {
            num = Math.min(cards[i] - 1, n - cards[i]);
            if (pairs[num] == null) {
                pairs[num] = new Pair(0);
            } else {
                turn += 2;
            }
        }
        q = new ArrayDeque<>(n >> 1);
        for (; i < n; i++) {
            num = Math.min(cards[i] - 1, n - cards[i]);
            if (pairs[num] == null) {
                pairs[num] = new Pair(i);
            } else {
                pairs[num].right = i;
                q.addLast(pairs[num]);
            }
        }
        pair = new Pair(0);
        pair.right = n + 1;
        q.addLast(pair);
        cost1 = new ArrayDeque<>();
        cost2 = new ArrayDeque<>();
        while (!q.isEmpty()) {
            pair = q.pollFirst();
            while (pair.right > turn) {
                if (coin == 0) {
                    return (turn + 1) - n / 3 >> 1;
                }
                if (cost1.isEmpty()) {
                    if (coin < 2 || cost2.isEmpty()) {
                        return (turn + 1) - n / 3 >> 1;
                    }
                    cost2.pollFirst();
                    coin -= 2;
                    turn += 2;
                    continue;
                }
                cost1.pollFirst();
                coin--;
                turn += 2;
            }
            if (pair.left == 0) {
                cost1.addLast(pair);
                continue;
            }
            cost2.addLast(pair);
        }
        return n / 3 + 1;
    }
}