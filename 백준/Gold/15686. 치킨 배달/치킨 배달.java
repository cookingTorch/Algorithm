import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static final int MAX = 10000;
	
	private static int m, num, size, min;
	private static int[] ans;
	private static int[][] distance;
	
	private static void dfs(int start, int dist, int depth) {
		int temp, i, j;
		int[] save;
		
		if (depth == m) {
			min = Math.min(min, dist);
		}
		for (i = start; i < size; i++) {
			temp = 0;
			save = Arrays.copyOf(ans, num);
			for (j = 0; j < num; j++) {
				if (distance[j][i] < ans[j]) {
					temp += ans[j] - distance[j][i];
					ans[j] = distance[j][i];
				}
			}
			dfs(i + 1, dist - temp, depth + 1);
			ans = save;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, i, j;
		int[] home, chicken;
		ArrayList<int[]> homes, chickens;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		homes = new ArrayList<>();
		chickens = new ArrayList<>();
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < n; j++) {
				switch (Integer.parseInt(st.nextToken())) {
				case 1:
					homes.add(new int[] {i, j});
					break;
				case 2:
					chickens.add(new int[] {i, j});
				}
			}
		}
		num = homes.size();
		size = chickens.size();
		distance = new int[num][size];
		for (i = 0; i < num; i++) {
			home = homes.get(i);
			for (j = 0; j < size; j++) {
				chicken = chickens.get(j);
				distance[i][j] = Math.abs(home[0] - chicken[0]) + Math.abs(home[1] - chicken[1]);
			}
		}
		min = MAX * num;
		ans = new int[num];
		Arrays.fill(ans, MAX);
		dfs(0, min, 0);
		System.out.print(min);
	}
}
