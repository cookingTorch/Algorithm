import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		StringTokenizer st;
		
		int n, i, num;
		String command;
		Deque<Integer> stack = new LinkedList<>();
		
		str = br.readLine();
		n = Integer.parseInt(str);
		
		for (i = 0; i < n; i++) {
			
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			command = st.nextToken();
			
			if (command.equals("push")) {
				num = Integer.parseInt(st.nextToken());
				stack.add(num);
			}
			else if (command.equals("pop")) {
				if (stack.isEmpty()) {
					num = -1;
				}
				else {
					num = stack.pollLast();
				}
				bw.write(Integer.toString(num) + "\n");
			}
			else if (command.equals("size")) {
				num = stack.size();
				bw.write(Integer.toString(num) + "\n");
			}
			else if (command.equals("empty")) {
				if (stack.isEmpty()) {
					num = 1;
				}
				else {
					num = 0;
				}
				bw.write(Integer.toString(num) + "\n");
			}
			else if (command.equals("top")) {
				if (stack.isEmpty()) {
					num = -1;
				}
				else {
					num = stack.peekLast();
				}
				bw.write(Integer.toString(num) + "\n");
			}
			
		}
		
		bw.flush();
		bw.close();

	}

}