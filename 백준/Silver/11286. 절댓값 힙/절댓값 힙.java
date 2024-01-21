import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n, x, i;
		PriorityQueue<Integer> pq;
		
		pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Math.abs(o1) == Math.abs(o2) ? o1 - o2 : Math.abs(o1) - Math.abs(o2);
			}
		});
		n = Integer.parseInt(br.readLine());
		for (i = 0; i < n; i++) {
			x = Integer.parseInt(br.readLine());
			if (x == 0) {
				if (pq.isEmpty()) {
					sb.append('0').append('\n');
				} else {
					sb.append(pq.poll()).append('\n');
				}
			} else {
				pq.add(x);
			}
		}
		System.out.println(sb);
	}
}