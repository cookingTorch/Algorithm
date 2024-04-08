import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int n;
	private static int[] arr;
	private static int[][] tree;
	
	private static int[] merge(int[] arr1, int[] arr2) {
		int size, size1, size2, i, i1, i2;
		int[] result;
		
		size1 = arr1.length;
		size2 = arr2.length;
		size = size1 + size2;
		result = new int[size];
		for (i = 0, i1 = 0, i2 = 0; i < size; i++) {
			if (i1 < size1 && (i2 == size2 || arr1[i1] < arr2[i2])) {
				result[i] = arr1[i1++];
			} else {
				result[i] = arr2[i2++];
			}
		}
		return result;
	}
	
	private static void init(int node, int start, int end) {
		int mid;
		
		if (start == end) {
			tree[node] = new int[1];
			tree[node][0] = arr[start];
			return;
		}
		mid = (start + end) >> 1;
		init(node << 1, start, mid);
		init(node << 1 | 1, mid + 1, end);
		tree[node] = merge(tree[node << 1], tree[node << 1 | 1]);
	}
	
	private static int lowerBound(int[] arr, int num) {
		int left, right, mid;
		
		left = 0;
		right = arr.length;
		while (left < right) {
			mid = (left + right) >> 1;
			if (arr[mid] < num) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return right;
	}
	
	private static int query(int node, int start, int end, int left, int right, int num) {
		int mid;
		
		if (end < left || right < start) {
			return 0;
		}
		if (left <= start && end <= right) {
			return lowerBound(tree[node], num);
		}
		mid = (start + end) >> 1;
		return query(node << 1, start, mid, left, right, num) + query(node << 1 | 1, mid + 1, end, left, right, num);
	}
	
	private static int upperBound(int i, int j, int k) {
		int left, right, mid;
		
		left = 0;
		right = n;
		while (left < right) {
			mid = (left + right) >> 1;
			if (query(1, 1, n, i, j, tree[1][mid]) <= k - 1) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return left;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int m, i, j, k, l;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		tree = new int[4 * n][];
		init(1, 1, n);
		for (l = 0; l < m; l++) {
			st = new StringTokenizer(br.readLine());
			i = Integer.parseInt(st.nextToken());
			j = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			sb.append(tree[1][upperBound(i, j, k) - 1]).append('\n');
		}
		System.out.print(sb);
	}
}
