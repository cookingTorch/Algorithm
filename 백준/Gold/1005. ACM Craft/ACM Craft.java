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
	private static int[] time, degree, distance;
	private static ArrayList<LinkedList<Integer>> adj;
	
	private static int topologicalSort(int dest) {
		int curr;
		Queue<Integer> q;
		
		distance = new int[n];
		q = new ArrayDeque<>();
		for (curr = 0; curr < n; curr++) {
			if (degree[curr] == 0) {
				if (curr == dest) {
					return time[curr];
				}
				q.add(curr);
			}
		}
		while (!q.isEmpty()) {
			curr = q.poll();
			for (int next : adj.get(curr)) {
				distance[next] = Math.max(distance[next], distance[curr] + time[curr]);
				if (--degree[next] == 0) {
					if (next == dest) {
						return distance[next] + time[next];
					}
					q.add(next);
				}
			}
		}
		return 0;
	}
	
	private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		int k, u, v, i;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		time = new int[n];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			time[i] = Integer.parseInt(st.nextToken());
		}
		adj = new ArrayList<>();
		for (i = 0; i < n; i++) {
			adj.add(new LinkedList<>());
		}
		degree = new int[n];
		for (i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken()) - 1;
			v = Integer.parseInt(st.nextToken()) - 1;
			adj.get(u).add(v);
			degree[v]++;
		}
		return topologicalSort(Integer.parseInt(br.readLine()) - 1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		
		t = Integer.parseInt(br.readLine());
		for (testCase = 0; testCase < t; testCase++) {
			sb.append(solution(br, st)).append('\n');
		}
		System.out.print(sb);
	}
}
