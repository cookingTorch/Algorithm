import java.util.HashMap;

class Solution {
    private static final String CENTER = "-";

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int i;
        int j;
        int len;
        int center;
        int income;
        int[] ans;
        int[] parents;
        HashMap<String, Integer> map;

        center = enroll.length;
        map = new HashMap<>();
        map.put(CENTER, center);
        parents = new int[center];
        for (i = 0; i < center; i++) {
            map.put(enroll[i], i);
            parents[i] = map.get(referral[i]);
        }
        ans = new int[center];
        len = amount.length;
        for (i = 0; i < len; i++) {
            for (j = map.get(seller[i]), income = amount[i] * 100; j != center; j = parents[j], income /= 10) {
                ans[j] += income - income / 10;
            }
        }
        return ans;
    }
}
