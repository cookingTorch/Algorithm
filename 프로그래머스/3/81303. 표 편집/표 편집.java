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
		mid = (start + end) >> 1;
		init(node << 1, start, mid);
		init(node << 1 | 1, mid + 1, end);
		tree[node] = tree[node << 1] + tree[node << 1 | 1];
	}
	
	private static int delete(int node, int start, int end, int idx) {
		int mid;
		
		tree[node]--;
		if (start == end) {
			ans[start] = X;
			return start;
		}
		mid = (start + end) >> 1;
		if (tree[node << 1] < idx) {
			return delete(node << 1 | 1, mid + 1, end, idx - tree[node << 1]);
		} else {
			return delete(node << 1, start, mid, idx);
		}
	}
	
	private static int add(int node, int start, int end, int idx) {
		int mid;
		
		tree[node]++;
		if (start == end) {
			ans[start] = O;
			return 1;
		}
		mid = (start + end) >> 1;
		if (idx > mid) {
			return tree[node << 1] + add(node << 1 | 1, mid + 1, end, idx);
		}
		return add(node << 1, start, mid, idx);
	}
	
    public String solution(int n, int k, String[] cmd) {
    	StringTokenizer st;
    	
    	int len, size, end, idx, i;
    	ArrayDeque<Integer> stack;
    	
    	ans = new char[n];
    	for (i = 0; i < n; i++) {
    		ans[i] = O;
    	}
        end = n - 1;
        tree = new int[4 * n];
        init(1, 0, end);
        size = n;
        idx = k + 1;
        stack = new ArrayDeque<>();
        len = cmd.length;
        for (i = 0; i < len; i++) {
        	st = new StringTokenizer(cmd[i]);
        	switch (st.nextToken().charAt(0)) {
        	case U:
        		idx -= Integer.parseInt(st.nextToken());
        		break;
        	case D:
        		idx += Integer.parseInt(st.nextToken());
        		break;
        	case C:
        		stack.addFirst(delete(1, 0, end, idx));
        		if (idx == size--) {
        			idx--;
        		}
        		break;
        	case Z:
        		if (add(1, 0, end, stack.removeFirst()) <= idx) {
        			idx++;
        		}
        		size++;
        	}
        }
        return new String(ans);
    }
}