import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		StringTokenizer st;
		
		int n, m, k, n2, m2, i, j, p, q, temp, flag = 1, cnt = 0;
		int[][] originalArray, array1, array2, stack1, stack2;
		
		str = br.readLine();
		st = new StringTokenizer(str," ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		n2 = n / 2;
		m2 = m / 2;
		
		originalArray = new int[n][m];
		array1 = new int[n2 + 1][m2 + 1];
		array2 = new int[n2 + 1][m2 + 1];
		stack1 = new int[n2 + 1][m2 + 1];
		stack2 = new int[n2 + 1][m2 + 1];
		
		for (i = 0; i < n; i++) {
			str = br.readLine();
			for (j = 0; j < m; j++) {
				originalArray[i][j] = str.charAt(j) - '0';
			}
		}

		for (i = 0; i < n2; i++) {
			for (j = 0; j < m2; j++) {
				if (originalArray[i][j] == originalArray[i][m - 1 - j] && originalArray[i][j] == originalArray[n - 1 - i][j] && originalArray[i][j] == originalArray[n - 1 - i][m - 1 - j]) {
					continue;
				}
				else if (originalArray[i][j] == originalArray[i][m - 1 - j] && originalArray[i][j] != originalArray[n - 1 - i][j] && originalArray[i][j] != originalArray[n - 1 - i][m - 1 - j]) {
					array1[i + 1][j + 1] ^= 1;
				}
				else if (originalArray[i][j] != originalArray[i][m - 1 - j] && originalArray[i][j] == originalArray[n - 1 - i][j] && originalArray[i][j] != originalArray[n - 1 - i][m - 1 - j]) {
					array2[i + 1][j + 1] ^= 1;
				}
				else if (originalArray[i][j] != originalArray[i][m - 1 - j] && originalArray[i][j] != originalArray[n - 1 - i][j] && originalArray[i][j] == originalArray[n - 1 - i][m - 1 - j]) {
					array1[i + 1][j + 1] ^= 1;
					array2[i + 1][j + 1] ^= 1;
				}
				else {
					flag--;
					break;
				}
			}
			if (flag <= 0) {
				break;
			}
		}
		
		if (flag > 0) {
			
			for (i = 1; i <= n2; i++) {
				for (j = 1; j <= m2; j++) {
					
					temp = 0;
					stack1[i][j] = 0 + stack1[i][j - 1] + stack1[i - 1][j] - stack1[i - 1][j - 1];
					stack2[i][j] = 0 + stack2[i][j - 1] + stack2[i - 1][j] - stack2[i - 1][j - 1];
					
					if (array1[i][j] == 1) {
						if ((stack1[i][j] - stack1[i][Math.max(0, j - k)] - stack1[Math.max(0, i - k)][j] + stack1[Math.max(0, i - k)][Math.max(0, j - k)]) % 2 == 0) {
							if (i <= n2 + 1 - k && j <= m2 + 1 - k) {
								stack1[i][j]++;
								temp++;
								cnt++;
							}
							else {
								flag--;
								break;
							}
						}
					}
					else {
						if ((stack1[i][j] - stack1[i][Math.max(0, j - k)] - stack1[Math.max(0, i - k)][j] + stack1[Math.max(0, i - k)][Math.max(0, j - k)]) % 2 == 1) {
							if (i <= n2 + 1 - k && j <= m2 + 1 - k) {
								stack1[i][j]++;
								temp++;
								cnt++;
							}
							else {
								flag--;
								break;
							}
						}
					}
					
					if (array2[i][j] == 1) {
						if ((stack2[i][j] - stack2[i][Math.max(0, j - k)] - stack2[Math.max(0, i - k)][j] + stack2[Math.max(0, i - k)][Math.max(0, j - k)]) % 2 == 0) {
							if (i <= n2 + 1 - k && j <= m2 + 1 - k) {
								stack2[i][j]++;
								if (temp == 0) {
									cnt++;
								}
							}
							else {
								flag--;
								break;
							}
						}
					}
					else {
						if ((stack2[i][j] - stack2[i][Math.max(0, j - k)] - stack2[Math.max(0, i - k)][j] + stack2[Math.max(0, i - k)][Math.max(0, j - k)]) % 2 == 1) {
							if (i <= n2 + 1 - k && j <= m2 + 1 - k) {
								stack2[i][j]++;
								if (temp == 0) {
									cnt++;
								}
							}
							else {
								flag--;
								break;
							}
						}
					}
					
				}
				if (flag <= 0) {
					break;
				}
			}
			
		}
		
		if (flag > 0) {
			bw.write(Integer.toString(cnt));
		}
		else {
			bw.write("-1");
		}
		
		bw.flush();
		bw.close();
		
	}

}