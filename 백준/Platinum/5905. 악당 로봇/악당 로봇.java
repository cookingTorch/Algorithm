import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	private static final int SIZE = 3;
	private static final int DIFF = 'A';

	private static final class Trie {
		int output;
		int[] dp;
		Trie fail;
		Trie[] next;

		Trie() {
			dp = new int[k + 1];
			next = new Trie[SIZE];
		}

		void insert(String str) {
			int i;
			int len;
			int idx;
			Trie curr;

			len = str.length();
			for (i = 0, curr = root; i < len; i++, curr = curr.next[idx]) {
				idx = str.charAt(i) - DIFF;
				if (curr.next[idx] == null) {
					curr.next[idx] = new Trie();
				}
			}
			curr.output++;
		}

		Trie go(int idx) {
			if (next[idx] == null) {
				if (this == root) {
					return this;
				}
				return fail.go(idx);
			}
			return next[idx];
		}

		void buildFail() {
			int i;
			Trie curr;
			Trie next;
			ArrayDeque<Trie> q;

			q = new ArrayDeque<>();
			for (Trie first : this.next) {
				if (first == null) {
					continue;
				}
				first.fail = this;
				q.addLast(first);
			}
			while (!q.isEmpty()) {
				curr = q.pollFirst();
				for (i = 0; i < SIZE; i++) {
					next = curr.next[i];
					if (next == null) {
						continue;
					}
					next.fail = curr.fail.go(i);
					next.output += next.fail.output;
					q.addLast(next);
				}
			}
		}

		int getDp() {
			int i;
			int j;
			int max;
			Trie curr;
			Trie next;
			HashSet<Trie> visited;
			ArrayDeque<Trie> q1;
			ArrayDeque<Trie> q2;
			ArrayDeque<Trie> temp;

			q1 = new ArrayDeque<>();
			q2 = new ArrayDeque<>();
			q1.addLast(root);
			visited = new HashSet<>();
			max = 0;
			for (i = 1; i <= k; i++) {
				visited.clear();
				while (!q1.isEmpty()) {
					curr = q1.pollFirst();
					for (j = 0; j < SIZE; j++) {
						next = curr.go(j);
						max = Math.max(max, next.dp[i] = Math.max(next.dp[i], curr.dp[i - 1] + next.output));
						if (!visited.contains(next)) {
							q2.addLast(next);
							visited.add(next);
						}
					}
				}
				temp = q1;
				q1 = q2;
				q2 = temp;
			}
			return max;
		}
	}

	private static int k;
	private static Trie root;

	public static void main(String[] args) throws IOException {
		int n;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		root = new Trie();
		while (n-- > 0) {
			root.insert(br.readLine());
		}
		root.buildFail();
		System.out.print(root.getDp());
	}
}
