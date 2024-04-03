import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class Main {
	private static final class Node implements Comparable<Node> {
		int idx, num;
		
		Node(int idx, int num) {
			this.idx = idx;
			this.num = num;
		}

		@Override
		public int compareTo(Node o) {
			if (this.num == o.num) {
				return Integer.compare(this.idx, o.idx);
			}
			return Integer.compare(this.num, o.num);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n, num, i;
		Node mid;
		TreeSet<Node> set;
		
		n = Integer.parseInt(br.readLine());
		set = new TreeSet<>();
		mid = new Node(0, Integer.parseInt(br.readLine()));
		set.add(mid);
		sb.append(mid.num).append('\n');
		for (i = 1; i < n; i++) {
			num = Integer.parseInt(br.readLine());
			set.add(new Node(i, num));
			if (num < mid.num) {
				mid = set.lower(mid);
			}
			sb.append(mid.num).append('\n');
			if (++i == n) {
				break;
			}
			num = Integer.parseInt(br.readLine());
			set.add(new Node(i, num));
			if (num >= mid.num) {
				mid = set.higher(mid);
			}
			sb.append(mid.num).append('\n');
		}
		System.out.print(sb);
	}
}
