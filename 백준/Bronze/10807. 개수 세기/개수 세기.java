import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n, v, cnt, i;

		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		v = Integer.parseInt(br.readLine());
		cnt = 0;
		for (i = 0; i < n; i++) {
			if (Integer.parseInt(st.nextToken()) == v) {
				cnt++;
			}
		}
		System.out.print(cnt);

		br.close();
	}
}