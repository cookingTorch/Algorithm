import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		
		int i;
		char[] charStr;
		
		str = br.readLine();
		charStr = new char[str.length()];
		
		for (i = 0; i < str.length(); i++) {
			charStr[i] = str.charAt(i);
		}
		
		for (i = 0; i < str.length(); i++) {
			if ((int) charStr[i] >= (int) 'a') {
				charStr[i] = (char) ((int) charStr[i] - ((int) 'a' - (int) 'A'));
			}
			else {
				charStr[i] = (char) ((int) charStr[i] + ((int) 'a' - (int) 'A'));
			}
		}
		
		for (i = 0; i < str.length(); i++) {
			bw.write(Character.toString(charStr[i]));
		}
		
		bw.flush();
		bw.close();

	}

}