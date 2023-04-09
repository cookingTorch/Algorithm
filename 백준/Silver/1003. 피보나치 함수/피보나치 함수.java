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
		
		int t, i;
		int[] zero = new int[42];
		
		zero[0] = 1;
		zero[1] = 0;
		
		for (i = 2; i <= 41; i++) {
			zero[i] = zero[i - 1] + zero[i - 2]; 
		}
		
		str = br.readLine();
		t = Integer.parseInt(str);
		
		for (i = 0; i < t; i++) {
			str = br.readLine();
			bw.write(Integer.toString(zero[Integer.parseInt(str)]) + " " + Integer.toString(zero[Integer.parseInt(str) + 1]));
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		
	}

}