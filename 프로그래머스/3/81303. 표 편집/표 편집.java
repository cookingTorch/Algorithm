import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Solution {
	private static final char U = 'U';
	private static final char D = 'D';
	private static final char Z = 'Z';
	private static final char C = 'C';
	private static final char[] XO = new char[] {'X', 'O'};
	
	private static int block;
	private static int[] arr;
	private static int[] bucket;
	private static char[] ans;
	
	private static int delete(int prefix) {
		int i;
		int idx;
		int sum;
		
		for (i = -1, sum = 0; sum < prefix; sum += bucket[++i]);
		sum -= bucket[i]--;
		for (idx = block * i - 1; sum < prefix; sum += arr[++idx]);
		arr[idx]--;
		return idx;
	}
	
	private static int undo(int idx) {
		int i;
		int num;
		int prefix;
		
		num = idx / block;
		arr[idx]++;
		bucket[num]++;
		for (i = 0, prefix = 0; i < num; prefix += bucket[i++]);
		for (i = block * num - 1; i < idx; prefix += arr[++i]);
		return prefix;
	}
	
	public String solution(int n, int k, String[] cmd) {
		int i;
		int idx;
		int size;
		StringTokenizer st;
		ArrayDeque<Integer> stack;
		
		arr = new int[n];
		for (i = 0; i < n; i++) {
			arr[i] = 1;
		}
		block = (int) Math.sqrt(n);
		bucket = new int[(n - 1) / block + 1];
		for (i = 0; i < n / block; i++) {
			bucket[i] = block;
		}
		if (i < bucket.length) {
			bucket[i] = n % block;
		}
		idx = k + 1;
		size = n;
		stack = new ArrayDeque<>();
		for (String str : cmd) {
			st = new StringTokenizer(str);
			switch (st.nextToken().charAt(0)) {
			case U:
				idx -= Integer.parseInt(st.nextToken());
				break;
			case D:
				idx += Integer.parseInt(st.nextToken());
				break;
			case C:
				stack.addFirst(delete(idx));
				if (idx == size--) {
					idx--;
				}
				break;
			case Z:
				if (undo(stack.removeFirst()) <= idx) {
					idx++;
				}
				size++;
			}
		}
		ans = new char[n];
		for (i = 0; i < n; i++) {
			ans[i] = XO[arr[i]];
		}
		return new String(ans);
	}
}