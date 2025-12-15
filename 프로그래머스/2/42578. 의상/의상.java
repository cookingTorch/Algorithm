import java.util.HashMap;

class Solution {
    public int solution(String[][] clothes) {
        int i;
        int len;
        int ans;
        HashMap<String, Integer> map;

        map = new HashMap<>();
        len = clothes.length;
        for (i = 0; i < len; i++) {
            map.merge(clothes[i][1], 2, (v, nv) -> v + 1);
        }
        ans = 1;
        for (int v : map.values()) {
            ans *= v;
        }
        return ans - 1;
    }
}
