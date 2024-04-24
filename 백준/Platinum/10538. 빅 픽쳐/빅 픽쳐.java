import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	private static final int X = 'x';
	private static final int SIZE = 'z';
	
	private static final class Trie {
		int end;
		Trie fail;
		Trie[] next;
		
		Trie() {
			next = new Trie[2];
		}
		
		final void add(String str, int depth, int num) {
			int idx;
			
			if (depth == str.length()) {
				end = num;
				return;
			}
			idx = index[str.charAt(depth)];
			if (next[idx] == null) {
				next[idx] = new Trie();
			}
			next[idx].add(str, depth + 1, num);
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
				q.addLast(first);
			}
			while (!q.isEmpty()) {
				curr = q.pollFirst();
				for (i = 0; i < 2; i++) {
					if ((next = curr.next[i]) == null) {
						continue;
					}
					next.fail = curr.fail.go(i);
					q.addLast(next);
				}
			}
		}
		
		final void find(String str, int depth, int dest[]) {
			if (end > 0) {
				dest[depth - 1] = end;
			}
			if (depth == str.length()) {
				return;
			}
			go(index[str.charAt(depth)]).find(str, depth + 1, dest);
		}
	}
	
	private static int[] index;
	private static Trie root;
	
	private static final int[] getLps(int[] pat) {
		int i;
		int j;
		int[] lps;
		
		lps = new int[pat.length];
		i = 1;
		j = 0;
		while (i < pat.length) {
			if (pat[i] == pat[j]) {
				lps[i++] = ++j;
			} else if (j == 0) {
				i++;
			} else {
				j = lps[j - 1];
			}
		}
		return lps;
	}
	
	private static final int kmp(int[] pat, int[][] txt, int idx) {
		int i;
		int j;
		int cnt;
		int[] lps;
		
		lps = getLps(pat);
		cnt = 0;
		i = 0;
		j = 0;
		while (i < txt.length) {
			if (txt[i][idx] == pat[j]) {
				i++;
				if (++j == pat.length) {
					cnt++;
					j = lps[j - 1];
				}
			} else if (j == 0) {
				i++;
			} else {
				j = lps[j - 1];
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int i;
		int hp;
		int wp;
		int hm;
		int wm;
		int cnt;
		int[] drawing;
		int[][] masterpiece;
		String str;
		Integer num;
		HashMap<String, Integer> map;
		
		st = new StringTokenizer(br.readLine());
		hp = Integer.parseInt(st.nextToken());
		wp = Integer.parseInt(st.nextToken());
		hm = Integer.parseInt(st.nextToken());
		wm = Integer.parseInt(st.nextToken());
		map = new HashMap<>();
		drawing = new int[hp];
		root = new Trie();
		index = new int[SIZE];
		index[X] = 1;
		for (i = 1; i <= hp; i++) {
			str = br.readLine();
			if ((num = map.get(str)) == null) {
				map.put(str, i);
				num = i;
				root.add(str, 0, num);
			}
			drawing[i - 1] = num;
		}
		root.buildFail();
		masterpiece = new int[hm][wm];
		for (i = 0; i < hm; i++) {
			root.find(br.readLine(), 0, masterpiece[i]);
		}
		cnt = 0;
		for (i = wp - 1; i < wm; i++) {
			cnt += kmp(drawing, masterpiece, i);
		}
		System.out.print(cnt);
	}
}
