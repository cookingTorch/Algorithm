import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	private static final int MAX_A = 100_001;

	public static void main(String[] args) throws IOException {
		int n;
		int k;
		int max;
		int left;
		int right;
		int[] arr;
		int[] cnt;
		ArrayDeque<Integer> dq;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ", false);
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr = new int[n];
		cnt = new int[MAX_A];
		max = 0;
		left = 0;
		right = -1;
		st = new StringTokenizer(br.readLine(), " ", false);
		while (++right < n) {
			if (cnt[arr[right] = Integer.parseInt(st.nextToken())]++ == k) {
				max = Math.max(max, right - left);
				while (--cnt[arr[left++]] != k);
			}
		}
		System.out.print(Math.max(max, right - left));
	}
}
