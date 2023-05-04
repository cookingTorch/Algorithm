import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		String str1, str2;
		
		str1 = br.readLine();
		str2 = br.readLine();
		
		if (str2.length() <= str1.length()) {
			sb.append("go");
		}
		else {
			sb.append("no");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}

}