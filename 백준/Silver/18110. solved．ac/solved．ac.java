import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int n, cut, i;
		int[] difficulty;
		double ans = 0;
		
		n = Integer.parseInt(br.readLine());
		difficulty = new int[n];
		cut = (int) Math.round(((double) n) * 0.15);
		for (i = 0; i < n; i++) {
			difficulty[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(difficulty);
		for (i = cut; i < n - cut; i++) {
			ans += difficulty[i];
		}
		ans /= n - (2 * cut);
		sb.append(Math.round(ans));
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}