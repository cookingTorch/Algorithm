import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	private static final int G = 'G';
	private static final int T = 'T';
	private static final int C = 'C';
	private static final int END = 'U';
	private static final int NUM = 4;
	
	private static final class Trie {
		int isEnd;
		Trie fail;
		Trie[] next;
		
		Trie() {
			next = new Trie[NUM];
		}
		
		final void add(char[] str, int depth) {
			int idx;
			
			if (depth == str.length) {
				isEnd = 1;
				return;
			}
			idx = map[str[depth]];
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
		
		final int find(String str, int depth) {
			if (depth == str.length()) {
				return isEnd;
			}
			return go(map[str.charAt(depth)]).find(str, depth + 1) + isEnd;
		}
	}
	
	private static int[] map;
	private static BufferedReader br;
	private static Trie root;
	
	private static final int solution() throws IOException {
		int m;
		int i;
		int j;
		int left;
		int right;
		char[] marker;
		char[] mutation;
		String dna;
		StringTokenizer st;
		
		root = new Trie();
		st = new StringTokenizer(br.readLine());
		st.nextToken();
		m = Integer.parseInt(st.nextToken());
		dna = br.readLine();
		marker = br.readLine().toCharArray();
		root.add(marker, 0);
		mutation = new char[m];
		for (left = 0; left < m; left++) {
			for (right = left + 1; right < m; right++) {
				for (i = 0; i < left; i++) {
					mutation[i] = marker[i];
				}
				for (j = 0; i <= right; i++, j++) {
					mutation[i] = marker[right - j];
				}
				for (; i < m; i++) {
					mutation[i] = marker[i];
				}
				root.add(mutation, 0);
			}
		}
		root.makeFail();
		return root.find(dna, 0);
	}
	
	public static void main(String[] args) throws IOException {
		int t;
		int testCase;
		StringBuilder sb;
		
		map = new int[END];
		map[G] = 1;
		map[T] = 2;
		map[C] = 3;
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		t = Integer.parseInt(br.readLine());
		for (testCase = 0; testCase < t; testCase++) {
			sb.append(solution()).append('\n');
		}
		System.out.print(sb);
	}
}
