import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		int x;
		BufferedReader br;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		x = Integer.parseInt(br.readLine());
		if (x % 7 == 2) {
			System.out.print("1");
			return;
		}
		System.out.print("0");
	}
}
