import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int MOD = 100_000;
	
	public static void main(String[] args) throws IOException {
		int w;
		int h;
		int i;
		int j;
		int[][] up;
		int[][] right;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		up = new int[w + 1][h + 1];
		right = new int[w + 1][h + 1];
		up[1][1] = 1;
		right[1][1] = 1;
		for (i = 1, j = 2; i <= w; i++, j = 1) {
			for (; j <= h; j++) {
				up[i][j] = (up[i][j - 1] + right[i - 1][j - 1]) % MOD;
				right[i][j] = (right[i - 1][j] + up[i - 1][j - 1]) % MOD;
			}
		}
		System.out.print((up[w][h] + right[w][h]) % MOD);
	}
}
