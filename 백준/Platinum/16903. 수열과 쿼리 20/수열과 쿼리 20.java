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
	
	private static void delete(Node node, int num, int bit) {
		node.cnt--;
		
		if (bit == 0) {
			return;
		}
		
		if ((num & bit) == 0) {
			if (node.zero.cnt == 1) {
				node.zero = null;
				return;
			}
			delete(node.zero, num & (bit - 1), bit >> 1);
		} else {
			if (node.one.cnt == 1) {
				node.one = null;
				return;
			}
			delete(node.one, num & (bit - 1), bit >> 1);
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
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int m, q, x, bit, i;
		Node root;
		
		bit = 1 << 29;
		root = new Node();
		add(root, 0, bit);
		m = Integer.parseInt(br.readLine());
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			q = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			if (q == 1) {
				add(root, x, bit);
			} else if (q == 2) {
				delete(root, x, bit);
			} else {
				sb.append(sum(root, x, bit, 0)).append('\n');
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}