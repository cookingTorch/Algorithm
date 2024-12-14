import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		int n;
		int i;
		int head;
		int minThorax;
		int maxThorax;
		long cnt;
		long thr;
		long abdomen;
		long[] arr;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new long[n];
		st = new StringTokenizer(br.readLine(), " ", false);
		arr[0] = Integer.parseInt(st.nextToken());
		for (i = 1; i < n; i++) {
			arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
		}
		cnt = 0L;
		minThorax = 0;
		maxThorax = n - 1;
		abdomen = arr[n - 1];
		thr = (abdomen + 2L) / 3L;
		for (head = 0; arr[head] < thr; head++) {
			while (abdomen - arr[maxThorax] <= arr[head]) {
				maxThorax--;
			};
			while (arr[minThorax] <= abdomen + arr[head] >> 1) {
				minThorax++;
			}
			if (maxThorax < minThorax) {
				break;
			}
			cnt += maxThorax - minThorax + 1;
		}
		System.out.print(cnt);
	}
}
