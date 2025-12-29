import java.util.ArrayDeque;

class Solution {
    public int solution(int[] order) {
        int i;
        int len;
        int cur;
        ArrayDeque<Integer> stack;

        stack = new ArrayDeque<>();
        cur = 0;
        len = order.length;
        for (i = 0; i < len; i++) {
            if (order[i] > cur) {
                for (++cur; cur < order[i]; cur++) {
                    stack.addFirst(cur);
                }
            } else if (stack.pollFirst() != order[i]) {
                 break;
            }
        }
        return i;
    }
}
