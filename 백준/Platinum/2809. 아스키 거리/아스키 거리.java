import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
	private static final int NUM = 26;
	private static final int DIFF = 'a';
	
	private static final class Trie {
		int end;
		Trie fail;
		Trie[] next;
		
		Trie() {
			next = new Trie[NUM];
		}
		
		final void add(String str, int depth) {
			int idx;
			
			if (depth == str.length()) {
				end = depth;
				return;
			}
			idx = str.charAt(depth) - DIFF;
			if (next[idx] == null) {
				next[idx] = new Trie();
			}
			next[idx].add(str, depth + 1);
		}
		
		final Trie go(int idx) {
			if (next[idx] == null) {
				if (this == root) {
					return root;
				}
				return fail.go(idx);
			}
			return next[idx];
		}
		
		final void buildFail() {
			int i;
			Trie curr;
			Trie next;
			ArrayDeque<Trie> q;
			
			this.fail = this;
			q = new ArrayDeque<>();
			for (Trie first : this.next) {
				if (first == null) {
					continue;
				}
				first.fail = this;
				first.end = Math.max(this.end, first.end);
				q.addLast(first);
			}
			while (!q.isEmpty()) {
				curr = q.pollFirst();
				for (i = 0; i < NUM; i++) {
					if ((next = curr.next[i]) == null) {
						continue;
					}
					next.fail = curr.fail.go(i);
					next.end = Math.max(next.fail.end, next.end);
					q.addLast(next);
				}
			}
		}
		
		final void find(String str, int depth) {
			if (end > 0) {
				update(1, 0, n - 1, depth - end, depth - 1);
			}
			if (depth == str.length()) {
				return;
			}
			go(str.charAt(depth) - DIFF).find(str, depth + 1);
		}
	}
	
	private static int n;
	private static int cnt;
	private static boolean[] lazy;
	private static Trie root;
	
	private static final void prop(int node) {
		if (lazy[node]) {
			lazy[node << 1] = true;
			lazy[node << 1 | 1] = true;
			lazy[node] = false;
		}
	}
	
	private static final void update(int node, int start, int end, int left, int right) {
		int mid;
		
		if (end < left || right < start) {
			return;
		}
		if (left <= start && end <= right) {
			lazy[node] = true;
			return;
		}
		mid = start + end >> 1;
		update(node << 1, start, mid, left, right);
		update(node << 1 | 1, mid + 1, end, left, right);
	}
	
	private static final void propAll(int node, int start, int end) {
		int mid;
		
		if (start == end) {
			if (lazy[node]) {
				cnt++;
			}
			return;
		}
		prop(node);
		mid = start + end >> 1;
		propAll(node << 1, start, mid);
		propAll(node << 1 | 1, mid + 1, end);
	}
	
	public static void main(String[] args) throws IOException {
		int m;
		int i;
		String str;
		BufferedReader br;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		str = br.readLine();
		root = new Trie();
		m = Integer.parseInt(br.readLine());
		for (i = 0; i < m; i++) {
			root.add(br.readLine(), 0);
		}
		root.buildFail();
		lazy = new boolean[4 * n];
		root.find(str, 0);
		cnt = 0;
		propAll(1, 0, n - 1);
		System.out.print(str.length() - cnt);
	}
}
