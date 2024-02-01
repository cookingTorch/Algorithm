import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	
	private static int n;
	private static int[][] ingredient;
	
	private static int calc(int num) {
		int sour, bitter, i;
		
		sour = 1;
		bitter = 0;
		for (i = 0; i < n; i++) {
			if ((num & 1) == 1) {
				sour *= ingredient[i][0];
				bitter += ingredient[i][1];
			}
			num >>= 1;
		}
		return Math.abs(sour - bitter);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int max, ans, i;
		
		n = Integer.parseInt(br.readLine());
		ingredient = new int[n][];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			ingredient[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		}
		ans = INF;
		max = 1 << n;
		for (i = 1; i < max; i++) {
			ans = Math.min(ans, calc(i));
		}
		System.out.print(ans);
	}
}
