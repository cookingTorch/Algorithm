import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int TEN = 10;
	private static final int ELEVEN = 11;

	public static void main(String[] args) throws IOException {
		BufferedReader br;

		br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(Integer.parseInt(br.readLine()) / ELEVEN * TEN);
	}
}
