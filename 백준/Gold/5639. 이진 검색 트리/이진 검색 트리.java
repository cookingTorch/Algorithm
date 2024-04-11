import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final Node NIL = new Node();
	
	private static final class Node {
		int key;
		Node left, right;
		
		Node() {
		}
		
		Node(int key) {
			this.key = key;
			left = NIL;
			right = NIL;
		}
		
		void preorder(int curr) {
			if (curr < key) {
				if (left == NIL) {
					left = new Node(curr);
				} else {
					left.preorder(curr);
				}
			} else {
				if (right == NIL) {
					right = new Node(curr);
				} else {
					right.preorder(curr);
				}
			}
		}
		
		void postorder() {
			if (this == NIL) {
				return;
			}
			left.postorder();
			right.postorder();
			sb.append(key).append('\n');
		}
	}
	
	private static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str;
		Node root;
		
		root = null;
		if ((str = br.readLine()) != null) {
			root = new Node(Integer.parseInt(str));
		}
		while ((str = br.readLine()) != null) {
			root.preorder(Integer.parseInt(str));
		}
		sb = new StringBuilder();
		root.postorder();
		System.out.print(sb);
	}
}
