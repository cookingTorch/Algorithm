import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int n, m;
	private static int[] ans;
	
	private static void combi(int start, int depth, StringBuilder sb) {
		int i;
		
		if (depth == m) {
			for (i = 0; i < m; i++) {
				sb.append(ans[i] + 1).append(' ');
			}
			sb.append('\n');
			return;
		}
		for (i = start; i < n; i++) {
			ans[depth] = i;
			combi(i, depth + 1, sb);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		ans = new int[m];
		combi(0, 0, sb);
		System.out.print(sb);
	}
}
