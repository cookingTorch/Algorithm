import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	private static final String LINE = "--";
	
	private static final class Node {
		int floor;
		TreeMap<String, Node> childs;
		
		Node(int floor) {
			this.floor = floor;
			this.childs = new TreeMap<>();
		}
	}
	
	private static Node child;
	private static String str;
	private static StringBuilder sb;
	
	private static void push(Node node, StringTokenizer st) {
		str = st.nextToken();
		child = node.childs.get(str);
		if (child == null) {
			child = new Node(node.floor + 1);
			node.childs.put(str, child);
		}
		if (st.hasMoreTokens()) {
			push(child, st);
		}
	}
	
	private static void dfs(Node node) {
		int i;
		StringBuilder line;
		
		line = new StringBuilder();
		for (i = 0; i < node.floor; i++) {
			line.append(LINE);
		}
		for (Entry<String, Node> entry : node.childs.entrySet()) {
			sb.append(line).append(entry.getKey()).append('\n');
			if (!entry.getValue().childs.isEmpty()) {
				dfs(entry.getValue());
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, i;
		Node root;
		
		n = Integer.parseInt(br.readLine());
		root = new Node(0);
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			push(root, st);
		}
		sb = new StringBuilder();
		dfs(root);
		System.out.print(sb);
	}
}
