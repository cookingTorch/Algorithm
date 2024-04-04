import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	private static final int[][] combi2 = {
			{9, 3},
			{3, 9}
	};
	private static final int[][] combi3 = {
			{9, 3, 1},
			{9, 1, 3},
			{3, 9, 1},
			{3, 1, 9},
			{1, 9, 3},
			{1, 3, 9},
	};
	
	private static int maxX, maxY, maxZ;
	private static int dp2[][];
	private static int dp3[][][];
	
	private static int getDp2(int x, int y) {
		if (x == 0 && y == 0) {
			return 0;
		}
		if (dp2[x][y] != 0) {
			return dp2[x][y];
		}
		dp2[x][y] = INF;
		for (int[] damage : combi2) {
			dp2[x][y] = Math.min(dp2[x][y], getDp2(
				Math.max(0, x - damage[0]),
				Math.max(0, y - damage[1])
			) + 1);
		}
		return dp2[x][y];
	}
	
	private static int getDp3(int x, int y, int z) {
		if (x == 0 && y == 0 && z == 0) {
			return 0;
		}
		if (dp3[x][y][z] != 0) {
			return dp3[x][y][z];
		}
		dp3[x][y][z] = INF;
		for (int[] damage : combi3) {
			dp3[x][y][z] = Math.min(dp3[x][y][z], getDp3(
				Math.max(0, x - damage[0]),
				Math.max(0, y - damage[1]),
				Math.max(0, z - damage[2])
			) + 1);
		}
		return dp3[x][y][z];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n;
		
		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		switch (n) {
		case 1:
			System.out.print((Integer.parseInt(st.nextToken()) - 1) / 9 + 1);
			return;
		case 2:
			maxX = Integer.parseInt(st.nextToken());
			maxY = Integer.parseInt(st.nextToken());
			dp2 = new int[maxX + 1][maxY + 1];
			System.out.print(getDp2(maxX, maxY));
			return;
		case 3:
			maxX = Integer.parseInt(st.nextToken());
			maxY = Integer.parseInt(st.nextToken());
			maxZ = Integer.parseInt(st.nextToken());
			dp3 = new int[maxX + 1][maxY + 1][maxZ + 1];
			System.out.print(getDp3(maxX, maxY, maxZ));
			return;
		}
	}
}
