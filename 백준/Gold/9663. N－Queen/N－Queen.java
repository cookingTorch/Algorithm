import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static int n, board, ans;
	
	private static void dfs(int depth, int full, int left, int right) {
		int available, position;
		
		if (depth == n) {
			ans++;
			return;
		}
		available = ~(full | left | right) & board;
		for (; available > 0; available -= position) {
			position = available & -available;
			dfs(depth + 1, full | position, (left | position) << 1, (right | position) >> 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		board = (1 << n) - 1;
		dfs(0, 0, 0, 0);
		System.out.print(ans);
	}
}
