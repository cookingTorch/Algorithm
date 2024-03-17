import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int ans, ans2, n, i;
		
		n = Integer.parseInt(br.readLine());
		ans = 0;
		ans2 = 0;
		for (i = 1; i <= n; i++) {
			ans += i;
			ans2 += i * i * i;
		}
		sb.append(ans).append('\n').append(ans2).append('\n').append(ans2);
		System.out.print(sb);
	}
}
