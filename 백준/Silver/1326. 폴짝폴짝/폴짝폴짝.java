import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	
	private static int[] distance;
	private static boolean[] inQueue;
	private static ArrayList<ArrayList<Integer>> adj;
	
	private static int spfa(int a, int b) {
		int curr;
		Queue<Integer> q;
		
		Arrays.fill(distance, INF);
		distance[a] = 0;
		q = new ArrayDeque<>();
		q.add(a);
		inQueue[a] = true;
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
		return distance[b] == INF ? -1 : distance[b];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, num, a, b, i, j;
		
		n = Integer.parseInt(br.readLine());
		adj = new ArrayList<>();
		for (i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			num = Integer.parseInt(st.nextToken());
			for (j = i - num; j >= 0; j -= num) {
				adj.get(i).add(j);
			}
			for (j = i + num; j < n; j += num) {
				adj.get(i).add(j);
			}
		}
		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken()) - 1;
		b = Integer.parseInt(st.nextToken()) - 1;
		distance = new int[n];
		inQueue = new boolean[n];
		System.out.print(spfa(a, b));
	}
}
