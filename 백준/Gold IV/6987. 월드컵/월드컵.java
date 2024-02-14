import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int TEAM = 6;
	private static final int COMBI = 15;
	
	private static int[][] game, result;
	
	private static int dfs(int depth) {
		int val;
		
		if (depth == COMBI) {
			return 1;
		}
		val = 0;
		if (--result[game[depth][0]][0] >= 0 & --result[game[depth][1]][2] >= 0) {
			val |= dfs(depth + 1);
		}
		result[game[depth][0]][0]++;
		result[game[depth][1]][2]++;
		if (--result[game[depth][0]][1] >= 0 & --result[game[depth][1]][1] >= 0) {
			val |= dfs(depth + 1);
		}
		result[game[depth][0]][1]++;
		result[game[depth][1]][1]++;
		if (--result[game[depth][0]][2] >= 0 & --result[game[depth][1]][0] >= 0) {
			val |= dfs(depth + 1);
		}
		result[game[depth][0]][2]++;
		result[game[depth][1]][0]++;
		return val;
	}
	
	private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		int sum, i;
		
		sum = 0;
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < TEAM; i++) {
			result[i][0] = Integer.parseInt(st.nextToken());
			result[i][1] = Integer.parseInt(st.nextToken());
			result[i][2] = Integer.parseInt(st.nextToken());
			sum += result[i][0] + result[i][1] + result[i][2];
		}
		if (sum != COMBI * 2) {
			return 0;
		}
		return dfs(0);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int testCase, idx, i, j;
		
		game = new int[COMBI][2];
		idx = 0;
		for (i = 0; i < TEAM; i++) {
			for (j = i + 1; j < TEAM; j++) {
				game[idx][0] = i;
				game[idx++][1] = j;
			}
		}
		result = new int[TEAM][3];
		for (testCase = 0; testCase < 4; testCase++) {
			sb.append(solution(br, st)).append(' ');
		}
		System.out.print(sb);
	}
}
