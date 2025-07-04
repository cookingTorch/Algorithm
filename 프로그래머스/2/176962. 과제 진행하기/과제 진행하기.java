import java.util.ArrayDeque;
import java.util.Arrays;

class Solution {
    private static final int INF = Integer.MAX_VALUE >>> 1;
    private static final int DIFF = '0';
    private static final Plan NIL = new Plan();

    private static final class Plan implements Comparable<Plan> {
        int start;
        int playtime;
        String name;

        Plan() {
            start = INF;
            playtime = INF;
        }

        Plan(String[] plan) {
            name = plan[0];
            start = convert(plan[1]);
            playtime = Integer.parseInt(plan[2]);
        }

        private static int convert(String start) {
            return ((start.charAt(0) - DIFF) * 10 + start.charAt(1) - DIFF) * 60 + (start.charAt(3) - DIFF) * 10 + start.charAt(4) - DIFF;
        }

        @Override
        public int compareTo(Plan o) {
            return start - o.start;
        }
    }

    public String[] solution(String[][] plans) {
        int i;
        int len;
        int idx;
        int time;
        Plan cur;
        Plan[] arr;
        String[] ans;
        ArrayDeque<Plan> stack;

        len = plans.length;
        arr = new Plan[len + 1];
        for (i = 0; i < len; i++) {
            arr[i] = new Plan(plans[i]);
        }
        arr[len] = NIL;
        Arrays.sort(arr, 0, len);
        stack = new ArrayDeque<>();
        ans = new String[len];
        idx = 0;
        cur = arr[idx++];
        time = cur.start;
        for (i = 0; i < len;) {
            if (time + cur.playtime > arr[idx].start) {
                cur.playtime -= arr[idx].start - time;
                stack.addFirst(cur);
                cur = arr[idx++];
                time = cur.start;
            } else if (stack.isEmpty() || time + cur.playtime == arr[idx].start) {
                ans[i++] = cur.name;
                cur = arr[idx++];
                time = cur.start;
            } else {
                ans[i++] = cur.name;
                time += cur.playtime;
                cur = stack.removeFirst();
            }
        }
        return ans;
    }
}
