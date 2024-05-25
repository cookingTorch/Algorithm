import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static final String SYMBOLS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!?.,:;-_'#$%&/=*+(){}[] ";
	private static final int SIZE = SYMBOLS.length();
	private static final HashMap<Character, Integer> MAP = getMap();
	
	private static final class Result implements Comparable<Result> {
		int end;
		int start;
		
		Result(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Result o) {
			if (this.end == o.end) {
				return Integer.compare(this.start, o.start);
			}
			return Integer.compare(this.end, o.end);
		}
	}
	
	private static final class Trie {
		Trie fail;
		Trie[] next;
		ArrayList<Integer> outputs;
		
		Trie() {
			this.next = new Trie[SIZE];
			this.outputs = new ArrayList<>();
		}
		
		final void insert(String str) {
			int i;
			int idx;
			int len;
			Trie curr;
			
			len = str.length();
			for (curr = this, i = 0; i < len; i++, curr = curr.next[idx]) {
				idx = MAP.get(str.charAt(i));
				if (curr.next[idx] == null) {
					curr.next[idx] = new Trie();
				}
			}
			curr.outputs.add(len);
		}
		
		private final Trie go(int idx) {
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
				for (i = 0; i < SIZE; i++) {
					if ((next = curr.next[i]) == null) {
						continue;
					}
					next.fail = curr.fail.go(i);
					if (!next.fail.outputs.isEmpty()) {
						for (int output : next.fail.outputs) {
							next.outputs.add(output);
						}
					}
					q.add(next);
				}
			}
		}
		
		final void search(String str) {
			int i;
			int idx;
			int len;
			Trie curr;
			
			len = str.length();
			for (curr = this, i = 0; i < len; i++) {
				idx = MAP.get(str.charAt(i));
				curr = curr.go(idx);
				if (!curr.outputs.isEmpty()) {
					for (int output : curr.outputs) {
						pq.offer(new Result(i - output + 1, i));
					}
				}
			}
		}
	}
	
	private static final HashMap<Character, Integer> getMap() {
		int i;
		HashMap<Character, Integer> map;
		
		map = new HashMap<>();
		for (i = 0; i < SIZE; i++) {
			map.put(SYMBOLS.charAt(i), i);
		}
		return map;
	}
	
	private static Trie root;
	private static BufferedReader br;
	private static PriorityQueue<Result> pq;
	
	private static final int solution(int n, int m) throws IOException {
		int i;
		int cnt;
		int deleted;
		Result result;
		
		root = new Trie();
		for (i = 0; i < n; i++) {
			root.insert(br.readLine());
		}
		root.buildFail();
		cnt = 0;
		for (i = 0; i < m; i++) {
			root.search(br.readLine());
			deleted = -1;
			while (!pq.isEmpty()) {
				result = pq.poll();
				if (result.start <= deleted && deleted <= result.end) {
					continue;
				}
				deleted = result.end;
				cnt++;
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		int n;
		int m;
		StringBuilder sb;
		StringTokenizer st;
		
		pq = new PriorityQueue<>();
		sb = new StringBuilder();
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		while (n != 0) {
			sb.append(solution(n, m)).append('\n');
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
		}
		System.out.print(sb);
	}
}
