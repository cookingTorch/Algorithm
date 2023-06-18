import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, cnt = 0, i;
		String notSaw;
		Set<String> notHeard = new HashSet<>();
		PriorityQueue<String> pq = new PriorityQueue<>();
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		for (i = 0; i < n; i++) {
			notHeard.add(br.readLine());
		}
		
		for (i = 0; i < m; i++) {
			notSaw = br.readLine();
			if (notHeard.contains(notSaw)) {
				pq.add(notSaw);
				cnt++;
			}
		}
		
		sb.append(cnt).append("\n");
		while (!pq.isEmpty()) {
			sb.append(pq.poll()).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}