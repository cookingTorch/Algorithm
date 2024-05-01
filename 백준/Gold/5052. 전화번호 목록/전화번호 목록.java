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
		
		final boolean insert(String str) {
			int i;
			int len;
			int idx;
			Trie curr;
			
			len = str.length();
			for (curr = this, i = 0; i < len; curr = curr.next[idx], i++) {
				if (curr.output) {
					return false;
				}
				curr.visited = true;
				idx = str.charAt(i) - DIFF;
				if (curr.next[idx] == null) {
					curr.next[idx] = new Trie();
				}
			}
			if (curr.visited) {
				return false;
			}
			curr.visited = true;
			curr.output = true;
			return true;
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
			if (!root.insert(br.readLine())) {
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
