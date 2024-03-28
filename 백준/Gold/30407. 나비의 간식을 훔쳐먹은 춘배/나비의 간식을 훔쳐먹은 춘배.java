import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int n, d, k, max;
	private static int[] r;
	private static int[][][] dp;
	
	private static void dfs(int x, int y, int z, int depth) {
		int hp, damage, next;
		
		if (depth == n) {
			max = Math.max(max, dp[x][y][z]);
			return;
		}
		hp = dp[x][y][z];
		damage = (Math.max(0, r[depth] - (d + y * k))) >> 1;
		if ((next = Math.max(0, hp - damage)) > dp[x + 1][y][z]) {
			if (next > 0) {
				dp[x + 1][y][z] = next;
				dfs(x + 1, y, z, depth + 1);
			}
		}
		damage = Math.max(0, r[depth] - (d + (y + 1) * k));
		if ((next = Math.max(0, hp - damage)) > dp[x][y + 1][z]) {
			if (next > 0) {
				dp[x][y + 1][z] = next;
				dfs(x, y + 1, z, depth + 1);
			}
		}
		if (depth < n - 1 && z == 0) {
			damage = Math.max(0, r[depth] - (d + y * k));
			if ((next = Math.max(0, hp - damage)) > dp[x][y + 1][z + 1]) {
				if (next > 0) {
					dp[x][y + 1][z + 1] = next;
					dfs(x, y + 1, z + 1, depth + 2);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int h, i;
		
		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		r = new int[n];
		for (i = 0; i < n; i++) {
			r[i] = Integer.parseInt(br.readLine());
		}
		dp = new int[n + 1][n + 1][2];
		dp[0][0][0] = h;
		max = 0;
		dfs(0, 0, 0, 0);
		System.out.print(max <= 0 ? -1 : max);
	}
}
