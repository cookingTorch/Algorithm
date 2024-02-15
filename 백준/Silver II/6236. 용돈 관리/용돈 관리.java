import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m, left, right, mid, cnt, curr, i;
		int[] cost;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		cost = new int[n];
		left = 0;
		right = 0;
		for (i = 0; i < n; i++) {
			cost[i] = Integer.parseInt(br.readLine());
			left = Math.max(left, cost[i]);
			right += cost[i];
		}
		while (left < right) {
			mid = (left + right) / 2;
			cnt = 0;
			curr = 0;
			for (i = 0; i < n; i++) {
				if (curr < cost[i]) {
					curr = mid;
					cnt++;
				}
				curr -= cost[i];
			}
			if (cnt > m) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		System.out.print(right);
	}
}
