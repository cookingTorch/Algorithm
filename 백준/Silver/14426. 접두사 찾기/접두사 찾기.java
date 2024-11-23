import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int DIFF = 'a';
	private static final int ALPHABET_SIZE = 26;

	private static final class Trie {
		Trie[] next;

		Trie() {
			next = new Trie[ALPHABET_SIZE];
		}

		final void insert(char[] word) {
			int idx;
			Trie curr;

			curr = this;
			for (char ch : word) {
				idx = ch - DIFF;
				if (curr.next[idx] == null) {
					curr.next[idx] = new Trie();
				}
				curr = curr.next[idx];
			}
		}

		final boolean search(char[] word) {
			int idx;
			Trie curr;

			curr = this;
			for (char ch : word) {
				idx = ch - DIFF;
				if (curr.next[idx] == null) {
					return false;
				}
				curr = curr.next[idx];
			}
			return true;
		}
	}

	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int cnt;
		Trie trie;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ", false);
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		trie = new Trie();
		while (n-- > 0) {
			trie.insert(br.readLine().toCharArray());
		}
		cnt = 0;
		while (m-- > 0) {
			if (trie.search(br.readLine().toCharArray())) {
				cnt++;
			}
		}
		System.out.print(cnt);
	}
}
