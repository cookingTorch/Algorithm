import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int SIZE = 8;
	private static final char DIFF = 'a';

	public static void main(String[] args) throws IOException {
		int n;
		int row;
		int col;
		BufferedReader br;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		row = (n - 1) / SIZE + 1;
		col = (n - 1) % SIZE;
		System.out.print(new StringBuilder().append((char) (col + DIFF)).append(row));
	}
}
