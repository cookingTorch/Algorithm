import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int SIZE = 10;
	private static final int DIFF = '0';
	private static final String NO = "NO";
	private static final String YES = "YES";
	
	private static final class Trie {
		boolean output;
		boolean visited;
		Trie[] next;
		
		Trie() {
			next = new Trie[SIZE];
		}
		
		final boolean insert(String str, int depth) {
			int idx;
			
			if (output) {
				return false;
			}
			if (str.length() == depth) {
				if (visited) {
					return false;
				}
				visited = true;
				output = true;
				return true;
			}
			visited = true;
			idx = str.charAt(depth) - DIFF;
			if (next[idx] == null) {
				next[idx] = new Trie();
			}
			return next[idx].insert(str, depth + 1);
		}
	}

	private static BufferedReader br;
	
	private static boolean solution() throws IOException {
		int n;
		int i;
		boolean result;
		Trie root;
		
		root = new Trie();
		result = true;
		n = Integer.parseInt(br.readLine());
		for (i = 0; i < n; i++) {
			if (!root.insert(br.readLine(), 0)) {
				result = false;
				break;
			}
		}
		for (++i ; i < n; i++) {
			br.readLine();
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		int t;
		int testCase;
		StringBuilder sb;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		for (testCase = 0; testCase < t; testCase++) {
			sb.append(solution() ? YES : NO).append('\n');
		}
		System.out.print(sb);
	}
}
