import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[] arr;
	
	public static int binarySearch(int target, int end) {
		int left, right, mid;
		
		left = 0;
		right = end;
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
		StringTokenizer st;
		
		int n, num, idx, size, i;

		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		size = 0;
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			num = Integer.parseInt(st.nextToken());
			idx = binarySearch(num, size);
			arr[idx] = num;
			if (idx == size) {
				size++;
			}
		}
		System.out.print(size);
	}
}
