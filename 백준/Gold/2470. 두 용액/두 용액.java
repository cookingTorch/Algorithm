import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		int n;
		int i;
		int sum;
		int min;
		int ans1;
		int ans2;
		int left;
		int right;
		int[] arr;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		min = INF;
		left = 0;
		right = n - 1;
		ans1 = arr[left];
		ans2 = arr[right];
		while (left < right) {
			sum = arr[left] + arr[right];
			if (Math.abs(sum) < min) {
				min = Math.abs(sum);
				ans1 = arr[left];
				ans2 = arr[right];
			}
			if (sum < 0) {
				left++;
			} else if (sum > 0) {
				right--;
			} else {
				break;
			}
		}
		System.out.print(new StringBuilder().append(ans1).append(' ').append(ans2));
	}
}
