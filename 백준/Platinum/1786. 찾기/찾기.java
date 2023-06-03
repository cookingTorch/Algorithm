import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {
	
	private static ArrayList<Integer> ans = new ArrayList<>();
	
	// LPS 생성
	private static int[] findLps(String pat) {
		int len = 0, i = 1;
		int[] lps = new int[pat.length()];
		while (i < pat.length()) {
			if (pat.charAt(i) == pat.charAt(len)) {
				len++;
				lps[i] = len;
				i++;
			}
			else if (len == 0) {
				i++;
			}
			else {
				len = lps[len - 1];
			}
		}
		return lps;
	}
	
	// KMP
	private static void kmp(String txt, String pat) {
		int i = 0, j = 0;
		int[] lps;
		lps = findLps(pat);
		while (i < txt.length()) {
			if (txt.charAt(i) == pat.charAt(j)) {
				i++;
				j++;
				if (j == pat.length()) {
					ans.add(i - j + 1);
					j = lps[j - 1];
				}
			}
			else if (j == 0) {
				i++;
			}
			else {
				j = lps[j - 1];
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int i;
		String t, p;
		
		t = br.readLine();
		p = br.readLine();
		
		kmp(t, p);
		
		sb.append(ans.size()).append("\n");
		for (i = 0; i < ans.size(); i++) {
			sb.append(ans.get(i)).append(" ");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}