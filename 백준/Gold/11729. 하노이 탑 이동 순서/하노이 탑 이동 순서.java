import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int SIZE = 4;
	
	private static StringBuilder[][] move;
	private static StringBuilder[][][] dp;
	
	private static int getHanoi(int size) {
		if (size == 1) {
			return 1;
		}
		return 2 * getHanoi(size - 1) + 1;
	}
	
	private static StringBuilder getDp(int size, int from, int to, int mid) {
		if (dp[size][from][to] != null) {
			return dp[size][from][to];
		}
		if (size == 1) {
			return dp[size][from][to] = move[from][to];
		}
		return dp[size][from][to] = new StringBuilder()
				.append(getDp(size - 1, from, mid, to))
				.append(move[from][to])
				.append(getDp(size - 1, mid, to, from));
	}

	public static void main(String[] args) throws IOException {
		int n;
		int i;
		int j;
		
		n = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
		move = new StringBuilder[SIZE][SIZE];
		for (i = 1; i < SIZE; i++) {
			for (j = 1; j < SIZE; j++) {
				if (i == j) {
					continue;
				}
				move[i][j] = new StringBuilder().append(i).append(' ').append(j).append('\n');
			}
		}
		dp = new StringBuilder[n + 1][SIZE][SIZE];
		System.out.print(new StringBuilder().append(getHanoi(n)).append('\n').append(getDp(n, 1, 3, 2)));
	}
}
