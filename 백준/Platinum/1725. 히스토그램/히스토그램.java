import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final long INF = Long.MAX_VALUE;
	
	private static int n;
	private static int[] tree;
	private static long[] arr;
	
	private static final long init(int node, int start, int end) {
		int mid;
		
		if (start == end) {
			tree[node] = start;
			return arr[start];
		}
		mid = start + end >> 1;
		if (init(node << 1, start, mid) < init(node << 1 | 1, mid + 1, end)) {
			tree[node] = tree[node << 1];
		} else {
			tree[node] = tree[node << 1 | 1];
		}
		return arr[tree[node]];
	}
	
	private static final int query(int node, int start, int end, int l, int r) {
		int mid;
		int left;
		int right;
		
		if (end < l || r < start) {
			return n;
		}
		if (l <= start && end <= r) {
			return tree[node];
		}
		mid = start + end >> 1;
		left = query(node << 1, start, mid, l, r);
		right = query(node << 1 | 1, mid + 1, end, l, r);
		return arr[left] < arr[right] ? left : right;
	}
	
	private static final long getMax(int start, int end) {
		int mid;
		
		if (start > end) {
			return 0L;
		}
		mid = query(1, 0, n - 1, start, end);
		return Math.max((end - start + 1) * arr[mid],
				Math.max(getMax(start, mid - 1),
						getMax(mid + 1, end)));
	}
	
	public static void main(String[] args) throws IOException {
		int i;
		BufferedReader br;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new long[n + 1];
		for (i = 0; i < n; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		arr[n] = INF;
		tree = new int[4 * n];
		init(1, 0, n - 1);
		System.out.print(getMax(0, n - 1));
	}
}
