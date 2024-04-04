import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[][][] combi = {
			null,
			{{1, 0, 0, 1}, {0, 1, 0, 1}, {0, 0, 1, 1}},
			{{1, 1, 0, 2}, {1, 0, 1, 2}, {0, 1, 1, 2}, {2, 0, 0, 1}, {0, 2, 0, 1}, {0, 0, 2, 1}},
			{{1, 1, 1, 6}, {3, 0, 0, 1}, {0, 3, 0, 1}, {0, 0, 3, 1}},
			{{2, 2, 0, 6}, {2, 0, 2, 6}, {0, 2, 2, 6}, {4, 0, 0, 1}, {0, 4, 0, 1}, {0, 0, 4, 1}},
			{{5, 0, 0, 1}, {0, 5, 0, 1}, {0, 0, 5, 1}},
			{{2, 2, 2, 90}, {3, 3, 0, 20}, {3, 0, 3, 20}, {0, 3, 3, 20}, {6, 0, 0, 1}, {0, 6, 0, 1}, {0, 0, 6, 1}},
			{{7, 0, 0, 1}, {0, 7, 0, 1}, {0, 0, 7, 1}},
			{{4, 4, 0, 70}, {4, 0, 4, 70}, {0, 4, 4, 70}, {8, 0, 0, 1}, {0, 8, 0, 1}, {0, 0, 8, 1}},
			{{3, 3, 3, 1680}, {9, 0, 0, 1}, {0, 9, 0, 1}, {0, 0, 9, 1}},
			{{5, 5, 0, 252}, {5, 0, 5, 252}, {0, 5, 5, 252}, {10, 0, 0, 1}, {0, 10, 0, 1}, {0, 0, 10, 1}}
	};
	
	private static long dp[][][][];
	
	private static long getDp(int level, int x, int y, int z) {
		if (level == 0) {
			return 1;
		}
		if (dp[level][x][y][z] != 0) {
			return dp[level][x][y][z];
		}
		for (int[] colors : combi[level]) {
			if (colors[0] > x || colors[1] > y || colors[2] > z) {
				continue;
			}
			dp[level][x][y][z] += getDp(level - 1, x - colors[0], y - colors[1], z - colors[2]) * colors[3];
		}
		return dp[level][x][y][z];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, maxX, maxY, maxZ, sum;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		maxX = Integer.parseInt(st.nextToken());
		maxY = Integer.parseInt(st.nextToken());
		maxZ = Integer.parseInt(st.nextToken());
		sum = maxX + maxY + maxZ;
		if (sum < (n * (n + 1)) >> 1) {
			System.out.print("0");
			return;
		}
		dp = new long[n + 1][maxX + 1][maxY + 1][maxZ + 1];
		System.out.print(getDp(n, maxX, maxY, maxZ));
	}
}
