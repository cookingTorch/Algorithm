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
	
	private static int[] arr;
	private static Node[] tree;
	
	private static final Node combine(Node left, Node right) {
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
		return tree[node] = combine(init(node << 1, start, mid), init(node << 1 | 1, mid + 1, end));
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
		return combine(query(node << 1, start, mid, left, right),
				query(node << 1 | 1, mid + 1, end, left, right));
	}
	
	public static void main(String[] args) throws IOException {
		int n;
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
			sb.append(query(1, 1, n, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())).max).append('\n');
		}
		System.out.print(sb);
	}
}
