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
		
		int n, m, i;
		long x, y, sum, ans = 0;
		PriorityQueue<Long> card = new PriorityQueue<>();
		
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		for (i = 0; i < n; i++) {
			card.add(Long.parseLong(st.nextToken()));
		}
		
		for (i = 0; i < m; i++) {
			x = card.poll();
			y = card.poll();
			sum = x + y;
			card.add(sum);
			card.add(sum);
		}

		for (i = 0; i < n; i++) {
			ans += card.poll();
		}
		
		bw.write(Long.toString(ans));
		
		bw.flush();
		bw.close();
	}

}