import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int n;
	private static int[] degree;
	private static ArrayList<LinkedList<Integer>> adj;
	
	private static void bfs(StringBuilder sb) {
		int curr, i;
		Queue<Integer> q;
		
		q = new ArrayDeque<>();
		for (i = 0; i < n; i++) {
			if (degree[i] == 0) {
				q.add(i);
			}
		}
		while (!q.isEmpty()) {
			curr = q.poll();
			sb.append(curr + 1).append(' ');
			for (int next : adj.get(curr)) {
				if (--degree[next] == 0) {
					q.add(next);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int m, student, i;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		adj = new ArrayList<>();
		for (i = 0; i < n; i++) {
			adj.add(new LinkedList<>());
		}
		degree = new int[n];
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			adj.get(Integer.parseInt(st.nextToken()) - 1).add(student = Integer.parseInt(st.nextToken()) - 1);
			degree[student]++;
		}
		bfs(sb);
		System.out.print(sb);
	}
}
