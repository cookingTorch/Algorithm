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
		
		Deque<Integer> queue = new LinkedList<>();
		
		int n, i, x;
		String command;
		
		str = br.readLine();
		n = Integer.parseInt(str);
		
		for (i = 0; i < n; i++) {
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			command = st.nextToken();
			
			if (command.equals("push")) {
				x = Integer.parseInt(st.nextToken());
				queue.addLast(x);
			}
			else if (command.equals("pop")) {
				if (queue.size() > 0) {
					x = queue.pollFirst();
				}
				else {
					x = -1;
				}
				bw.write(Integer.toString(x));
				bw.newLine();
			}
			else if (command.equals("size")) {
				x = queue.size();
				bw.write(Integer.toString(x));
				bw.newLine();
			}
			else if (command.equals("empty")) {
				if (queue.size() > 0) {
					x = 0;
				}
				else {
					x = 1;
				}
				bw.write(Integer.toString(x));
				bw.newLine();
			}
			else if (command.equals("front")) {
				if (queue.size() > 0) {
					x = queue.pollFirst();
					queue.addFirst(x);
				}
				else {
					x = -1;
				}
				bw.write(Integer.toString(x));
				bw.newLine();
			}
			else if (command.equals("back")) {
				if (queue.size() > 0) {
					x = queue.pollLast();
					queue.addLast(x);
				}
				else {
					x = -1;
				}
				bw.write(Integer.toString(x));
				bw.newLine();
			}
		}

		bw.flush();
		bw.close();
	}

}