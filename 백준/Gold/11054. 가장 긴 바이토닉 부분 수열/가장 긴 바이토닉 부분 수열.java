import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[] arr;
	
	private static final int binarySearch(int num, int right) {
		int left, mid;
		
		left = 0;
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
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, idx, size, max, i;
		int[] nums, lis;
		
		n = Integer.parseInt(br.readLine());
		nums = new int[n];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		lis = new int[n];
		arr = new int[n];
		arr[0] = nums[0];
		lis[0] = 1;
		for (i = 1; i < n; i++) {
			lis[i] = lis[i - 1];
			idx = binarySearch(nums[i], lis[i]);
			arr[idx] = nums[i];
			if (idx == lis[i]) {
				lis[i]++;
			}
		}
		arr = new int[n];
		arr[0] = nums[n - 1];
		size = 1;
		max = size + lis[n - 1] - 1;
		for (i = n - 2; i >= 0; i--) {
			idx = binarySearch(nums[i], size);
			arr[idx] = nums[i];
			if (idx == size) {
				size++;
				max = Math.max(max, size + lis[i] - 1);
			}
		}
		System.out.print(max);
	}
}
