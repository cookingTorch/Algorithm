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
	private static Node[] adj;
	
	private static boolean isCovered(int node, int parent) {
		boolean cover;
		Node child;
		
		cover = false;
		for (child = adj[node]; child != null; child = child.next) {
			if (child.idx != parent && !isCovered(child.idx, node)) {
				cover = true;
			}
		}
		if (cover) {
			cnt++;
		}
		return cover;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, u, v, i;
		
		n = Integer.parseInt(br.readLine());
		adj = new Node[n + 1];
		for (i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			adj[u] = new Node(v, adj[u]);
			adj[v] = new Node(u, adj[v]);
		}
		cnt = 0;
		isCovered(1, 0);
		System.out.print(cnt);
	}
}
