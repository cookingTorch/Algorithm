import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int TEAM = 6;
	private static final int COMBI = 15;
	
	private static int[][] game, result;
	
	private static boolean dfs(int depth) {
		int[] teamA, teamB;
		
		if (depth == COMBI) {
			return true;
		}
		teamA = result[game[depth][0]];
		teamB = result[game[depth][1]];
		if (--teamA[0] >= 0 & --teamB[2] >= 0) {
			if (dfs(depth + 1)) {
				return true;
			}
		}
		teamA[0]++;
		teamB[2]++;
		if (--teamA[1] >= 0 & --teamB[1] >= 0) {
			if (dfs(depth + 1)) {
				return true;
			}
		}
		teamA[1]++;
		teamB[1]++;
		if (--teamA[2] >= 0 & --teamB[0] >= 0) {
			if (dfs(depth + 1)) {
				return true;
			}
		}
		teamA[2]++;
		teamB[0]++;
		return false;
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
		return dfs(0) ? 1 : 0;
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
