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

    private static long eval(int[] ops, long[] nums, int[] priority) {
        int i;
        int len;

        len = nums.length;
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
        int len;
        int[] ops;
        long max;
        long[] nums;
        ArrayList<Long> numList;
        ArrayList<Integer> opList;
        StringTokenizer st;

        opList = new ArrayList<>();
        numList = new ArrayList<>();
        st = new StringTokenizer(expression, DELIM, true);
        opList.add(0);
        numList.add(Long.parseLong(st.nextToken()));
        while (st.hasMoreTokens()) {
            op = st.nextToken().charAt(0);
            opList.add(op == PLUS ? 0 : op == MINUS ? 1 : 2);
            numList.add((long) Integer.parseInt(st.nextToken()));
        }
        len = numList.size();
        ops = new int[len];
        nums = new long[len];
        for (i = 0; i < len; i++) {
            ops[i] = opList.get(i);
            nums[i] = numList.get(i);
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
