import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	// LPS 생성
	private static int[] findLps(int[] pat) {
		int len = 0, i = 1;
		int[] lps = new int[pat.length];
		while (i < pat.length) {
			if (pat[i] == pat[len]) {
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
	private static boolean kmp(int[] txt, int[] pat) {
		int i = 0, j = 0;
		int[] lps;
		lps = findLps(pat);
		while (i < txt.length) {
			if (txt[i] == pat[j]) {
				i++;
				j++;
				if (j == pat.length) {
					return true;
				}
			}
			else if (j == 0) {
				i++;
			}
			else {
				j = lps[j - 1];
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str;
		StringTokenizer st;
		
		int n, i;
		int[] time1, time2, diff1, diff2;
		
		str = br.readLine();
		n = Integer.parseInt(str);
		
		// 시계 입력
		time1 = new int[n];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			time1[i] = Integer.parseInt(st.nextToken());
		}
		time2 = new int[n];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			time2[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(time1);
		Arrays.sort(time2);
		
		// 시간 차이 배열
		diff1 = new int[2 * n - 1];
		diff2 = new int[n];
		for (i = 0; i < n - 1; i++) {
			diff1[i] = time1[i + 1] - time1[i];
			diff1[i + n] = diff1[i];
			diff2[i] = time2[i + 1] - time2[i];
		}
		diff1[n - 1] = time1[0] + 360000 - time1[n - 1];
		diff2[n - 1] = time2[0] + 360000 - time2[n - 1];
		
		// 포함 판단
		if (kmp(diff1, diff2)) {
			sb.append("possible");
		}
		else {
			sb.append("impossible");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}