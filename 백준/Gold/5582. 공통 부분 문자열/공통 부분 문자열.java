import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		int i;
		int j;
		int max;
		int len1;
		int len2;
		int[][] lcs;
		String str1;
		String str2;
		BufferedReader br;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		str1 = br.readLine();
		str2 = br.readLine();
		len1 = str1.length();
		len2 = str2.length();
		max = 0;
		lcs = new int[len1 + 1][len2 + 1];
		for (i = 1; i <= len1; i++) {
			for (j = 1; j <= len2; j++) {
				if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					lcs[i][j] = lcs[i - 1][j - 1] + 1;
					max = Math.max(max, lcs[i][j]);
				}
			}
		}
		System.out.print(max);
	}
}
