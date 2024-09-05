import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int SIZE = 1 << 3;
	private static final int TOP = 1 << 2;
	private static final int MID = 1 << 1;
	private static final int BOT = 1;
	private static final int EMPTY = -1;

	private static int[][] dp;

	private static int getDp(int idx, int bit) {
		int i;
		int sum;
		int nextBit;

		if (dp[idx][bit] != EMPTY) {
			return dp[idx][bit];
		}
		if (bit == 0) {
			return dp[idx][bit] = getDp(idx - 1,TOP | MID | BOT);
		}
		sum = 0;
		nextBit = TOP | MID | BOT;
		for (i = 1; i < SIZE; i <<= 1) {
			if ((bit & i) != 0) {
				nextBit ^= i;
			}
		}
		sum += getDp(idx - 1, nextBit);
		if ((bit & (TOP | MID)) == (TOP | MID)) {
			sum += getDp(idx, bit ^ (TOP | MID));
		}
		if ((bit & (MID | BOT)) == (MID | BOT)) {
			sum += getDp(idx, bit ^ (MID | BOT));
		}
		return dp[idx][bit] = sum;
	}

	public static void main(String[] args) throws IOException {
		int n;
		int i;
		int j;
		BufferedReader br;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		dp = new int[n + 1][SIZE];
		for (i = 1; i <= n; i++) {
			for (j = 0; j < SIZE; j++) {
				dp[i][j] = EMPTY;
			}
		}
		dp[0][TOP | MID | BOT] = 1;
		System.out.println(getDp(n, TOP | MID | BOT));
	}
}
