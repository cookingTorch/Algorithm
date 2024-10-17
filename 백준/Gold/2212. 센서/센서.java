import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		int n;
		int k;
		int i;
		int thr;
		int sum;
		int[] diffs;
		int[] sensors;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		sensors = new int[n];
		st = new StringTokenizer(br.readLine(), " ", false);
		for (i = 0; i < n; i++) {
			sensors[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(sensors);
		diffs = new int[--n];
		for (i = 0; i < n; i++) {
			diffs[i] = sensors[i + 1] - sensors[i];
		}
		Arrays.sort(diffs);
		sum = 0;
		thr = n - (k - 1);
		for (i = 0; i < thr; i++) {
			sum += diffs[i];
		}
		System.out.print(sum);
	}
}
