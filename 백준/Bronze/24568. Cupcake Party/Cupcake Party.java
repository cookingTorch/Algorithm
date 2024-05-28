import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		int r;
		int s;
		BufferedReader br;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		r = Integer.parseInt(br.readLine());
		s = Integer.parseInt(br.readLine());
		System.out.print(8 * r + 3 * s - 28);
	}
}
