import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static void dfs(int n, int m, int d, int start, int[] arr, StringBuilder sb) {
		int i;
		
		if (d == m) {
			for (int num : arr) {
				sb.append(num).append(' ');
			}
			sb.append('\n');
		} else {
			for (i = start; i < n; i++) {
				arr[d] = i + 1;
				dfs(n, m, d + 1, i + 1, arr, sb);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		dfs(n, m, 0, 0, new int[m], sb);
		System.out.println(sb);
	}
}