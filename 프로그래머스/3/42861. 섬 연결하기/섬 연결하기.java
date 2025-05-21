import java.util.Arrays;

class Solution {
	private int[] roots;

	private int find(int v) {
		if (roots[v] <= 0) {
			return v;
		}
		return roots[v] = find(roots[v]);
	}

	private boolean union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u == v) {
			return false;
		}
		if (roots[u] > roots[v]) {
			roots[u] = v;
		} else {
			if (roots[u] == roots[v]) {
				roots[u]--;
			}
			roots[v] = u;
		}
		return true;
	}

	public int solution(int n, int[][] costs) {
		int i;
		int cnt;
		int sum;
		int[] cost;

		Arrays.sort(costs, 0, costs.length, (o1, o2) -> o1[2] - o2[2]);
		roots = new int[n + 1];
		i = 0;
		cnt = 1;
		sum = 0;
		while (cnt < n) {
			cost = costs[i++];
			if (union(cost[0] + 1, cost[1] + 1)) {
				sum += cost[2];
				cnt++;
			}
		}
		return sum;
	}
}
