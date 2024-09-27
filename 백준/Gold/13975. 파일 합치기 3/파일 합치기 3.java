import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static final int MAX_N = 1_000_000;
	private static final char LINE_BREAK = '\n';

	private static BufferedReader br;
	private static StringTokenizer st;
	private static PriorityQueue<Long> pq;

	private static long solution() throws IOException {
		int n;
		long sum;
		long cost;

		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ", false);
		while (n-- > 0) {
			pq.offer(Long.parseLong(st.nextToken()));
		}
		sum = 0;
		while (pq.size() > 1) {
			sum += (cost = pq.poll() + pq.poll());
			pq.offer(cost);
		}
		pq.poll();
		return sum;
	}

	public static void main(String[] args) throws IOException {
		int t;
		StringBuilder sb;

		pq = new PriorityQueue<>(MAX_N);
		br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		while (t-- > 0) {
			sb.append(solution()).append(LINE_BREAK);
		}
		System.out.print(sb.toString());
	}
}
