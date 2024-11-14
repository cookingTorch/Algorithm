import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		int n;
		int i;
		int max;
		int thr;
		int sum;
		int mid;
		int left;
		int right;
		int[] arr;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		max = 0;
		sum = 0;
		st = new StringTokenizer(br.readLine(), " ", false);
		for (i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]);
			sum += arr[i];
		}
		thr = Integer.parseInt(br.readLine());
		if (sum <= thr) {
			System.out.print(max);
			return;
		}
		left = 1;
		right = thr + 1;
		loop:
		while (left < right) {
			mid = left + right >> 1;
			sum = 0;
			for (i = 0; i < n; i++) {
				if ((sum += Math.min(arr[i], mid)) > thr) {
					right = mid;
					continue loop;
				}
			}
			left = mid + 1;
		}
		System.out.print(right - 1);
	}
}
