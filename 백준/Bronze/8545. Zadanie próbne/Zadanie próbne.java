import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int LEN = 3;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int i;
		char[] str;
		
		str = new char[LEN];
		for (i = LEN - 1; i >= 0; i--) {
			str[i] = (char) br.read();
		}
		System.out.print(str);
	}

}
