import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static long bfs(long start, long end) {
		long next;
		long[] curr;
		Queue<long[]> q;
		
		q = new ArrayDeque<>();
		q.add(new long[] {start, 1});
		while (!q.isEmpty()) {
			curr = q.poll();
			if ((next = curr[0] * 10 + 1) < end) {
				q.add(new long[] {next, curr[1] + 1});
			} else if (next == end) {
				return curr[1] + 1;
			}
			if ((next = curr[0] << 1) < end) {
				q.add(new long[] {next, curr[1] + 1});
			} else if (next == end) {
				return curr[1] + 1;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		long a, b;
		
		st = new StringTokenizer(br.readLine());
		a = Long.parseLong(st.nextToken());
		b = Long.parseLong(st.nextToken());
		if (a == b) {
			System.out.print('1');
			return;
		}
		System.out.print(bfs(a, b));
	}
}
