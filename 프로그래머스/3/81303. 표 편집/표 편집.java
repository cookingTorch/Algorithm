import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Solution {
    private static final char U = 'U';
    private static final char D = 'D';
    private static final char Z = 'Z';
    private static final char C = 'C';
    private static final char O = 'O';
    private static final char X = 'X';
    
    private static int[] tree;
    private static char[] ans;
    
    private static void init(int node, int start, int end) {
        int mid;
        
        if (start == end) {
        	tree[node] = 1;
            return;
        }
        tree[node] = end - start + 1;
        mid = start + end >> 1;
        init(node << 1, start, mid);
        init(node << 1 | 1, mid + 1, end);
    }
    
    private static int delete(int node, int start, int end, int prefix) {
        int mid;
        
        tree[node]--;
        if (start == end) {
            ans[start] = X;
            return start;
        }
        mid = start + end >> 1;
        if (tree[node << 1] < prefix) {
            return delete(node << 1 | 1, mid + 1, end, prefix - tree[node << 1]);
        } else {
            return delete(node << 1, start, mid, prefix);
        }
    }
    
    private static int undo(int node, int start, int end, int idx) {
        int mid;
        
        tree[node]++;
        if (start == end) {
            ans[start] = O;
            return 1;
        }
        mid = (start + end) >> 1;
        if (idx <= mid) {
            return undo(node << 1, start, mid, idx);
        }
        return tree[node << 1] + undo(node << 1 | 1, mid + 1, end, idx);
    }
    
    public String solution(int n, int k, String[] cmd) {
        int i;
        int len;
        int end;
        int size;
        int prefix;
        StringTokenizer st;
        ArrayDeque<Integer> stack;
        
        ans = new char[n];
        for (i = 0; i < n; i++) {
            ans[i] = O;
        }
        end = n - 1;
        tree = new int[n << 2];
        init(1, 0, end);
        size = n;
        prefix = k + 1;
        stack = new ArrayDeque<>();
        len = cmd.length;
        for (i = 0; i < len; i++) {
            st = new StringTokenizer(cmd[i]);
            switch (st.nextToken().charAt(0)) {
            case U:
                prefix -= Integer.parseInt(st.nextToken());
                break;
            case D:
                prefix += Integer.parseInt(st.nextToken());
                break;
            case C:
                stack.addFirst(delete(1, 0, end, prefix));
                if (prefix == size--) {
                    prefix--;
                }
                break;
            case Z:
                if (undo(1, 0, end, stack.removeFirst()) <= prefix) {
                    prefix++;
                }
                size++;
            }
        }
        return new String(ans);
    }
}