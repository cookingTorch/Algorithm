import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		int x;
		int cnt;
		BufferedReader br;

		br = new BufferedReader(new InputStreamReader(System.in));
		x = Integer.parseInt(br.readLine());
		for (cnt = 0; x > 0; x >>= 1) {
			if ((x & 1) == 1) {
				cnt++;
			}
		}
		System.out.print(cnt);
	}
}
