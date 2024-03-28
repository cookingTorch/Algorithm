import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	private static final int[] dx = {-1, 0, 1, 0};
	private static final int[] dy = {0, 1, 0, -1};
	private static final char WATER = '*';
	private static final char ROCK = 'X';
	private static final char START = 'S';
	private static final char CAVE = 'D';
	private static final char EMPTY = '.';
	private static final String KAKTUS = "KAKTUS";
	
	private static int r, c;
	private static int[][] waterTime;
	private static boolean[][] inQueue;
	private static ArrayList<int[]> waters;
	private static Queue<int[]> q;
	
	private static void bfs0() {
		int x, y, nx, ny, i;
		int[] curr;
		
		q = new ArrayDeque<>();
		for (int[] water : waters) {
			q.add(water);
		}
		while (!q.isEmpty()) {
			curr = q.poll();
			x = curr[0];
			y = curr[1];
			inQueue[x][y] = false;
			for (i = 0; i < 4; i++) {
				nx = x + dx[i];
				ny = y + dy[i];
				if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
					continue;
				}
				if (waterTime[x][y] + 1 < waterTime[nx][ny]) {
					waterTime[nx][ny] = waterTime[x][y] + 1;
					q.add(new int[] {nx, ny});
					inQueue[nx][ny] = true;
				}
			}
		}
	}
	
	private static int bfs1(int startX, int startY, int endX, int endY) {
		int x, y, nx, ny, i;
		int[] curr;
		int[][] time;
		
		time = new int[r][c];
		for (i = 0; i < r; i++) {
			Arrays.fill(time[i], INF);
		}
		time[startX][startY] = 0;
		q = new ArrayDeque<>();
		q.add(new int[] {startX, startY});
		while (!q.isEmpty()) {
			curr = q.poll();
			x = curr[0];
			y = curr[1];
			inQueue[x][y] = false;
			for (i = 0; i < 4; i++) {
				nx = x + dx[i];
				ny = y + dy[i];
				if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
					continue;
				}
				if (time[x][y] + 1 < time[nx][ny] && time[x][y] + 1 < waterTime[nx][ny]) {
					time[nx][ny] = time[x][y] + 1;
					q.add(new int[] {nx, ny});
					inQueue[x][y] = true;
				}
			}
		}
		return time[endX][endY];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int startX, startY, endX, endY, ans, i, j;
		String str;
		
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		startX = 0;
		startY = 0;
		endX = 0;
		endY = 0;
		waterTime = new int[r][c];
		waters = new ArrayList<>();
		for (i = 0; i < r; i++) {
			str = br.readLine();
			for (j = 0; j < c; j++) {
				switch (str.charAt(j)) {
				case WATER:
					waters.add(new int[] {i, j});
					waterTime[i][j] = 0;
					break;
				case ROCK:
					waterTime[i][j] = -1;
					break;
				case START:
					startX = i;
					startY = j;
					waterTime[i][j] = INF;
					break;
				case CAVE:
					endX = i;
					endY = j;
					waterTime[i][j] = -1;
					break;
				case EMPTY:
					waterTime[i][j] = INF;
				}
			}
		}
		inQueue = new boolean[r][c];
		bfs0();
		waterTime[endX][endY] = INF;
		ans = bfs1(startX, startY, endX, endY);
		System.out.print(ans == INF ? KAKTUS : ans);
	}
}
