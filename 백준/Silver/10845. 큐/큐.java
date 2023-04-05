import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		StringTokenizer st;
		
		int n, i;
		String command;
		ArrayList<Integer> nums = new ArrayList<>();
		
		str = br.readLine();
		n = Integer.parseInt(str);

		for (i = 0; i < n; i++) {
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			command = st.nextToken();
			if (command.equals("push")) {
				nums.add(Integer.parseInt(st.nextToken()));
			}
			else if (command.equals("pop")) {
				if (nums.size() > 0) {
					bw.write(Integer.toString(nums.remove(0)));
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
					bw.write(Integer.toString(nums.get(0)));
				}
				else {
					bw.write("-1");
				}
				bw.newLine();
			}
			else if (command.equals("back")) {
				if (nums.size() > 0) {
					bw.write(Integer.toString(nums.get(nums.size() - 1)));
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