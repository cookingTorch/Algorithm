import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	private static final int RADIX = 10;
	private static final int DIFF = '0';
	private static final int MAX_N = 200_000;
	private static final int SIZE = MAX_N << 2;
	private static final char SPACE = ' ';
	
	private static int n;
	private static int len;
	private static int offset;
	private static int[] arr;
	private static int[] tree;
	private static int[] unique;
	private static String str;
	private static BufferedReader br;
	
	private static final int nextInt() {
		int val;
		char ch;
		
		val = str.charAt(offset) - DIFF;
		for (++offset; offset < len && (ch = str.charAt(offset)) != SPACE; offset++) {
			val = val * RADIX + (ch - DIFF);
		}
		offset++;
		return val;
	}
	
	private static final int lowerBound(int num) {
		int mid;
		int left;
		int right;
		
		left = 0;
		right = n;
		while (left < right) {
			mid = left + right >> 1;
			if (unique[mid] < num) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return right;
	}
	
	private static final void compress() {
		int i;
		
		for (i = 0; i < n; i++) {
			unique[i] = arr[i];
		}
		Arrays.sort(unique, 0, n);
		for (i = 0; i < n; i++) {
			arr[i] = lowerBound(arr[i]);
		}
	}
	
	private static final void insert(int node, int start, int end, int idx, int num) {
		int mid;
		
		if (start == end) {
			if (num > tree[node]) {
				tree[node] = num;
			}
			return;
		}
		mid = start + end >> 1;
		if (idx <= mid) {
			insert(node << 1, start, mid, idx, num);
		} else {
			insert(node << 1 | 1, mid + 1, end, idx, num);
		}
		tree[node] = Math.max(tree[node << 1], tree[node << 1 | 1]);
	}
	
	private static final int query(int node, int start, int end, int idx) {
		int mid;
		
		if (idx < start) {
			return 0;
		}
		if (end <= idx) {
			return tree[node];
		}
		mid = start + end >> 1;
		return Math.max(query(node << 1, start, mid, idx), query(node << 1 | 1, mid + 1, end, idx));
	}
	
	private static final int solution() throws IOException {
		int i;
		int end;
		int max;
		int len0;
		int len1;
		
		n = Integer.parseInt(br.readLine());
		str = br.readLine();
		len = str.length();
		offset = 0;
		for (i = 0; i < n; i++) {
			arr[i] = nextInt();
		}
		compress();
		end = n - 1;
		len0 = 1;
		insert(1, 0, end, arr[0], 1);
		len1 = 1;
		max = 1;
		for (i = 1; i < n; i++) {
			if (arr[i] > arr[i - 1]) {
				if (++len0 > max) {
					max = len0;
				}
				len1++;
			} else {
				len0 = 1;
				len1 = 1;
			}
			insert(1, 0, end, arr[i], len0);
			len1 = Math.max(len1, query(1, 0, end, arr[i] - 1) + 1);
			max = Math.max(max, len1);
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		int z;
		int i;
		StringBuilder sb;
		
		arr = new int[MAX_N];
		unique = new int[MAX_N];
		tree = new int[SIZE];
		br = new BufferedReader(new InputStreamReader(System.in));
		z = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		while (z-- > 0) {
			for (i = 0; i < SIZE; i++) {
				tree[i] = 0;
			}
			sb.append(solution()).append('\n');
		}
		System.out.print(sb);
	}
}
