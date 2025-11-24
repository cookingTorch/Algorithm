import java.util.ArrayDeque;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int i;
        int len;
        int time;
        ArrayDeque<int[]> dq;

        len = truck_weights.length;
        dq = new ArrayDeque<>(len);
        dq.add(new int[] {time = 1, truck_weights[0]});
        weight -= truck_weights[0];
        for (i = 1; i < len; i++) {
            if (++time - dq.peekLast()[0] == bridge_length) {
                weight += dq.pollLast()[1];
            }
            while (weight < truck_weights[i]) {
                time = dq.peekLast()[0] + bridge_length;
                weight += dq.pollLast()[1];
            }
            dq.addFirst(new int[] {time, truck_weights[i]});
            weight -= truck_weights[i];
        }
        return dq.peekFirst()[0] + bridge_length;
    }
}
