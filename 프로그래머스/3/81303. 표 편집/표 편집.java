class Solution {
	private static final int RADIX = 10;
    private static final char U = 'U';
    private static final char D = 'D';
    private static final char C = 'C';
    private static final char Z = 'Z';
    private static final char O = 'O';
    private static final char X = 'X';
    
    public String solution(int n, int k, String[] cmd) {
        int i;
        int head;
        int move;
        int[] node;
        int[] prev;
        int[] next;
        int[][] stack;
        char[] ans;
        
        ans = new char[n];
        for (i = 0; i != n; i++) {
            ans[i] = O;
        }
        prev = new int[n + 1];
        next = new int[n + 1];
        for (i = 0; i <= n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        prev[0] = n;
        next[n] = 0;
        move = 0;
        head = 0;
        stack = new int[cmd.length][];
        for (String query : cmd) {
            switch (query.charAt(0)) {
            case U:
                move -= Integer.parseInt(query, 2, query.length(), RADIX);
                break;
            case D:
                move += Integer.parseInt(query, 2, query.length(), RADIX);
                break;
            case C:
                for (; move < 0; move++, k = prev[k]);
                for (; move > 0; move--, k = next[k]);
                ans[k] = X;
                stack[head++] = new int[] {prev[k], k, next[k]};
                prev[next[k]] = prev[k];
                next[prev[k]] = next[k];
                k = next[k] == n ? prev[k] : next[k];
                break;
            case Z:
                for (; move < 0; move++, k = prev[k]);
                for (; move > 0; move--, k = next[k]);
                node = stack[--head];
                prev[node[2]] = node[1];
                next[node[0]] = node[1];
                ans[node[1]] = O;
            }
        }
        return new String(ans, 0, n);
    }
}