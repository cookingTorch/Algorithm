import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static int getLps(String pat) {
		int i;
		int j;
		int len;
		int[] lps;
		
		i = 1;
		j = 0;
		len = pat.length();
		lps = new int[len];
		while (i < len) {
			if (pat.charAt(i) == pat.charAt(j)) {
				j++;
				lps[i] = j;
				i++;
			} else if (j == 0) {
				i++;
			} else {
				j = lps[j - 1];
			}
		}
		return lps[len - 1];
	}

	public static void main(String[] args) throws IOException {
		int l;
		BufferedReader br;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		l = Integer.parseInt(br.readLine());
		System.out.print(l - getLps(br.readLine()));
	}
}
