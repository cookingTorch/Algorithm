import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, i;
		long[] x, y;
		long ans;
		
		n = Integer.parseInt(br.readLine());
		x = new long[n + 1];
		y = new long[n + 1];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			x[i] = Long.parseLong(st.nextToken());
			y[i] = Long.parseLong(st.nextToken());
		}
		x[n] = x[0];
		y[n] = y[0];
		ans = 0;
		for (i = 0; i < n; i++) {
			ans += (x[i] * y[i + 1] - x[i + 1] * y[i]);
		}
		ans = Math.abs(ans);
		sb.append(ans / 2);
		if (ans % 2 == 1) {
			sb.append(".5");
		} else {
			sb.append(".0");
		}
		System.out.print(sb);
	}
}
