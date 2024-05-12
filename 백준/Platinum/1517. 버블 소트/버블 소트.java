import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int n;
	private static int[] arr;
	private static int[] tree;
	private static int[] unique;
	
	private static final int getIdx(int num) {
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
	
	private static final void compression() {
		int i;
		
		unique = Arrays.stream(arr).sorted().toArray();
		for (i = 0; i < n; i++) {
			arr[i] = getIdx(arr[i]);
		}
	}
	
	private static final int insert(int node, int start, int end, int num) {
		int mid;
		int ret;
		
		tree[node]++;
		if (start == end) {
			return tree[node] - 1;
		}
		mid = start + end >> 1;
		if (num <= mid) {
			ret = insert(node << 1, start, mid, num);
		} else {
			ret = tree[node << 1] + insert(node << 1 | 1, mid + 1, end, num);
		}
		return ret;
	}
	
	public static void main(String[] args) throws IOException {
		int i;
		long cnt;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		compression();
		tree = new int[4 * n];
		cnt = 0L;
		for (i = 0; i < n; i++) {
			cnt += i - insert(1, 0, n - 1, arr[i]);
		}
		System.out.print(cnt);
	}
}
