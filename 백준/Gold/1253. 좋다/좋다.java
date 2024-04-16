import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int n;
	private static int[] arr;
	
	private static boolean isGood(int idx, int num) {
		int left, right;
		
		left = 0;
		right = n;
		while (left < right) {
			if (arr[left] + arr[right] < num) {
				left++;
			} else if (arr[left] + arr[right] > num) {
				right--;
			} else {
				if (left == idx) {
					left++;
				} else if (right == idx) {
					right--;
				} else {
					return true;
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int cnt, i;
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		cnt = 0;
		n--;
		for (i = 0; i <= n; i++) {
			if (isGood(i, arr[i])) {
				cnt++;
			}
		}
		System.out.print(cnt);
	}
}
