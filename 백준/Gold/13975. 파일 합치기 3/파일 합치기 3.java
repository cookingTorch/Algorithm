import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static final int MAX_K = 1_000_001;
	private static final char LINE_BREAK = '\n';

	private static int k;
	private static int idx;
	private static int[] arr;
	private static BufferedReader br;
	private static PriorityQueue<Long> pq;

	private static long getMin() {
		if (idx == k) {
			return pq.poll();
		}
		if (pq.isEmpty() || arr[idx] < pq.peek()) {
			return arr[idx++];
		}
		return pq.poll();
	}

	private static long solution() throws IOException {
		int i;
		long sum;
		long cost;
		StringTokenizer st;

		k = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ", false);
		for (i = 0; i < k; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr, 0, k);
		idx = 0;
		sum = 0L;
		for (i = 1; i < k; i++) {
			cost = getMin() + getMin();
			sum += cost;
			pq.offer(cost);
		}
		pq.poll();
		return sum;
	}

	public static void main(String[] args) throws IOException {
		int t;
		StringBuilder sb;

		arr = new int[MAX_K];
		pq = new PriorityQueue<>();
		br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		while (t-- > 0) {
			sb.append(solution()).append(LINE_BREAK);
		}
		System.out.print(sb.toString());
	}
}
