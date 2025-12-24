import java.util.ArrayList;

class Solution {
    private static final int ALPH = 26;
    private static final int DIFF = 'A';

    private static final class Trie {
        private static int idx = 0;

        int num;
        Trie[] next;

        Trie() {
            num = idx++;
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

        root = new Trie();
        for (i = 0; i < ALPH; i++) {
            root.next[i] = new Trie();
        }
        res = new ArrayList<>();
        len = msg.length();
        cur = root;
        for (i = 0; i < len; i++) {
            ch = msg.charAt(i) - DIFF;
            if (cur.next[ch] == null) {
                res.add(cur.num);
                cur.next[ch] = new Trie();
                cur = root;
                i--;
            } else {
                cur = cur.next[ch];
            }
        }
        res.add(cur.num);
        len = res.size();
        ans = new int[len];
        for (i = 0; i < len; i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
}
