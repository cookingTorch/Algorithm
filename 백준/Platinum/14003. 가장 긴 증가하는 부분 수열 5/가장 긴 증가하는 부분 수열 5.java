import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[] arr;
	
	public static int binarySearch(int target, int right) {
		int left, mid;
		
		left = 0;
		while (left < right) {
			mid = (left + right) / 2;
			if (arr[mid] < target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return right;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, cnt, size, i;
		int[] nums, idx, ans;

		n = Integer.parseInt(br.readLine());
		nums = new int[n];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		idx = new int[n];
		arr = new int[n];
		size = 0;
		for (i = 0; i < n; i++) {
			idx[i] = binarySearch(nums[i], size);
			arr[idx[i]] = nums[i];
			if (idx[i] == size) {
				size++;
			}
		}
		ans = new int[size];
		sb.append(size).append('\n');
		cnt = size - 1;
		for (i = n - 1; cnt >= 0; i--) {
			if (idx[i] == cnt) {
				ans[cnt--] = nums[i];
			}
		}
		for (i = 0; i < size; i++) {
			sb.append(ans[i]).append(' ');
		}
		System.out.print(sb);
	}
}
