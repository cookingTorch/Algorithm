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
		
		int n, i, sum = 0;
		Stack<Integer> nums = new Stack<>();
		
		str = br.readLine();
		n = Integer.parseInt(str);
		
		for (i = 0; i < n; i++) {
			str = br.readLine();
			if (str.equals("0")) {
				nums.pop();
			}
			else {
				nums.add(Integer.parseInt(str));
			}
		}
		
		n = nums.size();
		
		for (i = 0; i < n; i++) {
			sum += nums.pop();
		}
		
		bw.write(Integer.toString(sum));
		
		bw.flush();
		bw.close();

	}

}