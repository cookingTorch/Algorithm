import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int SIZE = 26;
	private static final int DIFF = 'a';
	private static final char LINE_BREAK = '\n';
	
	private static final class Trie {
		private final static StringBuilder sb = new StringBuilder();
		
		int num;
		Trie[] next;
		
		Trie() {
			next = new Trie[SIZE];
		}
		
		final void insert(String str) {
			int i;
			int idx;
			int len;
			char ch;
			Trie curr;
			Trie next;
			
			len = str.length();
			for (curr = this, i = 0; i < len; i++, curr = next) {
				ch = str.charAt(i);
				idx = ch - DIFF;
				sb.append(ch);
				if ((next = curr.next[idx]) == null) {
					curr.next[idx] = next = new Trie();
					break;
				}
			}
			if (i == len && curr.num > 0) {
				sb.append(curr.num + 1);
			}
			for (; i < len; i++, curr = next) {
				idx = str.charAt(i) - DIFF;
				if ((next = curr.next[idx]) == null) {
					curr.next[idx] = next = new Trie();
				}
			}
			curr.num++;
			sb.append(LINE_BREAK);
		}

		@Override
		public String toString() {
			return sb.toString();
		}
	}
	
	public static void main(String[] args) throws IOException {
		int n;
		Trie trie;
		BufferedReader br;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		trie = new Trie();
		while (n-- > 0) {
			trie.insert(br.readLine());
		}
		System.out.print(trie.toString());
	}
}
