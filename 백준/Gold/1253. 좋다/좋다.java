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
		
		if (idx == 0) {
			left = 1;
		} else {
			left = 0;
		}
		if (idx == n - 1) {
			right = n - 2;
		} else {
			right = n - 1;
		}
		while (left < right) {
			if (arr[left] + arr[right] < num) {
				if (++left == idx) {
					left++;
				}
			} else if (arr[left] + arr[right] > num) {
				if (--right == idx) {
					right--;
				}
			} else {
				return true;
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
		for (i = 0; i < n; i++) {
			if (isGood(i, arr[i])) {
				cnt++;
			}
		}
		System.out.print(cnt);
	}
}
