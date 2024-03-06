import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	
	private static class Task implements Comparable<Task> {
		int d, t;
		
		Task(int d, int t) {
			this.d = d;
			this.t = t;
		}

		@Override
		public int compareTo(Task o) {
			return Integer.compare(o.t, this.t);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, end, i;
		Task task;
		PriorityQueue<Task> pq;
		
		n = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<>();
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			pq.add(new Task(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		end = INF;
		for (i = 0; i < n; i++) {
			task = pq.poll();
			end = Math.min(end, task.t) - task.d;
		}
		System.out.print(end);
	}
}
