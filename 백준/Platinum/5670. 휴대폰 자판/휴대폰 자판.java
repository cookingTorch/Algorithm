import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int ALPH = 26;
	private static final int MAX_N = 100_000;
	private static final int DIFF = 'a';
	private static final String FORMAT = "%.2f\n";
	
	private static final class Trie {
		int cnt;
		Trie[] next;
		
		Trie() {
			next = new Trie[ALPH];
		}
		
		final void insert(String str) {
			int i;
			int len;
			int idx;
			Trie curr;
			
			len = str.length();
			for (curr = this, i = 0; i < len; i++, curr = curr.next[idx]) {
				idx = str.charAt(i) - DIFF;
				if (curr.next[idx] == null) {
					curr.next[idx] = new Trie();
					curr.cnt++;
				}
			}
			curr.cnt++;
		}
		
		final int search(String str) {
			int i;
			int idx;
			int len;
			int ret;
			Trie curr;
			
			ret = 0;
			len = str.length();
			for (curr = this, i = 0; i < len; i++, curr = curr.next[idx]) {
				idx = str.charAt(i) - DIFF;
				if (curr.cnt != 1) {
					ret++;
				}
			}
			return ret;
		}
	}
	
	private static Trie root;
	private static String[] strs;
	private static BufferedReader br;
	
	private static final String solution(int n) throws IOException {
		int i;
		int sum;
		
		root = new Trie();
		for (i = 0; i < n; i++) {
			strs[i] = br.readLine();
			root.insert(strs[i]);
		}
		root.cnt = ALPH;
		sum = 0;
		for (i = 0; i < n; i++) {
			sum += root.search(strs[i]);
		}
		return String.format(FORMAT, (double) sum / n);
	}
	
	public static void main(String[] args) throws IOException {
		String str;
		StringBuilder sb;
		
		strs = new String[MAX_N];
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		while ((str = br.readLine()) != null) {
			sb.append(solution(Integer.parseInt(str)));
		}
		System.out.print(sb);
	}
}
