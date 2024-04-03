import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n, i;
		PriorityQueue<Integer> left, right;
		
		n = Integer.parseInt(br.readLine());
		left = new PriorityQueue<>(Collections.reverseOrder());
		right = new PriorityQueue<>();
		left.add(Integer.parseInt(br.readLine()));
		sb.append(left.peek()).append('\n');
		for (i = 1; i < n; i++) {
			right.add(Integer.parseInt(br.readLine()));
			if (left.peek() > right.peek()) {
				left.add(right.poll());
				right.add(left.poll());
			}
			sb.append(left.peek()).append('\n');
			if (++i == n) {
				break;
			}
			left.add(Integer.parseInt(br.readLine()));
			if (left.peek() > right.peek()) {
				left.add(right.poll());
				right.add(left.poll());
			}
			sb.append(left.peek()).append('\n');
		}
		System.out.print(sb);
	}
}
