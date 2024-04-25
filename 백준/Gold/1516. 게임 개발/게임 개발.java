import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	private static final int END = -1;
	
	private static final class Node {
		int idx;
		Node next;
		
		Node(int idx, Node next) {
			this.idx = idx;
			this.next = next;
		}
	}
	
	private static int n;
	private static int[] ans;
	private static int[] time;
	private static int[] degree;
	private static Node[] adj;
	private static BufferedReader br;
	
	private static final void inputAdj() throws IOException {
		int i;
		int parent;
		StringTokenizer st;
		
		adj = new Node[n + 1];
		degree = new int[n + 1];
		time = new int[n + 1];
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			while ((parent = Integer.parseInt(st.nextToken())) != END) {
				adj[parent] = new Node(i, adj[parent]);
				degree[i]++;
			}
		}
	}
	
	private static final void dfs(int curr) {
		Node next;
		
		for (next = adj[curr]; next != null; next = next.next) {
			ans[next.idx] = Math.max(ans[next.idx], ans[curr]);
			if (--degree[next.idx] == 0) {
				ans[next.idx] += time[next.idx];
				dfs(next.idx);
			}
		}
	}
	
	private static final void topoSort() {
		int i;
		ArrayDeque<Integer> q;
		
		ans = new int[n + 1];
		q = new ArrayDeque<>();
		for (i = 1; i <= n; i++) {
			if (degree[i] == 0) {
				ans[i] = time[i];
				q.addLast(i);
			}
		}
		while (!q.isEmpty()) {
			dfs(q.pollFirst());
		}
	}
	
	private static final void output() {
		int i;
		StringBuilder sb;
		
		sb = new StringBuilder();
		for (i = 1; i <= n; i++) {
			sb.append(ans[i]).append('\n');
		}
		System.out.print(sb);
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		inputAdj();
		topoSort();
		output();
	}
}
