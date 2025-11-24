class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int i;
        int len;
        int head;
        int time;
        int[] start;

        len = truck_weights.length;
        start = new int[len];
        start[0] = time = 1;
        weight -= truck_weights[0];
        head = 0;
        for (i = 1; i < len; i++) {
            if (++time - start[head] == bridge_length) {
                weight += truck_weights[head++];
            }
            while (weight < truck_weights[i]) {
                time = start[head] + bridge_length;
                weight += truck_weights[head++];
            }
            start[i] = time;
            weight -= truck_weights[i];
        }
        return start[len - 1] + bridge_length;
    }
}
