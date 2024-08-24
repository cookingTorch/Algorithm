import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static final class Line implements Comparable<Line> {
		int start;
		int end;

		Line(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Line o) {
			return Integer.compare(this.start, o.start);
		}
	}

	public static void main(String[] args) throws IOException {
		int n;
		int start;
		int end;
		int ans;
		Line line;
		PriorityQueue<Line> pq;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<>();
		while (n-- > 0) {
			st = new StringTokenizer(br.readLine());
			pq.add(new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		ans = 0;
		line = pq.poll();
		start = line.start;
		end = line.end;
		while (!pq.isEmpty()) {
			line = pq.poll();
			if (line.start > end) {
				ans += end - start;
				start = line.start;
			}
			end = Math.max(line.end, end);
		}
		System.out.print(ans + end - start);
	}
}
