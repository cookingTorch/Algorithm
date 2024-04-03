import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int AIR_CLEANER = -1;
	private static final int[] dx = {-1, 0, 1, 0};
	private static final int[] dy = {0, 1, 0, -1};
	
	private static int r, c, x1, y1, x2, y2;
	private static int[][] map, diff;
	
	private static void spread() {
		int x, y, nx, ny, i;
		
		for (x = 0; x < r; x++) {
			for (y = 0; y < c; y++) {
				for (i = 0; i < 4; i++) {
					nx = x + dx[i];
					ny = y + dy[i];
					if (0 <= nx && nx < r && 0 <= ny && ny < c && map[nx][ny] != AIR_CLEANER && map[x][y] > 0) {
						diff[nx][ny] += map[x][y] / 5;
						diff[x][y] -= map[x][y] / 5;
					}
				}
			}
		}
		for (x = 0; x < r; x++) {
			for (y = 0; y < c; y++) {
				map[x][y] += diff[x][y];
				diff[x][y] = 0;
			}
		}
	}
	
	private static void clean() {
		int x, y, nx, ny, i;
		
		x = x1 - 1;
		y = y1;
		i = 0;
		while (x != x1 || y != y1) {
			nx = x + dx[i];
			ny = y + dy[i];
			if (nx < 0 || nx > x1 || ny < 0 || ny == c) {
				i++;
				continue;
			}
			map[x][y] = map[nx][ny];
			x = nx;
			y = ny;
		}
		map[x1][y1 + 1] = 0;
		x = x2 + 1;
		y = y2;
		i = 2;
		while (x != x2 || y != y2) {
			nx = x + dx[i];
			ny = y + dy[i];
			if (nx < x2 || nx == r || ny < 0 || ny == c) {
				i = (i + 3) % 4;
				continue;
			}
			map[x][y] = map[nx][ny];
			x = nx;
			y = ny;
		}
		map[x2][y2 + 1] = 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t, sum, i, j;
		
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		map = new int[r][c];
		for (i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == AIR_CLEANER) {
					x2 = i;
					y2 = j;
					x1 = x2 - 1;
					y1 = y2;
				}
			}
		}
		diff = new int[r][c];
		for (i = 0; i < t; i++) {
			spread();
			clean();
		}
		sum = 2;
		for (i = 0; i < r; i++) {
			for (j = 0; j < c; j++) {
				sum += map[i][j];
			}
		}
		System.out.print(sum);
	}
}
