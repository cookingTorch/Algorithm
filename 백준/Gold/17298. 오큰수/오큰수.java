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
		
		int n, i, ai, temp, in;
		int[] nge;
		Deque<Integer> index = new LinkedList<>(); 
		Deque<Integer> num = new LinkedList<>();
		
		str = br.readLine();
		n = Integer.parseInt(str);
		
		nge = new int[n];
		
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		
		for (i = 0; i < n; i++) {
			ai = Integer.parseInt(st.nextToken());
			while (num.size() != 0) {
				temp = num.pollLast();
				if (ai > temp) {
					in = index.pollLast();
					nge[in] = ai;
				}
				else {
					num.addLast(temp);
					break;
				}
			}
			index.add(i);
			num.add(ai);
		}
		
		while (index.size() != 0) {
			in = index.pollLast();
			nge[in] = -1;
		}
		
		for (i = 0; i < n; i++) {
			bw.write(Integer.toString(nge[i]) + " ");
		}
		
		bw.flush();
		bw.close();
	}

}