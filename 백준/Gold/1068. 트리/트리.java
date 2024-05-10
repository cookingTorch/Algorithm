import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final class Node {
		int idx;
		Node next;
		
		Node(int idx, Node next) {
			this.idx = idx;
			this.next = next;
		}
	}
	
	private static int cnt;
	private static int erased;
	private static Node[] adj;
	
	private static final void dfs(int curr) {
		boolean flag;
		Node child;
		
		flag = true;
		for (child = adj[curr]; child != null; child = child.next) {
			if (child.idx == erased) {
				continue;
			}
			flag = false;
			dfs(child.idx);
		}
		if (flag) {
			cnt++;
		}
	}
	
	public static void main(String[] args) throws IOException {
		int n;
		int i;
		int v;
		int root;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		root = 0;
		adj = new Node[n];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			v = Integer.parseInt(st.nextToken());
			if (v == -1) {
				root = i;
				continue;
			}
			adj[v] = new Node(i, adj[v]);
		}
		erased = Integer.parseInt(br.readLine());
		cnt = 0;
		if (root != erased) {
			dfs(root);
		}
		System.out.print(cnt);
	}
}
