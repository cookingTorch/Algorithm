import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static long[] arr, tree;
	
	private static long init(int node, int start, int end) {
		int mid;
		
		if (start == end) {
			return tree[node] = arr[start];
		}
		mid = (start + end) / 2;
		return tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
	}
	
	private static long sum(int node, int start, int end, int left, int right) {
		int mid;
		
		if (right < start || end < left) {
			return 0;
		}
		if (left <= start && end <= right) {
			return tree[node];
		}
		mid = (start + end) / 2;
		return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);
	}
	
	private static long update(int node, int start, int end, int idx, long num) {
		int mid;
		
		if (idx < start || end < idx) {
			return tree[node];
		}
		if (start == end) {
			return tree[node] = num;
		}
		mid = (start + end) / 2;
		return tree[node] = update(node * 2, start, mid, idx, num) + update(node * 2 + 1, mid + 1, end, idx, num);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, q, x, y, i;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		arr = new long[n + 1];
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		tree = new long[4 * n];
		init(1, 1, n);
		for (i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			sb.append(sum(1, 1, n, Math.min(x, y), Math.max(x, y))).append('\n');
			update(1, 1, n, Integer.parseInt(st.nextToken()), Long.parseLong(st.nextToken()));
		}
		System.out.print(sb);
	}
}
