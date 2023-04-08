import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		
		int i, flag;
		Stack<String> temp = new Stack<>();
		
		do {
			str = br.readLine();
			if (str.equals(".")) {
				break;
			}
			flag = 1;
			for (i = 0; i < str.length(); i++) {
				if (Character.toString(str.charAt(i)).equals("(")) {
					temp.add("(");
				}
				else if (Character.toString(str.charAt(i)).equals("[")) {
					temp.add("[");
				}
				else if (Character.toString(str.charAt(i)).equals(")")) {
					if (temp.size() == 0 || (temp.size() > 0 && temp.pop().equals("(") == false)) {
						flag = 0;
						break;
					}
				}
				else if (Character.toString(str.charAt(i)).equals("]")) {
					if (temp.size() == 0 || (temp.size() > 0 && temp.pop().equals("[") == false)) {
						flag = 0;
						break;
					}
				}
			}
			if (temp.size() == 0 && flag == 1) {
				bw.write("yes");
				bw.newLine();
			}
			else {
				bw.write("no");
				bw.newLine();
			}
			temp.clear();
			
		} while (true);

		bw.flush();
		bw.close();
		
	}

}