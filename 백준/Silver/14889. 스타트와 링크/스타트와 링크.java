import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;

	private static int n;
	private static int min;
	private static int half;
	private static int[] team1;
	private static int[] team2;
	private static int[][] map;
	private static boolean[] isTeam1;

	private static int getScore(int[] team) {
		int i;
		int j;
		int score;

		score = 0;
		for (i = 0; i < half; i++) {
			for (j = 0; j < half; j++) {
				score += map[team[i]][team[j]];
			}
		}
		return score;
	}

	private static void combi(int start, int depth) {
		int i;
		int idx;
		int score;

		if (depth == half) {
			idx = 0;
			for (i = 0; i < n; i++) {
				if (!isTeam1[i]) {
					team2[idx++] = i;
				}
			}
			score = Math.abs(getScore(team1) - getScore(team2));
			if (score < min) {
				min = score;
			}
			return;
		}
		for (i = start; i < n; i++) {
			team1[depth] = i;
			isTeam1[i] = true;
			combi(i + 1, depth + 1);
			isTeam1[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		int i;
		int j;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ", false);
		n = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ", false);
			for (j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		min = INF;
		half = n >> 1;
		team1 = new int[half];
		team2 = new int[half];
		isTeam1 = new boolean[n];
		isTeam1[0] = true;
		combi(1, 1);
		System.out.print(min);
	}
}
