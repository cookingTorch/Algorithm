import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		int n;
		int i;
		int cnt;
		int full;
		int left;
		int right;
		long x;
		long half;
		long[] arr;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ", false);
		n = Integer.parseInt(st.nextToken());
		half = Long.parseLong(st.nextToken());
		x = half << 1;
		arr = new long[n + 1];
		st = new StringTokenizer(br.readLine(), " ", false);
		for (i = 1; i <= n; i++) {
			arr[i] = Long.parseLong(st.nextToken()) << 1;
		}
		Arrays.sort(arr);
		for (right = n; arr[right] == x; right--);
		full = n - right;
		cnt = 0;
		left = 1;
		while (left < right) {
			if (arr[left] + arr[right] >= half) {
				cnt++;
				right--;
			}
			left++;
		}
		System.out.print(full + cnt + (n - full - (cnt << 1)) / 3);
	}
}
