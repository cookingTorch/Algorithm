import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Solution {
    private static final int ADD = 0;
    private static final int SUB = 1;
    private static final int MUL = 2;
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
        switch (op) {
            case ADD:
                numStack.addFirst(numStack.pollFirst() + numStack.pollFirst());
                break;
            case SUB:
                numStack.addFirst(-numStack.pollFirst() + numStack.pollFirst());
                break;
            case MUL:
                numStack.addFirst(numStack.pollFirst() * numStack.pollFirst());
        }
    }

    private static long eval(ArrayList<Integer> ops, ArrayList<Long> nums, int[] priority) {
        int i;
        int len;

        len = nums.size();
        numStack.addFirst(nums.get(0));
        for (i = 1; i < len; i++) {
            while (!opStack.isEmpty() && priority[opStack.peekFirst()] >= priority[ops.get(i)]) {
                calc(opStack.pollFirst());
            }
            numStack.addFirst(nums.get(i));
            opStack.addFirst(ops.get(i));
        }
        while (!opStack.isEmpty()) {
            calc(opStack.pollFirst());
        }
        return numStack.pollFirst();
    }

    public long solution(String expression) {
        int op;
        long max;
        ArrayList<Long> nums;
        ArrayList<Integer> ops;
        StringTokenizer st;

        ops = new ArrayList<>();
        nums = new ArrayList<>();
        st = new StringTokenizer(expression, DELIM, true);
        ops.add(0);
        nums.add(Long.parseLong(st.nextToken()));
        while (st.hasMoreTokens()) {
            op = st.nextToken().charAt(0);
            ops.add(op == PLUS ? 0 : op == MINUS ? 1 : 2);
            nums.add((long) Integer.parseInt(st.nextToken()));
        }
        max = 0L;
        opStack = new ArrayDeque<>();
        numStack = new ArrayDeque<>();
        for (int[] priority : PRIORITIES) {
            max = Math.max(max, Math.abs(eval(ops, nums, priority)));
        }
        return max;
    }
}