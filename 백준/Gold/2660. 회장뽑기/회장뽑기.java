import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	
	private static int[] distance;
	private static boolean[] inQueue;
	private static Queue<Integer> q;
	private static ArrayList<ArrayList<Integer>> adj;
	
	private static int spfa(int start) {
		int curr, max;
		
		Arrays.fill(distance, INF);
		distance[start] = 0;
		q.clear();
		q.add(start);
		while (!q.isEmpty()) {
			curr = q.poll();
			inQueue[curr] = false;
			for (int next : adj.get(curr)) {
				if (distance[curr] + 1 < distance[next]) {
					distance[next] = distance[curr] + 1;
					if (!inQueue[next]) {
						q.add(next);
						inQueue[next] = true;
					}
				}
			}
		}
		max = 0;
		for (int dist : distance) {
			max = Math.max(max, dist);
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, a, b, min, dist, i;
		ArrayList<Integer> ans;
		
		n = Integer.parseInt(br.readLine());
		adj = new ArrayList<>();
		for (i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}
		while (true) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken()) - 1;
			b = Integer.parseInt(st.nextToken()) - 1;
			if (a == -2) {
				break;
			}
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		min = INF;
		ans = new ArrayList<>();
		distance = new int[n];
		inQueue = new boolean[n];
		q = new LinkedList<>();
		for (i = 0; i < n; i++) {
			dist = spfa(i);
			if (dist < min) {
				min = dist;
				ans.clear();
				ans.add(i);
			} else if (dist == min) {
				ans.add(i);
			}
		}
		sb.append(min).append(' ').append(ans.size()).append('\n');
		for (int num : ans) {
			sb.append(num + 1).append(' ');
		}
		System.out.print(sb);
	}
}
