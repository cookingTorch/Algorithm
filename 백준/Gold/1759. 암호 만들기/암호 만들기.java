import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int l, c;
	private static char[] arr, ans;
	private static boolean[] vowel;
	private static StringBuilder sb;
	
	private static void combi(int start, int cnt, int depth) {
		int temp, i;
		
		if (depth == l) {
			if (cnt > 0 && l - cnt > 1) {
				sb.append(ans).append('\n');
			}
			return;
		}
		for (i = start; i < c; i++) {
			ans[depth] = arr[i];
			temp = cnt;
			if (vowel[i]) {
				temp++;
			}
			combi(i + 1, temp, depth + 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int i;
		
		st = new StringTokenizer(br.readLine());
		l = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		arr = new char[c];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < c; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(arr);
		vowel = new boolean[c];
		for (i = 0; i < c; i++) {
			switch (arr[i]) {
			case 'a': case 'e': case 'i': case 'o': case 'u':
				vowel[i] = true;
				break;
			default:
			}
		}
		sb = new StringBuilder();
		ans = new char[l];
		combi(0, 0, 0);
		System.out.print(sb);
	}
}
