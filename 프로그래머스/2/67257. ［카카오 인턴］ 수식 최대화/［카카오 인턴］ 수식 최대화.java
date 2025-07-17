import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Solution {
    private static final int ADD = 0;
    private static final int SUB = 1;
    private static final int PLUS = '+';
    private static final int MINUS = '-';
    private static final int[][] PRIORITIES = {
            {0, 1, 2}, {0, 2, 1},
            {1, 0, 2}, {1, 2, 0},
            {2, 0, 1}, {2, 1, 0}
    };
    private static final String DELIM = "+-*";

    private static ArrayDeque<Long> numStack;
    private static ArrayDeque<Integer> opStack;

    private static void calc(int op) {
        if (op == ADD) {
            numStack.addFirst(numStack.pollFirst() + numStack.pollFirst());
        } else if (op == SUB) {
            numStack.addFirst(-numStack.pollFirst() + numStack.pollFirst());
        } else {
            numStack.addFirst(numStack.pollFirst() * numStack.pollFirst());
        }
    }

    private static long eval(int[] ops, long[] nums, int len, int[] priority) {
        int i;

        numStack.addFirst(nums[0]);
        for (i = 1; i < len; i++) {
            while (!opStack.isEmpty() && priority[opStack.peekFirst()] >= priority[ops[i]]) {
                calc(opStack.pollFirst());
            }
            numStack.addFirst(nums[i]);
            opStack.addFirst(ops[i]);
        }
        while (!opStack.isEmpty()) {
            calc(opStack.pollFirst());
        }
        return numStack.pollFirst();
    }

    public long solution(String expression) {
        int i;
        int op;
        int[] ops;
        long max;
        long[] nums;
        StringTokenizer st;

        ops = new int[expression.length() + 1 >>> 1];
        nums = new long[expression.length() + 1 >>> 1];
        st = new StringTokenizer(expression, DELIM, true);
        ops[0] = 0;
        nums[0] = Long.parseLong(st.nextToken());
        for (i = 1; st.hasMoreTokens(); i++) {
            op = st.nextToken().charAt(0);
            ops[i] = op == PLUS ? 0 : op == MINUS ? 1 : 2;
            nums[i] = (long) Integer.parseInt(st.nextToken());
        }
        max = 0L;
        opStack = new ArrayDeque<>();
        numStack = new ArrayDeque<>();
        for (int[] priority : PRIORITIES) {
            max = Math.max(max, Math.abs(eval(ops, nums, i, priority)));
        }
        return max;
    }
}
