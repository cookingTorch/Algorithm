import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int MIN = Integer.MIN_VALUE >> 2;
	
	private static final class Node {
		int min;
		int max;
		int minMax;
		int maxMin;
		int minMaxMin;
		
		Node() {
		}
		
		Node(int num) {
			min = num;
			max = num;
			minMax = MIN;
			maxMin = MIN;
			minMaxMin = MIN;
		}
	}
	
	private static int[] arr;
	private static Node[] tree;
	
	private static final Node merge(Node left, Node right) {
		Node result;
		
		result = new Node();
		result.min = Math.min(left.min, right.min);
		result.max = Math.max(left.max, right.max);
		result.minMax = Math.max(Math.max(left.minMax, right.minMax),
				right.max - left.min);
		result.maxMin = Math.max(Math.max(left.maxMin, right.maxMin),
				left.max - right.min);
		result.minMaxMin = Math.max(Math.max(left.minMaxMin, right.minMaxMin),
				Math.max(left.minMax - right.min, right.maxMin - left.min));
		return result;
	}
	
	private static final void merge(Node left, Node right, Node dest) {
		dest.min = Math.min(left.min, right.min);
		dest.max = Math.max(left.max, right.max);
		dest.minMax = Math.max(Math.max(left.minMax, right.minMax),
				right.max - left.min);
		dest.maxMin = Math.max(Math.max(left.maxMin, right.maxMin),
				left.max - right.min);
		dest.minMaxMin = Math.max(Math.max(left.minMaxMin, right.minMaxMin),
				Math.max(left.minMax - right.min, right.maxMin - left.min));
	}
	
	private static final void init(int node, int start, int end) {
		int mid;
		
		if (start == end) {
			tree[node] = new Node(arr[start]);
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
			tree[node].min = num;
			tree[node].max = num;
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
		arr = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		tree = new Node[4 * n];
		init(1, 1, n);
		sb = new StringBuilder();
		for (i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			if (Integer.parseInt(st.nextToken()) == 1) {
				update(1, 1, n, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			} else {
				sb.append(query(1, 1, n, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())).minMaxMin).append('\n');
			}
		}
		System.out.print(sb);
	}
}
