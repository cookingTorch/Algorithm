import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[] ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, i;
		String str;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		str = br.readLine();
		ans = new int[n + 1];
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			ans[Integer.parseInt(st.nextToken())] += Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(str);
		st.nextToken();
		sb.append(ans[1]).append(' ');
		for (i = 2; i <= n; i++) {
			sb.append(ans[i] += ans[Integer.parseInt(st.nextToken())]).append(' ');
		}
		System.out.print(sb);
	}
}
