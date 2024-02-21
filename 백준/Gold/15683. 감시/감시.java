import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static final int UP = 0;
	private static final int DOWN = 1;
	private static final int LEFT = 2;
	private static final int RIGHT = 3;
	private static final int[] dirX = {-1, 1, 0, 0};
	private static final int[] dirY = {0, 0, -1, 1};
	
	private static int n, m, val, max, size, ans;
	private static int[] arr;
	private static int[][] room;
	private static boolean[][] visited;
	private static ArrayList<int[]> cctvs;
	
	private static void visit(int x, int y, int dir) {
		while (0 <= x && x < n && 0 <= y && y < m && room[x][y] < 6) {
			if (!visited[x][y]) {
				visited[x][y] = true;
				val--;
			}
			x = x + dirX[dir];
			y = y + dirY[dir];
		}
	}
	
	private static int solution() {
		int x, y, i;
		int[] cctv;
		
		visited = new boolean[n][m];
		val = max;
		for (i = 0; i < size; i++) {
			cctv = cctvs.get(i);
			x = cctv[0];
			y = cctv[1];
			switch (room[x][y]) {
			case 1:
				switch (arr[i]) {
				case 0:
					visit(x, y, RIGHT);
					break;
				case 1:
					visit(x, y, DOWN);
					break;
				case 2:
					visit(x, y, LEFT);
					break;
				case 3:
					visit(x, y, UP);
				}
				break;
			case 2:
				switch (arr[i]) {
				case 0:
					visit(x, y, LEFT);
					visit(x, y, RIGHT);
					break;
				case 1:
					visit(x, y, UP);
					visit(x, y, DOWN);
				}
				break;
			case 3:
				switch (arr[i]) {
				case 0:
					visit(x, y, UP);
					visit(x, y, RIGHT);
					break;
				case 1:
					visit(x, y, RIGHT);
					visit(x, y, DOWN);
					break;
				case 2:
					visit(x, y, DOWN);
					visit(x, y, LEFT);
					break;
				case 3:
					visit(x, y, LEFT);
					visit(x, y, UP);
				}
				break;
			case 4:
				switch (arr[i]) {
				case 0:
					visit(x, y, LEFT);
					visit(x, y, UP);
					visit(x, y, RIGHT);
					break;
				case 1:
					visit(x, y, UP);
					visit(x, y, RIGHT);
					visit(x, y, DOWN);
					break;
				case 2:
					visit(x, y, RIGHT);
					visit(x, y, DOWN);
					visit(x, y, LEFT);
					break;
				case 3:
					visit(x, y, DOWN);
					visit(x, y, LEFT);
					visit(x, y, UP);
				}
				break;
			case 5:
				visit(x, y, UP);
				visit(x, y, RIGHT);
				visit(x, y, DOWN);
				visit(x, y, LEFT);
			}
		}
		return val;
	}
	
	private static void rotate(int depth) {
		int i;
		int[] cctv;
		
		if (depth == size) {
			ans = Math.min(ans, solution());
			return;
		}
		cctv = cctvs.get(depth);
		switch (room[cctv[0]][cctv[1]]) {
		case 1: case 3: case 4:
			for (i = 0; i < 4; i++) {
				arr[depth] = i;
				rotate(depth + 1);
			}
			break;
		case 2:
			for (i = 0; i < 2; i++) {
				arr[depth] = i;
				rotate(depth + 1);
			}
			break;
		case 5:
			rotate(depth + 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int i, j;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		max = 0;
		room = new int[n][m];
		cctvs = new ArrayList<>();
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < m; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				if (room[i][j] < 6) {
					max++;
					if (room[i][j] != 0) {
						cctvs.add(new int[] {i, j});
					}
				}
			}
		}
		size = cctvs.size();
		arr = new int[size];
		ans = max;
		rotate(0);
		System.out.print(ans);
	}
}
