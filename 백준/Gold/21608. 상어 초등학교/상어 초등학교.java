import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int n;
	private static int[][] seat, empty;
	private static int[][][] satisfaction;
	private static ArrayList<ArrayList<Integer>> adj;
	
	private static void arrange(int num) {
		int max, temp, i, j, x, y;
		
		max = -1;
		temp = -1;
		x = 0;
		y = 0;
		for (i = 1; i <= n; i++) {
			for (j = 1; j <= n; j++) {
				if (seat[i][j] != 0) {
					continue;
				}
				if (satisfaction[num][i][j] > max) {
					x = i;
					y = j;
					max = satisfaction[num][i][j];
					temp = empty[i][j];
				} else if (satisfaction[num][i][j] == max && empty[i][j] > temp) {
					x = i;
					y = j;
					temp = empty[i][j];
				}
			}
		}
		seat[x][y] = num;
		for (int other : adj.get(num)) {
			satisfaction[other][x - 1][y]++;
			satisfaction[other][x + 1][y]++;
			satisfaction[other][x][y - 1]++;
			satisfaction[other][x][y + 1]++;
		}
		empty[x - 1][y]--;
		empty[x + 1][y]--;
		empty[x][y - 1]--;
		empty[x][y + 1]--;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int size, ans, i, j;
		int[] order;
		
		n = Integer.parseInt(br.readLine());
		size = n * n;
		adj = new ArrayList<>();
		for (i = 0; i <= size; i++) {
			adj.add(new ArrayList<>());
		}
		order = new int[size];
		for (i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			order[i] = Integer.parseInt(st.nextToken());
			for (j = 0; j < 4; j++) {
				adj.get(Integer.parseInt(st.nextToken())).add(order[i]);
			}
		}
		satisfaction = new int[size + 1][n + 2][n + 2];
		seat = new int[n + 2][n + 2];
		empty = new int[n + 2][n + 2];
		Arrays.fill(empty[1], 3);
		empty[1][1] = 2;
		empty[1][n] = 2;
		for (i = 2; i < n; i++) {
			Arrays.fill(empty[i], 4);
			empty[i][1] = 3;
			empty[i][n] = 3;
		}
		Arrays.fill(empty[n], 3);
		empty[n][1] = 2;
		empty[n][n] = 2;
		for (int num : order) {
			arrange(num);
		}
		ans = 0;
		for (i = 1; i <= n; i++) {
			for (j = 1; j <= n; j++) {
				ans += (int) Math.pow(10, satisfaction[seat[i][j]][i][j] - 1);
			}
		}
		System.out.print(ans);
	}
}
