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
		
		int n, i, size;
		String command, temp;
		Deque<Character> left = new LinkedList<>();
		Deque<Character> right = new LinkedList<>();
		
		str = br.readLine();
		for (i = 0; i < str.length(); i++) {
			left.addLast(str.charAt(i));
		}
		
		str = br.readLine();
		n = Integer.parseInt(str);
		
		for (i = 0; i < n; i++) {
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			command = st.nextToken();
			
			if (command.equals("L")) {
				if (left.size() != 0) {
					right.addFirst(left.pollLast());
				}
			} else if (command.equals("D")) {
				if (right.size() != 0) {
					left.addLast(right.pollFirst());
				}
			} else if (command.equals("B")) {
				if (left.size() != 0) {
					left.removeLast();
				}
			} else if (command.equals("P")) {
				temp = st.nextToken();
				left.addLast(temp.charAt(0));
			}
			
		}
		
		size = left.size();
		for (i = 0; i < size; i++) {
			bw.write(Character.toString(left.poll()));
		}
		size = right.size();
		for (i = 0; i < size; i++) {
			bw.write(Character.toString(right.poll()));
		}

		bw.flush();
		bw.close();
	}

}