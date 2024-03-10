import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	private static final String YES = "YES\n";
	private static final String NO = "NO\n";
	
	private static class Edge {
		int to, weight;
		
		Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
	
	private static int[] distance, cnt;
	private static boolean[] inQueue;
	private static Queue<Integer> q;
	private static ArrayList<LinkedList<Edge>> adj;
	
	private static boolean spfa(int start, int v) {
		int curr;
		
		distance[start] = 0;
		q.add(start);
		while (!q.isEmpty()) {
			curr = q.poll();
			inQueue[curr] = false;
			for (Edge edge : adj.get(curr)) {
				if (distance[curr] + edge.weight < distance[edge.to]) {
					distance[edge.to] = distance[curr] + edge.weight;
					cnt[edge.to] = cnt[curr] + 1;
					if (cnt[edge.to] > v) {
						return true;
					}
					if (!inQueue[edge.to]) {
						q.add(edge.to);
					}
				}
			}
		}
		return false;
	}
	
	private static boolean solution(BufferedReader br, StringTokenizer st) throws IOException {
		int n, m, w, s, e, t, i;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		adj = new ArrayList<>();
		for (i = 0; i <= n; i++) {
			adj.add(new LinkedList<>());
		}
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			adj.get(s).add(new Edge(e, t));
			adj.get(e).add(new Edge(s, t));
		}
		for (i = 0; i < w; i++) {
			st = new StringTokenizer(br.readLine());
			adj.get(Integer.parseInt(st.nextToken())).add(new Edge(Integer.parseInt(st.nextToken()), -Integer.parseInt(st.nextToken())));
		}
		distance = new int[n + 1];
		inQueue = new boolean[n + 1];
		q = new ArrayDeque<>();
		for (i = 1; i <= n; i++) {
			Arrays.fill(distance, INF);
			cnt = new int[n + 1];
			if (spfa(i, n)) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		
		t = Integer.parseInt(br.readLine());
		for (testCase = 0; testCase < t; testCase++) {
			sb.append(solution(br, st) ? YES : NO);
		}
		System.out.print(sb);
	}
}
