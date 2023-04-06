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
		
		int n, i;
		String command;
		Deque<Integer> nums = new LinkedList<>();
		
		str = br.readLine();
		n = Integer.parseInt(str);
		
		for (i = 0; i < n; i++) {
			
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			
			command = st.nextToken();
			if (command.equals("push_front")) {
				nums.addFirst(Integer.parseInt(st.nextToken()));
			}
			else if (command.equals("push_back")) {
				nums.addLast(Integer.parseInt(st.nextToken()));
			}
			else if (command.equals("pop_front")) {
				if (nums.size() > 0) {
					bw.write(Integer.toString(nums.pollFirst()));
				}
				else {
					bw.write("-1");
				}
				bw.newLine();
			}
			else if (command.equals("pop_back")) {
				if (nums.size() > 0) {
					bw.write(Integer.toString(nums.pollLast()));
				}
				else {
					bw.write("-1");
				}
				bw.newLine();
			}
			else if (command.equals("size")) {
				bw.write(Integer.toString(nums.size()));
				bw.newLine();
			}
			else if (command.equals("empty")) {
				if (nums.size() > 0) {
					bw.write("0");
				}
				else {
					bw.write("1");
				}
				bw.newLine();
			}
			else if (command.equals("front")) {
				if (nums.size() > 0) {
					bw.write(Integer.toString(nums.getFirst()));
				}
				else {
					bw.write("-1");
				}
				bw.newLine();
			}
			else if (command.equals("back")) {
				if (nums.size() > 0) {
					bw.write(Integer.toString(nums.getLast()));
				}
				else {
					bw.write("-1");
				}
				bw.newLine();
			}
			
		}
		
		bw.flush();
		bw.close();

	}

}