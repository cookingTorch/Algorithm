import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		int n;
		int k;
		int i;
		int num;
		int sum;
		BufferedReader br;
		StringTokenizer st;
		PriorityQueue<Integer> pq1;
		PriorityQueue<Integer> pq2;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		k = Math.min(n, Integer.parseInt(br.readLine()));
		pq1 = new PriorityQueue<>();
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			pq1.offer(Integer.parseInt(st.nextToken()));
		}
		pq2 = new PriorityQueue<>(Collections.reverseOrder());
		num = pq1.poll();
		sum = 0;
		while (!pq1.isEmpty()) {
			sum += pq1.peek() - num;
			pq2.offer(pq1.peek() - num);
			num = pq1.poll();
		}
		for (i = 1; i < k; i++) {
			sum -= pq2.poll();
		}
		System.out.print(sum);
	}
}
