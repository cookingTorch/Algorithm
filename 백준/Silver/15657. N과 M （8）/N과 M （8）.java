import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int n, m;
	private static int[] arr, ans;
	
	private static StringBuilder combi(int start, int depth) {
		int i;
		StringBuilder sb;
		
		sb = new StringBuilder();
		if (depth == m) {
			for (i = 0; i < m; i++) {
				sb.append(ans[i]).append(' ');
			}
			return sb.append('\n');
		}
		for (i = start; i < n; i++) {
			ans[depth] = arr[i];
			sb.append(combi(i, depth + 1));
		}
		return sb;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int i;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		ans = new int[m];
		System.out.print(combi(0, 0));
	}
}
