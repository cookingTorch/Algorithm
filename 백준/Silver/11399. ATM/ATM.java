import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, i, ans = 0;
		int[] p;
		
		n = Integer.parseInt(br.readLine());
		p = new int[n];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(p);
		for (i = 0; i < n; i++) {
			ans += p[i] * (n - i);
		}
		sb.append(ans);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
