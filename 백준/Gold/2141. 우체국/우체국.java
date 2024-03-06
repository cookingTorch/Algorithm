import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static class Town implements Comparable<Town> {
		int x, a;
		
		Town(int x, int a) {
			this.x = x;
			this.a = a;
		}

		@Override
		public int compareTo(Town o) {
			return Integer.compare(this.x, o.x);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, x, a, i;
		long sum, prefix;
		Town town;
		PriorityQueue<Town> pq;
		
		n = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<>();
		sum = 0;
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			pq.add(new Town(x, a));
			sum += a;
		}
		town = null;
		sum = (sum + 1) / 2;
		prefix = 0;
		while (prefix < sum) {
			town = pq.poll();
			prefix += town.a;
		}
		System.out.print(town.x);
	}
}
