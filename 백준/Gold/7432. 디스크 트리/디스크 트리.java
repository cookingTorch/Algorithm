import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	private static final char LINE_BREAK = '\n';
	private static final char INDENTATION = ' ';
	private static final String DELIM = "\\";
	
	private static final class Trie {
		private static StringBuilder sb;
		
		TreeMap<String, Trie> child;
		
		Trie() {
			child = new TreeMap<>();
		}
		
		final void insert(StringTokenizer st) {
			Trie next;
			String str;
			
			str = st.nextToken();
			if ((next = child.get(str)) == null) {
				child.put(str, next = new Trie());
			}
			if (st.hasMoreTokens()) {
				next.insert(st);
			}
		}
		
		private final void build(int depth) {
			int i;
			
			for (Entry<String, Trie> entry : child.entrySet()) {
				for (i = 0; i < depth; i++) {
					sb.append(INDENTATION);
				}
				sb.append(entry.getKey()).append(LINE_BREAK);
				entry.getValue().build(depth + 1);
			}
		}

		@Override
		public String toString() {
			sb = new StringBuilder();
			build(0);
			return sb.toString();
		}
	}
	
	public static void main(String[] args) throws IOException {
		int n;
		int i;
		Trie trie;
		BufferedReader br;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		trie = new Trie();
		for (i = 0; i < n; i++) {
			trie.insert(new StringTokenizer(br.readLine(), DELIM));
		}
		System.out.print(trie.toString());
	}
}
