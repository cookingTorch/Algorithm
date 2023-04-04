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
		
		int n, i;
		
		str = br.readLine();
		n = Integer.parseInt(str);
		
		for (i = n; i > 0; i--) {
			bw.write(Integer.toString(i));
			bw.newLine();
		}
		
		bw.flush();
		bw.close();

	}

}