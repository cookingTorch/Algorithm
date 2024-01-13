import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	private static class Node {
		Node zero, one;
		
		Node() {
			this.zero = null;
			this.one = null;
		}
	}
	
	private static void add(Node node, int num, int bit) {
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
			if (node.one != null) {
				ans = (ans << 1) + 1;
				return sum(node.one, num & (bit - 1), bit >> 1, ans);
			} else {
				ans = (ans << 1) + 0;
				return sum(node.zero, num & (bit - 1), bit >> 1, ans);
			}
		} else {
			if (node.zero != null) {
				ans = (ans << 1) + 1;
				return sum(node.zero, num & (bit - 1), bit >> 1, ans);
			} else {
				ans = (ans << 1) + 0;
				return sum(node.one, num & (bit - 1), bit >> 1, ans);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, a, bit, i, max;
		Node root;
		
		bit = 1 << 29;
		root = new Node();
		max = 0;
		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		add(root, a, bit);
		for (i = 1; i < n; i++) {
			a = Integer.parseInt(st.nextToken());
			add(root, a, bit);
			max = Math.max(max, sum(root, a, bit, 0));
		}
		sb.append(max);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}