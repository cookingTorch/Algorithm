import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static final int MAX_M = 13;
	private static final int EMPTY = '0';
	private static final int HOME = '1';
	private static final int CHICKEN = '2';
	private static final int INF = Integer.MAX_VALUE;

	private static final class Dist implements Comparable<Dist> {
		int idx;
		int dist;

		Dist(int idx, int dist) {
			this.idx = idx;
			this.dist = dist;
		}

		@Override
		public int compareTo(Dist o) {
			return Integer.compare(dist, o.dist);
		}
	}

	private static int n;
	private static int m;
	private static int sum;
	private static int min;
	private static int homeIdx;
	private static int chickenIdx;
	private static boolean[] on;
	private static Dist[][] dists;

	private static int getDist(int pos1, int pos2) {
		return Math.abs(pos1 / n - pos2 / n) + Math.abs(pos1 % n - pos2 % n);
	}

	private static void dfs(int start, int depth) {
		int i;

		if (depth == m) {
			sum = 0;
			for (i = 0; i < homeIdx; i++) {
				for (Dist dist : dists[i]) {
					if (on[dist.idx]) {
						sum += dist.dist;
						break;
					}
				}
			}
			if (sum < min) {
				min = sum;
			}
			return;
		}
		depth++;
		for (i = start; i < chickenIdx; i++) {
			on[i] = true;
			dfs(i + 1, depth);
			on[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		int i;
		int j;
		int size;
		char[] map;
		int home;
		int[] homes;
		int[] chickens;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ", false);
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		size = n * n;
		homes = new int[n << 1];
		chickens = new int[MAX_M];
		switch (br.read()) {
			case EMPTY:
				break;
			case HOME:
				homes[homeIdx++] = 0;
				break;
			case CHICKEN:
				chickens[chickenIdx++] = 0;
				break;
		}
		for (i = 1; i < size; i++) {
			br.read();
			switch (br.read()) {
				case EMPTY:
					break;
				case HOME:
					homes[homeIdx++] = i;
					break;
				case CHICKEN:
					chickens[chickenIdx++] = i;
					break;
			}
		}
		dists = new Dist[homeIdx][chickenIdx];
		for (i = 0; i < homeIdx; i++) {
			home = homes[i];
			for (j = 0; j < chickenIdx; j++) {
				dists[i][j] = new Dist(j, getDist(home, chickens[j]));
			}
			Arrays.sort(dists[i]);
		}
		min = INF;
		on = new boolean[chickenIdx];
		dfs(0, 0);
		System.out.print(min);
	}
}
