class Solution {
    private static final int SIZE = 10;
    private static final int DIFF = '0';

    private static final class Trie {
        boolean end;
        Trie[] next;

        Trie() {
            next = new Trie[SIZE];
        }
    }

    public boolean solution(String[] phone_book) {
        int i;
		int j;
		int len;
		int idx;
        int size;
		Trie cur;
        Trie root;
		String num;

        size = phone_book.length;
        root = new Trie();
        for (i = 0; i < size; i++) {
			num = phone_book[i];
	        len = num.length() - 1;
	        cur = root;
	        for (j = 0; j < len; j++) {
		        idx = num.charAt(j) - DIFF;
		        if (cur.next[idx] == null) {
			        cur.next[idx] = new Trie();
		        }
		        cur = cur.next[idx];
		        if (cur.end) {
			        return false;
		        }
	        }
	        idx = num.charAt(len) - DIFF;
	        if (cur.next[idx] == null) {
		        cur.next[idx] = new Trie();
		        cur.next[idx].end = true;
	        } else {
		        return false;
	        }
        }
        return true;
    }
}
