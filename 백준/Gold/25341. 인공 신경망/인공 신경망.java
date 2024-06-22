import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final char LINE_BREAK = '\n';
	
	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int q;
		int c;
		int b;
		int i;
		int j;
		int[] p;
		int[] w;
		int[] multiplier;
		long val;
		String[] strs;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		strs = new String[m];
		for (i = 0; i < m; i++) {
			strs[i] = br.readLine();
		}
		w = new int[m];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < m; i++) {
			w[i] = Integer.parseInt(st.nextToken());
		}
		b = Integer.parseInt(st.nextToken());
		p = new int[n];
		multiplier = new int[n + 1];
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(strs[i]);
			c = Integer.parseInt(st.nextToken());
			for (j = 0; j < c; j++) {
				p[j] = Integer.parseInt(st.nextToken());
			}
			for (j = 0; j < c; j++) {
				multiplier[p[j]] += Integer.parseInt(st.nextToken()) * w[i];
			}
			b += Integer.parseInt(st.nextToken()) * w[i];
		}
		sb = new StringBuilder();
		for (i = 0; i < q; i++) {
			val = b;
			st = new StringTokenizer(br.readLine());
			for (j = 1; j <= n; j++) {
				val += Integer.parseInt(st.nextToken()) * multiplier[j];
			}
			sb.append(val).append(LINE_BREAK);
		}
		System.out.print(sb);
	}
}
