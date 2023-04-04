import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		StringTokenizer st;
		
		int n, i, j;
		int[][] cost;
		
		str = br.readLine();
		n = Integer.parseInt(str);
		
		cost = new int[n][3];
		
		for (i = 0; i < n; i++) {
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			for (j = 0; j < 3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (i = 1; i < n; i++) {
			cost[i][0] += Math.min(cost[i - 1][1], cost[i - 1][2]);
			cost[i][1] += Math.min(cost[i - 1][2], cost[i - 1][0]);
			cost[i][2] += Math.min(cost[i - 1][0], cost[i - 1][1]);
		}
		
		System.out.println(Math.min(Math.min(cost[n - 1][0], cost[n - 1][1]), cost[n - 1][2]));
		
	}

}