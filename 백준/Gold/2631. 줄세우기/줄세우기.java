import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static int[] arr;
	
	public static int lowerBound(int num, int right) {
		int left, mid;
		
		left = 0;
		while (left < right) {
			mid = (left + right) / 2;
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
		
		int n, size, idx, num, i;
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		size = 0;
		for (i = 0; i < n; i++) {
			num = Integer.parseInt(br.readLine());
			idx = lowerBound(num, size);
			arr[idx] = num;
			if (idx == size) {
				size++;
			}
		}
		System.out.print(n - size);
	}
}
