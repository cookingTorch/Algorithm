import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	private static int[] energies;
	private static int[][] dp;
	private static ArrayList<ArrayList<Integer>> adj;
	
	private static int getDp(int node, int isIn, int parent) {
		if (dp[node][isIn] != -1) {
			return dp[node][isIn];
		}
		if (isIn == 1) {
			dp[node][isIn] = energies[node];
			for (int next : adj.get(node)) {
				if (next == parent) {
					continue;
				}
				dp[node][isIn] += getDp(next, 0, node);
			}
			return dp[node][isIn];
		}
		dp[node][isIn] = 0;
		for (int next : adj.get(node)) {
			if (next == parent) {
				continue;
			}
			dp[node][isIn] += Math.max(getDp(next, 0, node), getDp(next, 1, node));
		}
		return dp[node][isIn];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m, diff, i, j;
		HashSet<Integer> protons;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		energies = new int[n];
		for (i = 0; i < n; i++) {
			energies[i] = Integer.parseInt(br.readLine());
		}
		protons = new HashSet<>();
		for (i = 0; i < m; i++) {
			protons.add(Integer.parseInt(br.readLine()));
		}
		adj = new ArrayList<>(n);
		for (i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 0; i < n; i++) {
			for (j = i + 1; j < n; j++) {
				diff = Math.abs(energies[i] - energies[j]);
				if (protons.contains(diff)) {
					adj.get(i).add(j);
					adj.get(j).add(i);
				}
			}
		}
		dp = new int[n][2];
		for (i = 0; i < n; i++) {
			dp[i][0] = -1;
			dp[i][1] = -1;
		}
		System.out.print(Math.max(getDp(0, 0, -1), getDp(0, 1, -1)));
	}
}
