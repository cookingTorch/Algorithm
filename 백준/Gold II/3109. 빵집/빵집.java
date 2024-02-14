import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int c, ans;
	private static char[][] map;
	
	private static boolean dfs(int row, int depth) {
		if (depth == c) {
			return true;
		}
		if (map[row][depth] == 'x') {
			return false;
		}
		map[row][depth] = 'x';
		if (dfs(row - 1, depth + 1)) {
			return true;
		}
		if (dfs(row, depth + 1)) {
			return true;
		}
		if (dfs(row + 1, depth + 1)) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int r, i;
		
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r + 2][];
		map[0] = new char[c];
		Arrays.fill(map[0], 'x');
		for (i = 1; i <= r; i++) {
			map[i] = br.readLine().toCharArray();
		}
		map[r + 1] = new char[c];
		Arrays.fill(map[r + 1], 'x');
		ans = 0;
		for (i = 1; i <= r; i++) {
			if (dfs(i, 0)) {
				ans++;
			}
		}
		System.out.print(ans);
	}
}
