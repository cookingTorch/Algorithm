import java.util.HashMap;

class Solution {
    private static final int MISS = 5;

    public int solution(int cacheSize, String[] cities) {
        int l;
        int r;
        int len;
        int idx;
        int cnt;
        int time;
        boolean[] out;
        HashMap<String, Integer> map;

        map = new HashMap<>();
        len = cities.length;
        out = new boolean[len + 1];
        out[len] = true;
        l = 0;
        cnt = 0;
        time = 0;
        for (r = 0; r < len; r++) {
            idx = map.getOrDefault(cities[r] = cities[r].toLowerCase(), len);
            map.put(cities[r], r);
            if (out[idx]) {
                if (cnt == cacheSize) {
                    for (; out[l]; l++);
                    out[l++] = true;
                } else {
                    cnt++;
                }
                time += MISS;
            } else {
                out[idx] = true;
                time++;
            }
        }
        return time;
    }
}
