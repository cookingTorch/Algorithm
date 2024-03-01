import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, s, left, right, sum, min, i;
		int[] arr;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		if (s == 0) {
			System.out.print(0);
			return;
		}
		arr = new int[n];
		min = n + 1;
		sum = 0;
		left = 0;
		right = 0;
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			arr[right] = Integer.parseInt(st.nextToken());
			sum += arr[right++];
			if (sum >= s) {
				while (sum >= s) {
					sum -= arr[left++];
				}
				min = Math.min(min, right - left + 1);
			}
		}
		System.out.print(min == n + 1 ? 0 : min);
	}
}
