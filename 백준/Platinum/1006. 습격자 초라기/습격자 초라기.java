import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	private static int w;
	private static int[][] enemies, min;
	
	// DP
	private static int[] findMin(int idx) {
		
		// 재귀
		if (idx > 2 && min[idx - 1][0] == 0) {
			min[idx - 1] = Arrays.copyOf(findMin(idx - 1), 3);
		}
		if (idx > 3 && min[idx - 2][0] == 0) {
			min[idx - 2] = Arrays.copyOf(findMin(idx - 2), 3);
		}
		
		// [i, i-1]
		min[idx][1] = min[idx - 1][0] + 1;
		if (enemies[idx - 1][0] + enemies[idx][0] <= w) {
			min[idx][1] = Math.min(min[idx][1], min[idx - 1][2] + 1);
		}
		
		// [i-1, i]
		min[idx][2] = min[idx - 1][0] + 1;
		if (enemies[idx - 1][1] + enemies[idx][1] <= w) {
			min[idx][2] = Math.min(min[idx][2], min[idx - 1][1] + 1);
		}
		
		// [i, i]
		min[idx][0] = Math.min(min[idx][1] + 1, min[idx][2] + 1);
		if (enemies[idx][0] + enemies[idx][1] <= w) {
			min[idx][0] = Math.min(min[idx][0], min[idx - 1][0] + 1);
		}
		if (enemies[idx - 1][0] + enemies[idx][0] <= w && enemies[idx - 1][1] + enemies[idx][1] <= w) {
			min[idx][0] = Math.min(min[idx][0], min[idx - 2][0] + 2);
		}
		
		return min[idx];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str;
		StringTokenizer st;
		
		int t, testCase, i, j, n, ans;
		
		// 테스트 케이스
		str = br.readLine();
		t = Integer.parseInt(str);
		for (testCase = 0; testCase < t; testCase++) {
			
			// 원타곤 입력
			str = br.readLine();
			st = new StringTokenizer(str);
			n = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			enemies = new int[n + 1][2];
			for (i = 0; i < 2; i++) {
				str = br.readLine();
				st = new StringTokenizer(str);
				for (j = 1; j <= n; j++) {
					enemies[j][i] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 최소값 탐색
			min = new int[n + 1][3];
			Arrays.fill(min[1], 1);
			if (enemies[1][0] + enemies[1][1] > w) {
				min[1][0]++;
			}
			if (n == 1) {
				ans = min[1][0];
			}
			else {
				ans = findMin(n)[0];
				
				// [N, 1]
				if (enemies[n][0] + enemies[1][0] <= w) {
					min = new int[n + 1][3];
					min[1][1] = 0;
					min[1][0] = min[1][1] + 1;
					min[2][1] = min[1][0] + 1;
					min[2][2] = min[1][0] + 1;
					if (enemies[1][1] + enemies[2][1] <= w) {
						min[2][2] = Math.min(min[2][2], min[1][1] + 1);
					}
					min[2][0] = Math.min(min[2][1] + 1, min[2][2] + 1);
					if (enemies[2][0] + enemies[2][1] <= w) {
						min[2][0] = Math.min(min[2][0], min[1][0] + 1);
					}
					if (n == 2) {
						ans = Math.min(ans, min[2][2] + 1);
					}
					else {
						ans = Math.min(ans, findMin(n)[2] + 1);
					}
				}
				
				// [2N, N+1]
				if (enemies[n][1] + enemies[1][1] <= w) {
					min = new int[n + 1][3];
					min[1][2] = 0;
					min[1][0] = min[1][2] + 1;
					min[2][1] = min[1][0] + 1;
					if (enemies[1][0] + enemies[2][0] <= w) {
						min[2][1] = Math.min(min[2][1], min[1][2] + 1);
					}
					min[2][2] = min[1][0] + 1;
					min[2][0] = Math.min(min[2][1] + 1, min[2][2] + 1);
					if (enemies[2][0] + enemies[2][1] <= w) {
						min[2][0] = Math.min(min[2][0], min[1][0] + 1);
					}
					if (n == 2) {
						ans = Math.min(ans, min[2][1] + 1);
					}
					else {
						ans = Math.min(ans, findMin(n)[1] + 1);
					}
				}
				
				// [N, 1], [2N, N+1]
				if (enemies[n][0] + enemies[1][0] <= w && enemies[n][1] + enemies[1][1] <= w) {
					min = new int[n + 1][3];
					min[1][0] = 0;
					min[2][1] = min[1][0] + 1;
					min[2][2] = min[1][0] + 1;
					min[2][0] = Math.min(min[2][1] + 1, min[2][2] + 1);
					if (enemies[2][0] + enemies[2][1] <= w) {
						min[2][0] = Math.min(min[2][0], min[1][0] + 1);
					}
					if (n <= 3) {
						ans = Math.min(ans, min[n - 1][0] + 2);
					}
					else {
						ans = Math.min(ans, findMin(n - 1)[0] + 2);
					}
				}
			}
			
			// 출력
			sb.append(ans).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}