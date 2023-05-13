import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str;
		
		int i;
		int[] weights = new int[3];
		
		for (i = 0; i < 3; i++) {
			str = br.readLine();
			weights[i] = Integer.parseInt(str);
		}
		Arrays.sort(weights);
		
		sb.append(weights[1]);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}

}