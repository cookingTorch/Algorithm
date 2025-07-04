import java.util.ArrayList;
import java.util.HashSet;

class Solution {
    private static int r;
    private static String[][] table;

    private static boolean isUnique(int mask) {
        int i;
        int j;
        int size;
        ArrayList<String> list;
        HashSet<ArrayList<String>> set;

        set = new HashSet<>();
        size = Integer.bitCount(mask);
        for (i = 0; i < r; i++) {
            list = new ArrayList<>(size);
            for (j = mask; j != 0; j &= j - 1) {
                list.add(table[i][Integer.numberOfTrailingZeros(j)]);
            }
            if (!set.add(list)) {
                return false;
            }
        }
        return true;
    }

    public int solution(String[][] relation) {
        int c;
        int i;
        int j;
        int cnt;
        int full;
        int rest;
        boolean[] dp;

        r = relation.length;
        c = relation[0].length;
        table = relation;
        cnt = 0;
        full = (1 << c) - 1;
        dp = new boolean[full + 1];
        for (i = 1; i <= full; i++) {
            if (dp[i]) {
                continue;
            }
            if (isUnique(i)) {
                cnt++;
                rest = full ^ i;
                for (j = rest; j != 0; j = (j - 1) & rest) {
                    dp[i | j] = true;
                }
            }
        }
        return cnt;
    }
}
