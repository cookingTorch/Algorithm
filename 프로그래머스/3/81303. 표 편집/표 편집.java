import java.util.ArrayDeque;

class Solution {
	private static final char U = 'U';
	private static final char D = 'D';
	private static final char C = 'C';
	private static final char Z = 'Z';
	private static final char O = 'O';
	private static final char X = 'X';
	private static final Node NIL = new Node(-1);

	private static final class Node {
		int idx;
		Node prev;
		Node next;

		Node(int idx) {
			this.idx = idx;
		}
	}

	private char[] ans;
	private Node node;
	private ArrayDeque<Node> stack;

	private void init(int n, int k) {
		int i;

		node = NIL;
		for (i = 0; i < n; i++) {
			node.next = new Node(i);
			node.next.prev = node;
			node = node.next;
		}
		node.next = NIL;
		NIL.prev = node;
		node = NIL.next;
		for (i = 0; i < k; i++) {
			node = node.next;
		}
		stack = new ArrayDeque<>();
		ans = new char[n];
		for (i = 0; i < n; i++) {
			ans[i] = O;
		}
	}

	private void up(int x) {
		while (x-- > 0) {
			node = node.prev;
		}
	}

	private void down(int x) {
		while (x-- > 0) {
			node = node.next;
		}
	}

	private void delete() {
		node.prev.next = node.next;
		node.next.prev = node.prev;
		stack.push(node);
		ans[node.idx] = X;
		if (node.next == NIL) {
			node = node.prev;
		} else {
			node = node.next;
		}
	}

	private void undo() {
		Node del;

		del = stack.pop();
		del.prev.next = del;
		del.next.prev = del;
		ans[del.idx] = O;
	}

	public String solution(int n, int k, String[] cmd) {
		init(n, k);
		for (String command : cmd) {
			switch (command.charAt(0)) {
				case U:
					up(Integer.parseInt(command, 2, command.length(), 10));
					break;
				case D:
					down(Integer.parseInt(command, 2, command.length(), 10));
					break;
				case C:
					delete();
					break;
				case Z:
					undo();
			}
		}
		return new String(ans, 0, n);
	}
}
