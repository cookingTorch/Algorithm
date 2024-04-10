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
		int[][] line;
		int[][][] map;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		size = (n - 1) / m + 1;
		map = new int[m][size][];
		cnt = 0;
		for (i = 0; i < size; i++) {
			for (j = 0; j < m; j++) {
				if (cnt++ < n) {
					st = new StringTokenizer(br.readLine());
					map[j][i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
				} else {
					map[j][i] = MIN;
				}
			}
		}
		row = k % m;
		col = k / m;
		line = map[row];
		min = line[0];
		for (i = 1; i <= col; i++) {
			if (line[i][0] < min[0] || (line[i][0] == min[0] && line[i][1] < min[1])) {
				min = line[i];
			}
		}
		ans = 0;
		for (i = 0; i < row; i++) {
			line = map[i];
			for (j = 0; j < size; j++) {
				if (line[j][0] < min[0] || (line[j][0] == min[0] && line[j][1] < min[1])) {
					break;
				}
			}
			ans += j;
		}
		for (++i; i < m; i++) {
			line = map[i];
			for (j = 0; j < size; j++) {
				if (line[j][0] < min[0] || (line[j][0] == min[0] && line[j][1] <= min[1])) {
					break;
				}
			}
			ans += j;
		}
		System.out.print(ans + col);
	}
}
