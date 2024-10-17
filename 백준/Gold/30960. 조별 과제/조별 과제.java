import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		int n;
		int i;
		int min;
		int half;
		int suffix;
		int[] arr;
		int[] prefix;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		st = new StringTokenizer(br.readLine(), " ", false);
		for (i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		half = n >> 1;
		prefix = new int[half];
		prefix[0] = arr[1] - arr[0];
		for (i = 1; i < half; i++) {
			prefix[i] = prefix[i - 1] + arr[(i << 1) + 1] - arr[i << 1];
		}
		suffix = arr[n - 1] - arr[n - 2];
		min = prefix[half - 1] + suffix;
		for (i = half - 2; i >= 0; i--) {
			suffix += arr[(i << 1) + 2] - arr[(i << 1) + 1];
			min = Math.min(min, prefix[i] + suffix);
		}
		System.out.print(min);
	}
}
