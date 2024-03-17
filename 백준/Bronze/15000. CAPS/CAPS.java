import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int diff = -'a' + 'A';
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int i;
		char[] str;
		
		str = br.readLine().toCharArray();
		for (i = 0; i < str.length; i++) {
			sb.append((char) (str[i] + diff));
		}
		System.out.print(sb);
	}
}
