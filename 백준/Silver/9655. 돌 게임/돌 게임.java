import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final char ONE = 1;
	private static final char[] SK = {'S', 'K'};
	private static final char[] CY = {'C', 'Y'};
	
	public static void main(String[] args) throws IOException {
		String str;
		BufferedReader br;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		if ((str.charAt(str.length() - 1) & ONE) == ONE) {
			System.out.print(SK);
		} else {
			System.out.print(CY);
		}
	}
}
