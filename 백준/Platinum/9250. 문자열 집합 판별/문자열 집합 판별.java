import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
	private static final int NUM = 26;
	private static final int DIFF = 'a';
	private static final String NO = "NO\n";
	private static final String YES = "YES\n";
	
	private static final class Trie {
		boolean isEnd;
		Trie fail;
		Trie[] next;
		
		Trie() {
			next = new Trie[NUM];
		}
		
		final void add(String str, int depth) {
			int idx;
			
			if (depth == str.length()) {
				isEnd = true;
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
		
		final void makeFail() {
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
				first.isEnd |= this.isEnd;
				q.addLast(first);
			}
			while (!q.isEmpty()) {
				curr = q.pollFirst();
				for (i = 0; i < NUM; i++) {
					if ((next = curr.next[i]) == null) {
						continue;
					}
					next.fail = curr.fail.go(i);
					next.isEnd |= next.fail.isEnd;
					q.addLast(next);
				}
			}
		}
		
		final boolean find(String str, int depth) {
			if (isEnd) {
				return true;
			}
			if (depth == str.length()) {
				return false;
			}
			return go(str.charAt(depth) - DIFF).find(str, depth + 1);
		}
	}
	
	private static Trie root;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n, q, i;
		
		root = new Trie();
		n = Integer.parseInt(br.readLine());
		for (i = 0; i < n; i++) {
			root.add(br.readLine(), 0);
		}
		root.makeFail();
		q = Integer.parseInt(br.readLine());
		for (i = 0; i < q; i++) {
			sb.append(root.find(br.readLine(), 0) ? YES : NO);
		}
		System.out.print(sb);
	}
}
