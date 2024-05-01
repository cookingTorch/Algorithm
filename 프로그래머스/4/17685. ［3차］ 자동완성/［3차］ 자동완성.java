class Solution {
    private static final int SIZE = 26;
    private static final int DIFF = 'a';
    
    private static final class Trie {
        int cnt;
        Trie[] next;
        
        Trie() {
            next = new Trie[SIZE];
        }
        
        final void insert(String str) {
            int i;
            int len;
            int idx;
            Trie curr;
            
            len = str.length();
            for (curr = this, i = 0; i < len; curr = curr.next[idx], i++) {
                curr.cnt++;
                idx = str.charAt(i) - DIFF;
                if (curr.next[idx] == null) {
                    curr.next[idx] = new Trie();
                }
            }
            curr.cnt++;
        }
        
        final int search(String str) {
            int i;
            int len;
            Trie curr;
            
            len = str.length();
            for (curr = this, i = 0; i < len && curr.cnt != 1; curr = curr.next[str.charAt(i++) - DIFF]);
            return i;
        }
    }
    
    public int solution(String[] words) {
        int cnt;
        Trie root;
        
        root = new Trie();
        for (String word : words) {
            root.insert(word);
        }
        cnt = 0;
        for (String word : words) {
            cnt += root.search(word);
        }
        return cnt;
    }
} 