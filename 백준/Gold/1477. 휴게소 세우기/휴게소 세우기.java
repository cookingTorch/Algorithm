import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m, l, left, right, mid, cnt, prev, i;
		int[] locs;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()) + 2;
		m = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		locs = new int[n];
		st = new StringTokenizer(br.readLine());
		for (i = 1; i < n - 1; i++) {
			locs[i] = Integer.parseInt(st.nextToken());
		}
		locs[n - 1] = l;
		Arrays.sort(locs);
		right = locs[1];
		for (i = 2; i < n; i++) {
			right = Math.max(right, locs[i] - locs[i - 1]);
		}
		left = 0;
		while (left < right) {
			mid = (left + right) / 2;
			cnt = m;
			prev = 0;
			for (i = 0;;) {
				if ((prev += mid) >= locs[i]) {
					prev = locs[i];
					if (++i == n) {
						break;
					}
				} else {
					if (--cnt < 0) {
						break;
					}
				}
			}
			if (cnt < 0) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		System.out.print(right);
	}
}
