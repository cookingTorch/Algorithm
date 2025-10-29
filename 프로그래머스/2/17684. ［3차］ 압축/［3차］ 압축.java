import java.util.ArrayList;

class Solution {
    private static final int ALPH = 26;
    private static final int DIFF = 'A';

    private static final class Trie {
        private static int idx = 0;

        int res;
        Trie[] next;

        Trie() {
            res = idx++;
            next = new Trie[ALPH];
        }
    }

    public int[] solution(String msg) {
        int i;
        int ch;
        int len;
        int[] ans;
        Trie cur;
        Trie root;
        ArrayList<Integer> res;

        res = new ArrayList<>();
        root = new Trie();
        for (i = 0; i < ALPH; i++) {
            root.next[i] = new Trie();
        }
        cur = root;
        len = msg.length();
        for (i = 0; i < len; i++) {
            ch = msg.charAt(i) - DIFF;
            if (cur.next[ch] == null) {
                res.add(cur.res);
                cur.next[ch] = new Trie();
                cur = root;
                i--;
            } else {
                cur = cur.next[ch];
            }
        }
        res.add(cur.res);
        len = res.size();
        ans = new int[len];
        for (i = 0; i < len; i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
}