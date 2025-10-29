import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    private static final int ALPH = 26;
    private static final int DIFF = 'A' - 1;
    private static final String EMPTY = "";

    public int[] solution(String msg) {
        int i;
        int j;
        int len;
        int size;
        String str;
        String tmp;
        String[] chars;
        HashMap<String, Integer> map;
        ArrayList<Integer> ans;

        len = msg.length();
        chars = new String[len];
        for (i = 0; i < len; i++) {
            chars[i] = new String(new char[] {msg.charAt(i)});
        }
        map = new HashMap<>();
        for (i = 1; i <= ALPH; i++) {
            map.put(new String(new char[] {(char) (i + DIFF)}), i);
        }
        size = ALPH;
        ans = new ArrayList<>();
        for (i = 0; i < len;) {
            str = EMPTY;
            for (j = i; j < len; j++) {
                tmp = str + chars[j];
                if (!map.containsKey(tmp)) {
                    map.put(tmp, ++size);
                    break;
                }
                str = tmp;
            }
            ans.add(map.get(str));
            i = j;
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}
