import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int MAX_TIME = 100_000;
	
	public static void main(String[] args) throws IOException {
		int n;
		int k;
		int s;
		int e;
		int i;
		int j;
		int left;
		int right;
		int t;
		int ansS;
		int ansE;
		long max;
		long time;
		long[] arr;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		arr = new long[MAX_TIME + 2];
		for (i = 0; i < n; i++) {
			k = Integer.parseInt(br.readLine());
			for (j = 0; j < k; j++) {
				st = new StringTokenizer(br.readLine());
				s = Integer.parseInt(st.nextToken());
				e = Integer.parseInt(st.nextToken());
				arr[s]++;
				arr[e]--;
			}
		}
		for (i = 1; i <= MAX_TIME + 1; i++) {
			arr[i] += arr[i - 1];
		}
		time = 0L;
		for (i = 0; i < t; i++) {
			time += arr[i];
		}
		ansS = 0;
		ansE = t;
		max = time;
		for (left = 0, right = t; right <= MAX_TIME + 1; left++, right++) {
			time -= arr[left];
			time += arr[right];
			if (time > max) {
				max = time;
				ansS = left + 1;
				ansE = right + 1;
			}
			max = Math.max(max, time);
		}
		sb = new StringBuilder().append(ansS).append(' ').append(ansE);
		System.out.print(sb);
	}
}
