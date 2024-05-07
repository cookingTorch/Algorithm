import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int MIN = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		int n;
		int i;
		int sum;
		int max;
		int forward;
		int backward;
		int[] arr;
		int[] prefix;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		arr[0] = Integer.parseInt(st.nextToken());
		sum = arr[0];
		max = arr[0];
		prefix = new int[n];
		prefix[0] = arr[0];
		for (i = 1; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			prefix[i] = prefix[i - 1] + arr[i];
			sum += arr[i];
			max = Math.max(max, arr[i]);
		}
		backward = MIN;
		for (i = 1; i < n - 1; i++) {
			backward = Math.max(backward, prefix[i] - (arr[i] << 1));
		}
		backward += sum - arr[n - 1];
		forward = MIN;
		prefix[n - 1] = arr[n - 1];
		for (i = n - 2; i > 0; i--) {
			prefix[i] = prefix[i + 1] + arr[i];
			forward = Math.max(forward, prefix[i] - (arr[i] << 1));
		}
		forward += sum - arr[0];
		sum -= arr[0] + arr[n - 1] - max;
		System.out.print(Math.max(Math.max(forward, backward), sum));
	}
}
