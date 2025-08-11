import java.util.HashMap;

class Solution {
    public long solution(int[] weights) {
        int i;
        int len;
        long num;
        long cnt;
        HashMap<Integer, Integer> map;

        map = new HashMap<>();
        cnt = 0L;
        len = weights.length;
        for (i = 0; i < len; i++) {
            map.put(weights[i], map.getOrDefault(weights[i], 0) + 1);
        }
        for (int weight : map.keySet()) {
            num = map.get(weight);
            cnt += (num * (num - 1L) >> 1)
                    + num * (
                    ((weight & 1) == 0 ? map.getOrDefault((weight >> 1) * 3, 0) : 0)
                    + (weight % 3 == 0 ? map.getOrDefault(weight / 3 << 2, 0) : 0)
                    + map.getOrDefault(weight << 1, 0));
        }
        return cnt;
    }
}
