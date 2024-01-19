import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int r, c, d;
	private static int[][] room;
	
	private static boolean backward() {
		switch (d) {
		case 0:
			r++;
			break;
		case 1:
			c--;
			break;
		case 2:
			r--;
			break;
		case 3:
			c++;
			break;
		}
		return room[r][c] < 1;
	}
	
	private static void forward() {
		int x, y;
		
		x = r;
		y = c;
		switch (d) {
		case 0:
			x = r - 1;
			break;
		case 1:
			y = c + 1;
			break;
		case 2:
			x = r + 1;
			break;
		case 3:
			y = c - 1;
			break;
		}
		if (room[x][y] == 0) {
			r = x;
			c = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m, cnt, i, j;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		room = new int[n][m];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < m; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cnt = 0;
		while (true) {
			if (room[r][c] == 0) {
				room[r][c]--;
				cnt++;
			}
			if (!(room[r][c - 1] == 0 || room[r - 1][c] == 0 || room[r][c + 1] == 0 || room[r + 1][c] == 0)) {
				if (backward()) {
					continue;
				} else {
					break;
				}
			}
			d = (d + 3) % 4;
			forward();
		}
		System.out.println(cnt);
	}
}