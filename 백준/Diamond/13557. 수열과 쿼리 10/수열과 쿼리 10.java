import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final Node NIL = new Node();
	
	private static final class Node {
		long sum;
		long max;
		long lMax;
		long rMax;
	}
	
	private static int n;
	private static int[] arr;
	private static Node[] tree;
	
	private static final Node merge(Node left, Node right) {
		Node result;
		
		result = new Node();
		result.sum = left.sum + right.sum;
		result.max = Math.max(Math.max(left.max, right.max), left.rMax + right.lMax);
		result.lMax = Math.max(left.lMax, left.sum + right.lMax);
		result.rMax = Math.max(right.rMax, right.sum + left.rMax);
		return result;
	}
	
	private static final Node init(int node, int start, int end) {
		int mid;
		
		if (start == end) {
			tree[node] = new Node();
			tree[node].sum = arr[start];
			tree[node].max = arr[start];
			tree[node].lMax = arr[start];
			tree[node].rMax = arr[start];
			return tree[node];
		}
		mid = start + end >> 1;
		return tree[node] = merge(init(node << 1, start, mid), init(node << 1 | 1, mid + 1, end));
	}
	
	private static final Node query(int node, int start, int end, int left, int right) {
		int mid;
		
		if (left <= start && end <= right) {
			return tree[node];
		}
		mid = start + end >> 1;
		if (right <= mid) {
			return query(node << 1, start, mid, left, right);
		}
		if (mid < left) {
			return query(node << 1 | 1, mid + 1, end, left, right);
		}
		return merge(query(node << 1, start, mid, left, right),
				query(node << 1 | 1, mid + 1, end, left, right));
	}
	
	private static final long getMax(int x1, int y1, int x2, int y2) {
		Node left, right, mid;
		
		if (y1 < x2) {
			return query(1, 1, n, x1, y1).rMax
					+ query(1, 1, n, x2, y2).lMax
					+ (y1 + 1 < x2 ? query(1, 1, n, y1 + 1, x2 - 1).sum : 0);
		}
		if (x1 < x2) {
			left = query(1, 1, n, x1, x2 - 1);
		} else {
			left = NIL;
		}
		if (y1 < y2) {
			right = query(1, 1, n, y1 + 1, y2);
		} else {
			right = NIL;
		}
		mid = query(1, 1, n, x2, y1);
		return Math.max(mid.max,
				Math.max(left.rMax + mid.lMax,
				Math.max(mid.rMax + right.lMax,
				left.rMax + mid.sum + right.lMax)));
	}
	
	public static void main(String[] args) throws IOException {
		int m;
		int i;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		tree = new Node[4 * n];
		init(1, 1, n);
		sb = new StringBuilder();
		m = Integer.parseInt(br.readLine());
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			sb.append(getMax(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append('\n');
		}
		System.out.print(sb);
	}
}
