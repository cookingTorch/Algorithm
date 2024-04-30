import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final class Node {
		int sum;
		int max;
		int lMax;
		int rMax;
	}
	
	private static int u;
	private static int v;
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
	
	private static final void merge(Node left, Node right, Node dest) {
		dest.sum = left.sum + right.sum;
		dest.max = Math.max(Math.max(left.max, right.max), left.rMax + right.lMax);
		dest.lMax = Math.max(left.lMax, left.sum + right.lMax);
		dest.rMax = Math.max(right.rMax, right.sum + left.rMax);
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
	
	private static void update(int node, int start, int end, int idx, int num) {
		int mid;
		
		if (start == end) {
			tree[node].sum = num;
			tree[node].max = num;
			tree[node].lMax = num;
			tree[node].rMax = num;
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
	
	public static void main(String[] args) throws IOException {
		int n;
		int q;
		int i;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		u = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());
		arr = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			arr[i] = u * Integer.parseInt(st.nextToken()) + v;
		}
		tree = new Node[4 * n];
		init(1, 1, n);
		sb = new StringBuilder();
		for (i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			if (Integer.parseInt(st.nextToken()) == 0) {
				sb.append(query(1, 1, n, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())).max - v).append('\n');
			} else {
				update(1, 1, n, Integer.parseInt(st.nextToken()), u * Integer.parseInt(st.nextToken()) + v);
			}
		}
		System.out.print(sb);
	}
}
