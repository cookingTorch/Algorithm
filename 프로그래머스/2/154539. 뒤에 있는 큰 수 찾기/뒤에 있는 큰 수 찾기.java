import java.util.ArrayDeque;

class Solution {
    private static final int NIL = -1;
    private static final int INF = Integer.MAX_VALUE;

    public int[] solution(int[] numbers) {
        int i;
        int num;
        int len;
        ArrayDeque<int[]> stack;

        len = numbers.length;
        stack = new ArrayDeque<>(len + 1);
        stack.addFirst(new int[] {NIL, INF});
        for (i = 0; i < len; i++) {
            num = numbers[i];
            while (stack.peekFirst()[1] < num) {
                numbers[stack.pollFirst()[0]] = num;
            }
            stack.addFirst(new int[] {i, num});
        }
        stack.pollLast();
        while (!stack.isEmpty()) {
            numbers[stack.pollFirst()[0]] = NIL;
        }
        return numbers;
    }
}
