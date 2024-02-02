import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n, i;
		Queue<Integer> q;
		
		n = Integer.parseInt(br.readLine());
		q = new ArrayDeque<>();
		for (i = 1; i <= n; i++) {
			q.add(i);
		}
		while (q.size() > 1) {
			q.poll();
			q.add(q.poll());
		}
		System.out.print(q.poll());
	}
}
