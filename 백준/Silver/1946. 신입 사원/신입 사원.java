import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		int n, ans, min, i;
		int[][] rank;
		
		n = Integer.parseInt(br.readLine());
		rank = new int[n][];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			rank[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		}
		Arrays.sort(rank, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		ans = n;
		min = rank[0][1];
		for (i = 1; i < n; i++) {
			if (rank[i][1] > min) {
				ans--;
			}
			min = Math.min(min, rank[i][1]);
		}
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		
		t = Integer.parseInt(br.readLine());
		for (testCase = 0; testCase < t; testCase++) {
			sb.append(solution(br, st)).append('\n');
		}
		System.out.print(sb);
	}
}
