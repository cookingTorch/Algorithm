import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		int n;
		int i;
		int num;
		int idx;
		int sum;
		int min;
		int temp;
		int[] arr;
		long ans;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		sum = 0;
		idx = 0;
		ans = 0L;
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			num = Integer.parseInt(st.nextToken());
			temp = num;
			if (sum < 0 && temp > 0) {
				for (; idx < i; idx++) {
					if (arr[idx] < 0) {
						min = Math.min(-arr[idx], temp);
						temp -= min;
						arr[idx] += min;
						ans += (i - idx) * min;
					}
					if (temp == 0) {
						break;
					}
				}
			} else if (sum > 0 && temp < 0) {
				for (; idx < i; idx++) {
					if (arr[idx] > 0) {
						min = Math.min(arr[idx], -temp);
						temp += min;
						arr[idx] -= min;
						ans += (i - idx) * min;
					}
					if (temp == 0) {
						break;
					}
				}
			}
			sum += num;
			arr[i] = temp;
		}
		System.out.print(ans);
	}
}
