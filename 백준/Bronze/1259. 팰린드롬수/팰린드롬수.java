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
		
		int i, flag;
		
		str = br.readLine();
		while (str.equals("0") == false) {
			flag = 1;
			for (i = 0; i < str.length() / 2; i++) {
				if (Character.toString(str.charAt(i)).equals(Character.toString(str.charAt(str.length() - 1 - i))) == false) {
					flag = 0;
					break;
				}
			}
			if (flag == 1) {
				bw.write("yes");
			}
			else {
				bw.write("no");
			}
			bw.newLine();
			str = br.readLine();
		}

		bw.flush();
		bw.close();
	}

}