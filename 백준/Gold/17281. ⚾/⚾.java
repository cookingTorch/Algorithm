import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static final int PLAYERS = 9;
	private static final int FOURTH = 3;
	private static final int OUT = 0;
	
	private static int n, player, ans;
	private static int[] order;
	private static int[][] result;
	private static boolean[] visited, base;
	
	private static int move() {
		int score;
		
		score = 0;
		if (base[2]) {
			score = 1;
		}
		base[2] = base[1];
		base[1] = base[0];
		base[0] = false;
		return score;
	}
	
	private static int hit(int res) {
		int score;
		
		score = move();
		base[0] = true;
		for (res--; res > 0; res--) {
			score += move();
		}
		return score;
	}
	
	private static int inning(int[] res) {
		int score, out;
		
		score = 0;
		for (out = 0; out < 3; player = ++player % PLAYERS) {
			if (res[order[player]] == OUT) {
				out++;
				continue;
			}
			score += hit(res[order[player]]);
		}
		return score;
	}
	
	private static int game() {
		int score, i;
		
		score = 0;
		player = 0;
		for (i = 0; i < n; i++) {
			Arrays.fill(base, false);
			score += inning(result[i]);
		}
		return score;
	}
	
	private static void permu(int depth) {
		int i;
		
		if (depth == PLAYERS) {
			ans = Math.max(ans, game());
			return;
		}
		if (depth == FOURTH) {
			permu(depth + 1);
			return;
		}
		for (i = 1; i < PLAYERS; i++) {
			if (visited[i]) {
				continue;
			}
			visited[i] = true;
			order[depth] = i;
			permu(depth + 1);
			visited[i] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int i, j;
		
		n = Integer.parseInt(br.readLine());
		result = new int[n][PLAYERS];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < PLAYERS; j++) {
				result[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		order = new int[PLAYERS];
		visited = new boolean[PLAYERS];
		base = new boolean[3];
		permu(0);
		System.out.print(ans);
	}
}
