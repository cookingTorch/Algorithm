import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final String SK = "SK";
	private static final String CY = "CY";

	public static void main(String[] args) throws IOException {
		int n;
		BufferedReader br;

		br = new BufferedReader(new InputStreamReader(System.in));
		if ((Integer.parseInt(br.readLine()) & 1) == 0) {
			System.out.print(CY);
		} else {
			System.out.print(SK);
		}
	}
}
