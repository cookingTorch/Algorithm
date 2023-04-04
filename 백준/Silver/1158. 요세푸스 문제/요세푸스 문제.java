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
		
		int n, k, i, j, num = 0;
		Deque<Integer> circle = new LinkedList<>();
		Deque<Integer> temp = new LinkedList<>();
		
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		for (i = 0; i < n; i++) {
			circle.addLast(i + 1);
		}
		
		bw.write("<");
		
		for (i = 0; i < n; i++) {
			
			for (j = 0; j < k - 1; j++) {
				num++;
				if (num == circle.size()) {
					num = 0;
				}
			}
			
			for (j = 0; j < num; j++) {
				temp.addLast(circle.pollFirst());
			}
			
			bw.write(Integer.toString(circle.pollFirst()));
			
			while (temp.size() > 0) {
				circle.addFirst(temp.pollLast());
			}
			
			if (circle.size() > 0) {
				bw.write(", ");
			}
			
			if (num == circle.size()) {
				num = 0;
			}
			
		}
		
		bw.write(">");
		
		bw.flush();
		bw.close();
	}

}