import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, left, right, cnt, num, i;
		int[] arr;
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		cnt = 0;
		for (i = 0; i < n; i++) {
			num = arr[i];
			if (i == 0) {
				left = 1;
			} else {
				left = 0;
			}
			if (i == n - 1) {
				right = n - 2;
			} else {
				right = n - 1;
			}
			while (left < right) {
				if (arr[left] + arr[right] < num) {
					if (++left == i) {
						left++;
					}
				} else if (arr[left] + arr[right] > num) {
					if (--right == i) {
						right--;
					}
				} else {
					cnt++;
					break;
				}
			}
		}
		System.out.print(cnt);
	}
}
