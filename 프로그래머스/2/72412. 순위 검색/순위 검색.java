import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

class Solution {
    private static final int CNT = 4;
    private static final int MAX = 1 << CNT;
    private static final char WILD = '-';
    private static final char SPACE = ' ';
    private static final String AND = " and ";
    private static final String DELIM = " ";

    private static int count(ArrayList<Integer> list, int score) {
        int l;
        int r;
        int mid;

        l = 0;
        r = list.size();
        while (l < r) {
            mid = l + r >>> 1;
            if (list.get(mid) < score) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return list.size() - r;
    }

    public int[] solution(String[] info, String[] query) {
        int i;
        int j;
        int idx;
        int size;
        int mask;
        int score;
        int[] ans;
        String str;
        String key;
        String[] arr;
        HashMap<String, ArrayList<Integer>> map;
        StringBuilder sb;
        StringTokenizer st;

        arr = new String[CNT];
        map = new HashMap<>();
        size = info.length;
        for (i = 0; i < size; i++) {
            str = info[i];
            st = new StringTokenizer(str, DELIM, false);
            for (j = 0; j < CNT; j++) {
                arr[j] = st.nextToken();
            }
            score = Integer.parseInt(st.nextToken());
            for (mask = 0; mask < MAX; mask++) {
                sb = new StringBuilder();
                sb.append((mask & 1) == 0 ? WILD : arr[0]);
                for (j = 1; j < CNT; j++) {
                    sb.append(AND).append((mask & (1 << j)) == 0 ? WILD : arr[j]);
                }
                key = sb.toString();
                if (!map.containsKey(key)) {
                    map.put(key, new ArrayList<>());
                }
                map.get(key).add(score);
            }
        }
        for (ArrayList<Integer> list : map.values()) {
            Collections.sort(list);
        }
        size = query.length;
        ans = new int[size];
        for (i = 0; i < size; i++) {
            str = query[i];
            idx = str.lastIndexOf(SPACE);
            key = str.substring(0, idx);
            score = Integer.parseInt(str.substring(idx + 1));
            if (map.containsKey(key)) {
                ans[i] = count(map.get(key), score);
            }
        }
        return ans;
    }
}
