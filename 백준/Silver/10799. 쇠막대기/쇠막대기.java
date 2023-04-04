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
		
		int ans = 0, stack = 0, i;
		
		str = br.readLine();
		
		for (i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(' && i < str.length() - 1 && str.charAt(i + 1) == ')') {
				ans += stack;
				i++;
			}
			else if (str.charAt(i) == '('){
				stack++;
				ans++;
			}
			else {
				stack--;
			}
		}

		bw.write(Integer.toString(ans));
		
		bw.flush();
		bw.close();
	}

}