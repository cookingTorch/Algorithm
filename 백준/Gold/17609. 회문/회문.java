import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final char[] PALINDROME = {'0', '\n'};
	private static final char[] PSEUDO_PALINDROME = {'1', '\n'};
	private static final char[] NON_PALINDROME = {'2', '\n'};

	private static BufferedReader br;

	private static boolean isPseudo(int left, int right, char[] str) {
		for (; left < right; left++, right--) {
			if (str[left] != str[right]) {
				return false;
			}
		}
		return true;
	}

	private static char[] solution() throws IOException {
		int len;
		int left;
		int right;
		char[] str;

		len = (str = br.readLine().toCharArray()).length;
		for (left = 0, right = len - 1; left < right; left++, right--) {
			if (str[left] != str[right]) {
				if (isPseudo(left + 1, right, str) || isPseudo(left, right - 1, str)) {
					return PSEUDO_PALINDROME;
				}
				return NON_PALINDROME;
			}
		}
		return PALINDROME;
	}

	public static void main(String[] args) throws IOException {
		int t;
		StringBuilder sb;

		br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		while (t-- > 0) {
			sb.append(solution());
		}
		System.out.print(sb);
	}
}
