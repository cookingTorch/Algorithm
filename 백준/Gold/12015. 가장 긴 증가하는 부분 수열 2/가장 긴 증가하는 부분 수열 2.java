import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[] arr;

	public static int binarySearch(int target, int end) {
		int left;
		int right;
		int mid;

		left = 0;
		right = end;
		while (left < right) {
			mid = left + right >> 1;
			if (arr[mid] < target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return right;
	}

	public static void main(String[] args) throws IOException {
		int n;
		int num;
		int idx;
		int size;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		size = 0;
		st = new StringTokenizer(br.readLine());
		while (n-- > 0) {
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
