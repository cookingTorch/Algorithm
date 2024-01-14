import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	private static class Node {
		int cnt;
		Node zero, one;
		
		Node() {
			this.zero = null;
			this.one = null;
			this.cnt = 0;
		}
	}
	
	private static void add(Node node, int num, int bit) {
		node.cnt++;
		
		if (bit == 0) {
			return;
		}
		
		if ((num & bit) == 0) {
			if (node.zero == null) {
				node.zero = new Node();
			}
			add(node.zero, num & (bit - 1), bit >> 1);
		} else {
			if (node.one == null) {
				node.one = new Node();
			}
			add(node.one, num & (bit - 1), bit >> 1);
		}
	}
	
	private static int sum(Node node, int num, int bit, int ans) {
		if (bit == 0) {
			return ans;
		}
		
		if ((num & bit) == 0) {
			if (node.one != null && node.one.cnt > 0) {
				ans = (ans << 1) + 1;
				return sum(node.one, num & (bit - 1), bit >> 1, ans);
			} else {
				ans = (ans << 1) + 0;
				return sum(node.zero, num & (bit - 1), bit >> 1, ans);
			}
		} else {
			if (node.zero != null && node.zero.cnt > 0) {
				ans = (ans << 1) + 1;
				return sum(node.zero, num & (bit - 1), bit >> 1, ans);
			} else {
				ans = (ans << 1) + 0;
				return sum(node.one, num & (bit - 1), bit >> 1, ans);
			}
		}
	}
	
	private static void reset(Node node, int bit) {
		node.cnt = 0;
		
		if (bit == 0) {
			return;
		}
		
		if (node.zero != null) {
			reset(node.zero, bit >> 1);
		}
		if (node.one != null) {
			reset(node.one, bit >> 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int t, testCase, n, a, bit, prefix, max, i;
		Node root;
		
		bit = 1 << 30;
		root = new Node();
		
		t = Integer.parseInt(br.readLine());
		for (testCase = 0; testCase < t; testCase++) {
			n = Integer.parseInt(br.readLine());
			prefix = 0;
			max = 0;
			add(root, prefix, bit);
			st = new StringTokenizer(br.readLine());
			for (i = 0; i < n; i++) {
				a = Integer.parseInt(st.nextToken());
				prefix ^= a;
				max = Math.max(max, sum(root, prefix, bit, 0));
				add(root, prefix, bit);
			}
			sb.append(max).append('\n');
			reset(root, bit);
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}