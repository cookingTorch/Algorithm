import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static class Node {
		char alph;
		Node left, right;
		
		Node(char alph) {
			this.alph = alph;
		}
	}
	
	private static void pre(Node node, StringBuilder sb) {
		if (node == null) {
			return;
		}
		sb.append(node.alph);
		pre(node.left, sb);
		pre(node.right, sb);
	}
	
	private static void in(Node node, StringBuilder sb) {
		if (node == null) {
			return;
		}
		in(node.left, sb);
		sb.append(node.alph);
		in(node.right, sb);
	}

	private static void post(Node node, StringBuilder sb) {
		if (node == null) {
			return;
		}
		post(node.left, sb);
		post(node.right, sb);
		sb.append(node.alph);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, curr, i;
		char next;
		Node[] nodes;
		
		n = Integer.parseInt(br.readLine());
		nodes = new Node[n];
		for (i = 0; i < n; i++) {
			nodes[i] = new Node((char) ('A' + i));
		}
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			curr = st.nextToken().charAt(0) - 'A';
			next = st.nextToken().charAt(0);
			if (next != '.') {
				nodes[curr].left = nodes[next - 'A'];
			}
			next = st.nextToken().charAt(0);
			if (next != '.') {
				nodes[curr].right = nodes[next - 'A'];
			}
		}
		pre(nodes[0], sb);
		sb.append('\n');
		in(nodes[0], sb);
		sb.append('\n');
		post(nodes[0], sb);
		System.out.print(sb);
	}
}
