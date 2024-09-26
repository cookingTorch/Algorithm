import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static final class Node implements Comparable<Node> {
		int idx;
		int num;

		Node(int idx, int num) {
			this.idx = idx;
			this.num = num;
		}

		@Override
		public int compareTo(Node o) {
			return o.num - num;
		}
	}

	public static void main(String[] args) throws IOException {
		int n;
		int i;
		int j;
		Node node;
		ArrayDeque<Node>[] stacks;
		PriorityQueue<Node> pq;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		stacks = new ArrayDeque[n];
		for (i = 0; i < n; i++) {
			stacks[i] = new ArrayDeque<>();
		}
		pq = new PriorityQueue<>(n);
		for (i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ", false);
			for (j = 0; j < n; j++) {
				stacks[j].addFirst(new Node(j, Integer.parseInt(st.nextToken())));
			}
		}
		st = new StringTokenizer(br.readLine(), " ", false);
		for (j = 0; j < n; j++) {
			pq.offer(new Node(j, Integer.parseInt(st.nextToken())));
		}
		while (--n > 0) {
			node = pq.poll();
			if (!stacks[node.idx].isEmpty()) {
				pq.offer(stacks[node.idx].pollFirst());
			}
		}
		System.out.print(pq.peek().num);
	}
}
