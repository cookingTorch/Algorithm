import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	private static int n;
	private static int[] arr;
	private static int[] tree;
	private static int[] unique;
	
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
	
	private static final int getRank(int node, int start, int end, int num) {
		int mid;
		int ret;
		
		if (start == end) {
			return tree[node] = 1;
		}
		mid = start + end >> 1;
		if (num <= mid) {
			ret = getRank(node << 1, start, mid, num) + tree[node << 1 | 1];
		} else {
			ret = getRank(node << 1 | 1, mid + 1, end, num);
		}
		tree[node] = tree[node << 1] + tree[node << 1 | 1];
		return ret;
	}
	
	private static final void compression() {
		int i;
		
		unique = Arrays.stream(arr).sorted().toArray();
		for (i = 0; i < n; i++) {
			arr[i] = lowerBound(arr[i]);
		}
	}
	
	public static void main(String[] args) throws IOException {
		int i;
		StringBuilder sb;
		BufferedReader br;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		for (i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		compression();
		tree = new int[n << 2];
		sb = new StringBuilder();
		for (int num : arr) {
			sb.append(getRank(1, 0, n - 1, num)).append('\n');
		}
		System.out.print(sb);
	}
}
