import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int[] MIN = {-1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m, k, cnt, size, row, col, ans, i, j;
		int[] min;
		int[][][] map;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		size = (n - 1) / m + 1;
		map = new int[size][m][2];
		cnt = 0;
		for (i = 0; i < size; i++) {
			for (j = 0; j < m; j++) {
				if (cnt++ < n) {
					st = new StringTokenizer(br.readLine());
					map[i][j][0] = Integer.parseInt(st.nextToken());
					map[i][j][1] = Integer.parseInt(st.nextToken());
				} else {
					map[i][j] = MIN;
				}
			}
		}
		row = k / m;
		col = k % m;
		min = map[0][col];
		for (i = 1; i <= row; i++) {
			if (map[i][col][0] < min[0] || (map[i][col][0] == min[0] && map[i][col][1] < min[1])) {
				min = map[i][col];
			}
		}
		ans = 0;
		for (i = 0; i < col; i++) {
			for (j = 0; j < size; j++) {
				if (map[j][i][0] < min[0] || (map[j][i][0] == min[0] && map[j][i][1] < min[1])) {
					break;
				}
			}
			ans += j;
		}
		for (++i; i < m; i++) {
			for (j = 0; j < size; j++) {
				if (map[j][i][0] < min[0] || (map[j][i][0] == min[0] && map[j][i][1] <= min[1])) {
					break;
				}
			}
			ans += j;
		}
		ans += row;
		System.out.print(ans);
	}
}
