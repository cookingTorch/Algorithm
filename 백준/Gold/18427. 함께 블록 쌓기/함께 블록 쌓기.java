import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static final int P = 10007;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, h, i, j;
		int[] dp;
		ArrayList<Integer> blocks;
		ArrayList<ArrayList<Integer>> students;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		students = new ArrayList<>(n);
		for (i = 0; i < n; i++) {
			blocks = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				blocks.add(Integer.parseInt(st.nextToken()));
			}
			students.add(blocks);
		}
		dp = new int[h + 1];
		dp[0] = 1;
		for (ArrayList<Integer> student : students) {
			for (j = h; j >= 0; j--) {
				for (int block : student) {
					if (j >= block) {
						dp[j] = (dp[j] + dp[j - block]) % P;
					}
				}
			}
		}
		System.out.print(dp[h]);
	}
}
