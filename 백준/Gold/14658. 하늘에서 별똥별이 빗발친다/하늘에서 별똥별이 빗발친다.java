import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	private static int l, k;
	private static int[][] stars;
	
	private static boolean trampoline(int[] star1, int[] star2) {
		return Math.abs(star1[0] - star2[0]) <= l && Math.abs(star1[1] - star2[1]) <= l;
	}
	
	private static int count(int[] start) {
		int cnt, i;
		
		cnt = 0;
		for (i = 0; i < k; i++) {
			if (stars[i][0] >= start[0] && stars[i][1] >= start[1] && trampoline(start, stars[i])) {
				cnt++;
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int x, y, max, i, j;
		LinkedList<int[]> starts;
		
		st = new StringTokenizer(br.readLine());
		Integer.parseInt(st.nextToken());
		Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		stars = new int[k][2];
		for (i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			stars[i][0] = Integer.parseInt(st.nextToken());
			stars[i][1] = Integer.parseInt(st.nextToken());
		}
		starts = new LinkedList<>();
		for (i = 0; i < k; i++) {
			for (j = i + 1; j < k; j++) {
				if (trampoline(stars[i], stars[j])) {
					x = Math.min(stars[i][0], stars[j][0]);
					y = Math.min(stars[i][1], stars[j][1]);
					if (!((x == stars[i][0] && y == stars[i][0]) || (x == stars[j][1] && y == stars[j][1]))) {
						starts.add(new int[] {x, y});
					}
				}
			}
		}
		max = 0;
		for (i = 0; i < k; i++) {
			max = Math.max(max, count(stars[i]));
		}
		for (int[] start : starts) {
			max = Math.max(max, count(start));
		}
		System.out.print(k - max);
	}
}
