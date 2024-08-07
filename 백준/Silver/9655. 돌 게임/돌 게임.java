import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final String SK = "SK";
	private static final String CY = "CY";

	public static void main(String[] args) throws IOException {
		int n;

		n = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
		if (((n & 3) & 1) == 0) {
			System.out.print(CY);
		} else {
			System.out.print(SK);
		}
	}
}
