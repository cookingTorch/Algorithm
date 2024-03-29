import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int BIT = 1 << 30;
	
	private static class Node {
		Node[] child;
		
		Node() {
			child = new Node[2];
		}
		
		void add(int num, int bit) {
			int curr;
			
			if (bit == 0) {
				return;
			}
			curr = (num & bit) == 0 ? 0 : 1;
			if (child[curr] == null) {
				child[curr] = new Node();
			}
			child[curr].add(num & (bit - 1), bit >> 1);
		}
		
		int sum(int num, int bit, int ans) {
			int curr, other;
			
			if (bit == 0) {
				return ans;
			}
			curr = (num & bit) == 0 ? 0 : 1;
			other = curr ^ 1;
			if (child[other] != null) {
				ans = (ans << 1) + 1;
				return child[other].sum(num & (bit - 1), bit >> 1, ans);
			} else {
				ans = (ans << 1) + 0;
				return child[curr].sum(num & (bit - 1), bit >> 1, ans);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int t, testCase, n, prefix, max, i;
		Node trie;
		
		t = Integer.parseInt(br.readLine());
		for (testCase = 0; testCase < t; testCase++) {
			trie = new Node();
			n = Integer.parseInt(br.readLine());
			prefix = 0;
			max = 0;
			trie.add(prefix, BIT);
			st = new StringTokenizer(br.readLine());
			for (i = 0; i < n; i++) {
				prefix ^= Integer.parseInt(st.nextToken());
				max = Math.max(max, trie.sum(prefix, BIT, 0));
				trie.add(prefix, BIT);
			}
			sb.append(max).append('\n');
		}
		System.out.print(sb);
	}
}
