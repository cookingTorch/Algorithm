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

		int i, sum = 0; 

		for (i = 0; i < 5; i++) {
			str = br.readLine();
			sum += Integer.parseInt(str);
		} 

		sb.append(sum);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}

}