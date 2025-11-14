import java.util.ArrayList;
import java.util.HashSet;

class Solution {
    private static int n;
    private static String[][] table;

    private static boolean isUnique(int mask) {
        int i;
        int j;
        int size;
        ArrayList<String> list;
        HashSet<ArrayList<String>> set;

        set = new HashSet<>();
        size = Integer.bitCount(mask);
        for (i = 0; i < n; i++) {
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
        int m;
        int r;
        int c;
        int ans;
        int thr;
        int len;
        int sub;
        int mask;
        boolean[] visited;

        table = relation;
        n = relation.length;
        m = relation[0].length;
        thr = 1 << m;
        ans = 0;
        visited = new boolean[thr];
        for (len = 1; len <= m; len++) {
            for (mask = (1 << len) - 1; mask < thr;) {
                for (sub = mask; sub != 0; sub = (sub - 1) & mask) {
                    if (visited[sub]) {
                        break;
                    }
                }
                if (sub == 0 && isUnique(mask)) {
                    ans++;
                    visited[mask] = true;
                }
                c = mask & -mask;
                r = mask + c;
                mask = ((r ^ mask) >>> 2) / c | r;
            }
        }
        return ans;
    }
}
