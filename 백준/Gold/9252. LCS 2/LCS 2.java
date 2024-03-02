import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int len1, len2, size, idx, i, j;
		int[][] cnt;
		char[] str1, str2, result;
		
		str1 = br.readLine().toCharArray();
		str2 = br.readLine().toCharArray();
		len1 = str1.length;
		len2 = str2.length;
		cnt = new int[len1 + 1][len2 + 1];
		for (i = 0, j = 0; i < len1; i++) {
			for (j = 0; j < len2; j++) {
				if (str1[i] == str2[j]) {
					cnt[i + 1][j + 1] = cnt[i][j] + 1;
				} else {
					cnt[i + 1][j + 1] = Math.max(cnt[i + 1][j], cnt[i][j + 1]);
				}
			}
		}
		size = cnt[len1][len2];
		result = new char[size];
		for (i = len1, j = len2, idx = size - 1; idx >= 0;) {
			if (cnt[i - 1][j] == cnt[i][j]) {
				i--;
			} else if (cnt[i][j - 1] == cnt[i][j]) {
				j--;
			} else {
				result[idx--] = str1[i - 1];
				i--;
				j--;
			}
		}
		sb.append(size).append('\n').append(result);
		System.out.print(sb);
	}
}
