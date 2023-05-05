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
		String str;
		
		int a, b;
		
		str = br.readLine();
		a = Integer.parseInt(str);
		str = br.readLine();
		b = Integer.parseInt(str);
		
		sb.append(a + b);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}

}