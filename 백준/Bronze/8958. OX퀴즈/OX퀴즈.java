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
		
		int t, i, j, temp, score;
		String ox;
		
		str = br.readLine();
		t = Integer.parseInt(str);
		
		for (i = 0; i < t; i++) {
			str = br.readLine();
			temp = 0;
			score = 0;
			for (j = 0; j < str.length(); j++) {
				ox = Character.toString(str.charAt(j));
				if (ox.equals("O")) {
					temp++;
					score += temp;
					
				}
				else {
					temp = 0;
				}
			}
			bw.write(Integer.toString(score));
			bw.newLine();
		}
		
		bw.flush();
		bw.close();

	}

}