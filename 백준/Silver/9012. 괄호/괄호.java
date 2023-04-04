import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		
		int n, i, j, flag;
		Deque<Character> stack = new LinkedList<>();
		Character ch;
		
		str = br.readLine();
		n = Integer.parseInt(str);
		
		for (i = 0; i < n; i++) {
			stack = new LinkedList<>();
			str = br.readLine();
			flag = 1;
			for (j = 0; j < str.length(); j++) {
				ch = str.charAt(j);
				if (ch.equals('(')) {
					stack.push(ch);
				}
				else {
					if (stack.isEmpty()) {
						flag = 0;
					}
					else {
						stack.remove();
					}
				}
			}
			if (stack.isEmpty() == false) {
				flag = 0;
			}
			
			if (flag == 1) {
				bw.write("YES\n");
			}
			else {
				bw.write("NO\n");
			}
		}
		
		bw.flush();
		bw.close();
		
	}

}