import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static final class Partition implements Comparable<Partition> {
		int idx;
		boolean start;

		Partition(int idx, boolean start) {
			this.idx = idx;
			this.start = start;
		}

		@Override
		public int compareTo(Partition o) {
			return Integer.compare(this.idx, o.idx);
		}
	}

	public static void main(String[] args) throws IOException {
		int n;
		int cnt;
		int idx;
		int ans;
		Partition partition;
		BufferedReader br;
		StringTokenizer st;
		PriorityQueue<Partition> pq;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<>();
		while (n-- > 0) {
			st = new StringTokenizer(br.readLine());
			pq.add(new Partition(Integer.parseInt(st.nextToken()), true));
			pq.add(new Partition(Integer.parseInt(st.nextToken()), false));
		}
		idx = 0;
		cnt = 0;
		ans = 0;
		while (!pq.isEmpty()) {
			partition = pq.poll();
			if (partition.start) {
				if (cnt++ == 0) {
					idx = partition.idx;
				}
			} else {
				if (--cnt == 0) {
					ans += partition.idx - idx;
				}
			}
		}
		System.out.print(ans);
	}
}
