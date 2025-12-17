class Solution {
    private static final int SIZE = 10;
    private static final int DIFF = '0';

    private static final class Trie {
        boolean end;
        Trie[] next;

        Trie() {
            next = new Trie[SIZE];
        }

        boolean insert(String word) {
            int i;
            int idx;
            int len;
            Trie cur;

			cur = this;
            len = word.length() - 1;
            for (i = 0; i < len; i++) {
                idx = word.charAt(i) - DIFF;
                if (cur.next[idx] == null) {
                    cur.next[idx] = new Trie();
                }
	            cur = cur.next[idx];
	            if (cur.end) {
		            return true;
	            }
            }
            idx = word.charAt(len) - DIFF;
            if (cur.next[idx] == null) {
                cur.next[idx] = new Trie();
                cur.next[idx].end = true;
            } else {
                return true;
            }
            return false;
        }
    }

    public boolean solution(String[] phone_book) {
        int i;
        int len;
        Trie trie;

        len = phone_book.length;
        trie = new Trie();
        for (i = 0; i < len; i++) {
            if (trie.insert(phone_book[i])) {
                return false;
            }
        }
        return true;
    }
}
