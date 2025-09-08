import java.util.ArrayDeque;

class Solution {
    private static final int INF = Integer.MAX_VALUE >>> 1;
    private static final int[] NIL = new int[] {-1, INF};

    public int solution(int[] stones, int k) {
        int i;
        int len;
        int min;
        int[] rock;
        ArrayDeque<int[]> stack;

        len = stones.length;
        stack = new ArrayDeque<>(len << 1);
        stack.addFirst(NIL);
        min = INF;
        for (i = 0; i < len; i++) {
            while (stack.peekFirst()[1] <= stones[i]) {
                rock = stack.pollFirst();
                if (i - stack.peekFirst()[0] - 1 >= k) {
                    min = Math.min(min, rock[1]);
                    break;
                }
            }
            while (stack.peekFirst()[1] <= stones[i]) {
                stack.pollFirst();
            }
            stack.addFirst(new int[] {i, stones[i]});
        }
        while ((rock = stack.pollFirst()) != NIL) {
            if (len - stack.peekFirst()[0] - 1 >= k) {
                min = Math.min(min, rock[1]);
                break;
            }
        }
        return min;
    }
}