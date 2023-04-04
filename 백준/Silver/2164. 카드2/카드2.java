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
		
		int n, i;
		Deque<Integer> card = new LinkedList<>();
		
		str = br.readLine();
		n = Integer.parseInt(str);
		
		for (i = 1; i <= n; i++) {
			card.addLast(i);
		}
		
		for (i = 0; i < n - 1; i++) {
			card.removeFirst();
			card.addLast(card.pollFirst());
		}
		
		bw.write(Integer.toString(card.poll()));
		
		bw.flush();
		bw.close();

	}

}