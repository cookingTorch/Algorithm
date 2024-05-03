import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	private static final int ALPH = 26;
	private static final int DIFF = 'A';
	private static final char A = 'A';
	private static final char B = 'B';
	private static final char C = 'C';
	private static final char D = 'D';
	
	private static final class Result {
		int x;
		int y;
		char dir;
		
		Result(int x, int y, char dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		
		void setResult(int x, int y, char dir) {
			if (x < this.x || (x == this.x && y < this.y) || (x == this.x && y == this.y && dir < this.dir)) {
				this.x = x;
				this.y = y;
				this.dir = dir;
			}
		}

		@Override
		public String toString() {
			return x + " " + y + " " + dir;
		}
	}
	
	private static final class Output {
		int idx;
		int len;
		Output next;
		Output tail;
		
		Output(int idx) {
			this.idx = idx;
			this.tail = this;
		}
		
		Output(int idx, int len) {
			this(idx);
			this.len = len;
		}
		
		final void merge(Output output) {
			this.tail.next = output;
			this.tail = output.tail;
		}
		
		final void found(int x, int y, char dir) {
			if (len == 0) {
				dir += 4;
			} else {
				switch (dir) {
				case A:
					x += len - 1;
					break;
				case B:
					x += len - 1;
					y -= len - 1;
					break;
				case C:
					y -= len - 1;
					break;
				case D:
					x -= len - 1;
					y -= len - 1;
				}
			}
			if (result[idx] == null) {
				result[idx] = new Result(x, y, dir);
			} else {
				result[idx].setResult(x, y, dir);
			}
		}
	}
	
	private static final class Trie {
		Trie fail;
		Trie[] next;
		Output output;
		
		Trie() {
			next = new Trie[ALPH];
		}
		
		final void setOutput(Output output) {
			if (output == null) {
				return;
			}
			if (this.output == null) {
				this.output = output;
			} else {
				this.output.merge(output);
			}
		}
		
		final void insert(String str, int num, boolean reverse) {
			int i;
			int idx;
			int len;
			Trie curr;
			
			len = str.length();
			for (curr = this, i = 0; i < len; i++, curr = curr.next[idx]) {
				idx = (reverse ? str.charAt(len - i - 1) : str.charAt(i)) - DIFF;
				if (curr.next[idx] == null) {
					curr.next[idx] = new Trie();
				}
			}
			if (reverse) {
				curr.setOutput(new Output(num));
			} else {
				curr.setOutput(new Output(num, len));
				insert(str, num, true);
			}
		}
		
		final Trie go(int idx) {
			if (next[idx] == null) {
				if (this == root) {
					return this;
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
				for (i = 0; i < ALPH; i++) {
					if ((next = curr.next[i]) == null) {
						continue;
					}
					next.fail = curr.fail.go(i);
					next.setOutput(next.fail.output);
					q.addLast(next);
				}
			}
		}
		
		final void searchA(int y) {
			int x;
			Trie curr;
			Output output;
			
			curr = this;
			for (x = l - 1; x >= 0; x--) {
				curr = curr.go(map[x][y]);
				for (output = curr.output; output != null; output = output.next) {
					output.found(x, y, A);
				}
			}
		}
		
		final void searchB(int x, int y) {
			Trie curr;
			Output output;
			
			curr = this;
			for (; x >= 0 && y < c; x--, y++) {
				curr = curr.go(map[x][y]);
				for (output = curr.output; output != null; output = output.next) {
					output.found(x, y, B);
				}
			}
		}
		
		final void searchC(int x) {
			int y;
			Trie curr;
			Output output;
			
			curr = this;
			for (y = 0; y < c; y++) {
				curr = curr.go(map[x][y]);
				for (output = curr.output; output != null; output = output.next) {
					output.found(x, y, C);
				}
			}
		}
		
		final void searchD(int x, int y) {
			Trie curr;
			Output output;
			
			curr = this;
			for (; x < l && y < c; x++, y++) {
				curr = curr.go(map[x][y]);
				for (output = curr.output; output != null; output = output.next) {
					output.found(x, y, D);
				}
			}
		}
	}
	
	private static int l;
	private static int c;
	private static int w;
	private static int[][] map;
	private static Trie root;
	private static Result[] result;
	
	private static int[] parse(String str) {
		int i;
		int[] result;
		
		result = new int[c];
		for (i = 0; i < c; i++) {
			result[i] = str.charAt(i) - DIFF;
		}
		return result;
	}
	
	private static void search() {
		int i;
		
		result = new Result[w + 1];
		for (i = 0; i < c; i++) {
			root.searchA(i);
		}
		for (i = 0; i < l; i++) {
			root.searchB(i, 0);
		}
		for (i = 1; i < c; i++) {
			root.searchB(l - 1, i);
		}
		for (i = 0; i < l; i++) {
			root.searchC(i);
		}
		for (i = 0; i < c; i++) {
			root.searchD(0, i);
		}
		for (i = 1; i < l; i++) {
			root.searchD(i, 0);
		}
	}
	
	public static void main(String[] args) throws IOException {
		int i;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		l = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		map = new int[l][];
		for (i = 0; i < l; i++) {
			map[i] = parse(br.readLine());
		}
		root = new Trie();
		for (i = 1; i <= w; i++) {
			root.insert(br.readLine(), i, false);
		}
		root.buildFail();
		search();
		sb = new StringBuilder();
		for (i = 1; i <= w; i++) {
			sb.append(result[i]).append('\n');
		}
		System.out.print(sb);
	}
}
