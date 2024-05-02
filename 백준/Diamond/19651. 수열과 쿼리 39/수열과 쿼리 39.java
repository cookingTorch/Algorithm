import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final char QUERY_1 = '1';
	private static final char TWO = '2';
	private static final Node NIL = new Node();
	
	private static final class Node {
		int l;
		int r;
		int max;
		boolean filled;
		
		Node() {
		}
		
		Node(boolean flag) {
			if (flag) {
				fill(1);
			}
		}
		
		final void fill(int num) {
			l = num;
			r = num;
			max = num;
			if (num == 0) {
				filled = false;
			} else {
				filled = true;
			}
		}
	}
	
	private static int len;
	private static long[] arr;
	private static Node[] tree;
	private static BufferedReader br;
	
	private static final StringBuilder isTwo() throws IOException {
		int m;
		int i;
		StringBuilder sb;
		
		br.readLine();
		sb = new StringBuilder();
		m = Integer.parseInt(br.readLine());
		for (i = 0; i < m; i++) {
			if (new StringTokenizer(br.readLine()).nextToken().charAt(0) == TWO) {
				sb.append(TWO).append('\n');
			}
		}
		return sb;
	}
	
	private static final Node merge(Node left, Node right) {
		Node result;
		
		result = new Node();
		result.l = left.filled ? left.max + right.l : left.l;
		result.r = right.filled ? right.max + left.r : right.r;
		result.max = Math.max(Math.max(left.max, right.max), left.r + right.l);
		result.filled = left.filled & right.filled;
		return result;
	}
	
	private static final void merge(Node left, Node right, Node dest) {
		dest.l = left.filled ? left.max + right.l : left.l;
		dest.r = right.filled ? right.max + left.r : right.r;
		dest.max = Math.max(Math.max(left.max, right.max), left.r + right.l);
		dest.filled = left.filled & right.filled;
	}
	
	private static final void init(int node, int start, int end) {
		int mid;
		
		if (start == end) {
			tree[node] = new Node(arr[start] == 0);
			return;
		}
		mid = start + end >> 1;
		init(node << 1, start, mid);
		init(node << 1 | 1, mid + 1, end);
		tree[node] = merge(tree[node << 1], tree[node << 1 | 1]);
	}
	
	private static final void update(int node, int start, int end, int idx, int num) {
		int mid;
		
		if (start == end) {
			tree[node].fill(num);
			return;
		}
		mid = start + end >> 1;
		if (idx <= mid) {
			update(node << 1, start, mid, idx, num);
		} else {
			update(node << 1 | 1, mid + 1, end, idx, num);
		}
		merge(tree[node << 1], tree[node << 1 | 1], tree[node]);
	}
	
	private static final void update(int idx, long num) {
		if (idx <= 0 || idx > len) {
			return;
		}
		arr[idx] += num;
		if (arr[idx] == 0) {
			update(1, 1, len, idx, 1);
		} else {
			update(1, 1, len, idx, 0);
		}
	}
	
	private static final Node query(int node, int start, int end, int left, int right) {
		int mid;
		
		if (end < left || right < start) {
			return NIL;
		}
		if (left <= start && end <= right) {
			return tree[node];
		}
		mid = start + end >> 1;
		return merge(query(node << 1, start, mid, left, right),
				query(node << 1 | 1, mid + 1, end, left, right));
	}
	
	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int i;
		int j;
		int k;
		int size;
		int query;
		long x;
		long y;
		StringBuilder sb;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		if (n == 2) {
			System.out.print(isTwo());
			return;
		}
		arr = new long[n + 1];
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for (i = 1; i < n; i++) {
			arr[i] = arr[i + 1] - arr[i];
		}
		len = n - 2;
		for (i = 1; i <= len; i++) {
			arr[i] = arr[i + 1] - arr[i];
		}
		for (size = 1; size < n; size <<= 1);
		tree = new Node[size << 1];
		init(1, 1, len);
		sb = new StringBuilder();
		m = Integer.parseInt(br.readLine());
		for (k = 0; k < m; k++) {
			st = new StringTokenizer(br.readLine());
			query = st.nextToken().charAt(0);
			i = Integer.parseInt(st.nextToken());
			j = Integer.parseInt(st.nextToken());
			if (query == QUERY_1) {
				x = Long.parseLong(st.nextToken());
				y = Long.parseLong(st.nextToken());
				update(i - 2, x);
				update(i - 1, -x + y);
				update(j - 1, -x - (j - i + 1) * y);
				update(j, x + (j - i) * y);
			} else if (j - i == 1) {
				sb.append(TWO).append('\n');
			} else {
				sb.append(query(1, 1, len, i, j - 2).max + 2).append('\n');
			}
		}
		System.out.print(sb);
	}
}
