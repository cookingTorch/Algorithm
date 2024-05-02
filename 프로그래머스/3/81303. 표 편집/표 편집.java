import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Solution {
	private static final char U = 'U';
	private static final char D = 'D';
	private static final char Z = 'Z';
	private static final char C = 'C';
	private static final char O = 'O';
	private static final char X = 'X';
	
	private static int root;
	private static int[] ft;
	private static char[] ans;
	
	private static int find(int prefix) {
		int idx;
		int sum;
		
		idx = 0;
		sum = 0;
	    for (int i = root; i > 0; i >>= 1) {
	        if (sum + ft[idx + i] < prefix) {
	            sum += ft[idx += i];
	        }
	    }
	    return idx + 1;
	}
	
	private static void update(int idx, int diff) {
		int i;
		
		if (diff == 1) {
			ans[idx - 1] = O;
		} else {
			ans[idx - 1] = X;
		}
		for (i = idx; i <= root; i += (i & -i)) {
			ft[i] += diff;
		}
	}
	
	private static int sum(int idx) {
		int i;
		int sum;
		
		sum = 0;
		for (i = idx; i != 0; i -= (i & -i)) {
			sum += ft[i];
		}
		return sum;
	}
	
    public String solution(int n, int k, String[] cmd) {
    	int i;
    	int len;
    	int idx;
    	int size;
    	int prefix;
    	StringTokenizer st;
    	ArrayDeque<Integer> stack;
    	
    	ans = new char[n];
    	for (i = 0; i < n; i++) {
    		ans[i] = O;
    	}
    	for (root = 1; root < n; root <<= 1);
        ft = new int[root + 1];
        for (i = 1; i <= n; i++) {
        	ft[i]++;
        	if (i < root) {
        		ft[i + (i & -i)] += ft[i];
        	}
        }
        for (; i < root; i++) {
        	ft[i + (i & -i)] += ft[i];
        }
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
        		idx = find(prefix);
        		stack.addFirst(idx);
        		update(idx, -1);
        		if (prefix == size--) {
        			prefix--;
        		}
        		break;
        	case Z:
        		idx = stack.removeFirst();
        		update(idx, 1);
        		if (sum(idx) <= prefix) {
        			prefix++;
        		}
        		size++;
        	}
        }
        return new String(ans);
    }
}