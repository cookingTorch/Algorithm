import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final long INF = Long.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int num, i, j, t;
		long min;
		String[] ans;
		
		ans = new String[101];
		ans[2] = "1";
		ans[3] = "7";
		ans[4] = "4";
		ans[5] = "2";
		ans[6] = "0";
		ans[7] = "8";
		for (i = 8; i <= 100; i++) {
			min = INF;
			for (j = 2; j <= i / 2; j++) {
				if (j != 6) {
					min = Math.min(min, Long.parseLong(ans[j] + ans[i - j]));
				} else {
					min = Math.min(min, Long.parseLong("6" + ans[i - j]));
				}
				if (i - j != 6) {
					min = Math.min(min, Long.parseLong(ans[i - j] + ans[j]));
				} else {
					min = Math.min(min, Long.parseLong("6" + ans[j]));
				}
			}
			ans[i] = Long.toString(min);
		}
		ans[6] = "6";
		t = Integer.parseInt(br.readLine());
		for (i = 0; i < t; i++) {
			num = Integer.parseInt(br.readLine());
			sb.append(ans[num]).append(' ');
			if (num == 2) {
				
			}
			if ((num & 1) == 1) {
				num -= 3;
				sb.append('7');
			}
			for (j = 0; j < num / 2; j++) {
				sb.append('1');
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
}