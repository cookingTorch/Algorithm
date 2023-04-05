import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		StringTokenizer st;
		
		int i;
		PriorityQueue<Integer> nums = new PriorityQueue<>();
		
		str = br.readLine();
		while (str.equals("0 0 0") == false) {
			st = new StringTokenizer(str, " ");
			for (i = 0; i < 3; i++) {
				nums.add(Integer.parseInt(st.nextToken()));
			}
			if (Math.pow(nums.poll(), 2) + Math.pow(nums.poll(), 2) == Math.pow(nums.poll(), 2)) {
				bw.write("right\n");
			}
			else {
				bw.write("wrong\n");
			}
			str = br.readLine();
		}
		
		bw.flush();
		bw.close();

	}

}