import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, max1, max2, num, i;
		int[] cnt;
		
		n = Integer.parseInt(br.readLine());
		cnt = new int[n + 1];
		max1 = 0;
		max2 = 0;
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			num = Integer.parseInt(st.nextToken()) + 1;
			if (num > n || ++cnt[num] == 3) {
				System.out.print("0");
				return;
			} else if (cnt[num] == 1) {
				max1 = Math.max(max1, num);
			} else {
				max2 = Math.max(max2, num);
			}
		}
		if (max1 + max2 == n) {
			System.out.print((int) Math.pow(2, max2 + (max1 == max2 ? 0 : 1)));
		} else {
			System.out.print("0");
		}
	}
}
