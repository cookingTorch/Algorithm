import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final long MOD = 7L;
	private static final char[] SK = new char[] {'S', 'K'};
	private static final char[] CY = new char[] {'C', 'Y'};

	public static void main(String[] args) throws IOException {
		int n;
		BufferedReader br;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = (int) (Long.parseLong(br.readLine()) % MOD);
		if (n == 0 || n == 2) {
			System.out.print(CY);
		} else {
			System.out.print(SK);
		}
	}
}
