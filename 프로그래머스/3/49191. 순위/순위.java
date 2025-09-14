import java.util.ArrayDeque;

class Solution {
	private static final int THR = 64;

	private static final class Edge {
		int to;
		Edge next;

		Edge(int to, Edge next) {
			this.to = to;
			this.next = next;
		}
	}

	private static void topo(int n, Edge[] adj, int[] degree, ArrayDeque<Integer> q, long[][] parents) {
		int i;
		int to;
		int cur;
		Edge edge;

		for (i = 1; i <= n; i++) {
			if (degree[i] == 0) {
				q.addLast(i);
			}
		}
		while (!q.isEmpty()) {
			cur = q.pollFirst();
			if (cur < THR) {
				for (edge = adj[cur]; edge != null; edge = edge.next) {
					to = edge.to;
					parents[to][0] |= parents[cur][0] | 1L << cur;
					parents[to][1] |= parents[cur][1];
					if (--degree[to] == 0) {
						q.addLast(to);
					}
				}
			} else {
				for (edge = adj[cur]; edge != null; edge = edge.next) {
					to = edge.to;
					parents[to][0] |= parents[cur][0];
					parents[to][1] |= parents[cur][1] | 1L << cur - THR;
					if (--degree[to] == 0) {
						q.addLast(to);
					}
				}
			}
		}
	}

	public int solution(int n, int[][] results) {
		int u;
		int v;
		int i;
		int cnt;
		int[] in;
		int[] out;
		long[][] lower;
		long[][] higher;
		Edge[] forward;
		Edge[] backward;
		ArrayDeque<Integer> q;

		forward = new Edge[n + 1];
		backward = new Edge[n + 1];
		in = new int[n + 1];
		out = new int[n + 1];
		i = results.length;
		while (i-- > 0) {
			u = results[i][0];
			v = results[i][1];
			forward[u] = new Edge(v, forward[u]);
			backward[v] = new Edge(u, backward[v]);
			out[u]++;
			in[v]++;
		}
		higher = new long[n + 1][2];
		lower = new long[n + 1][2];
		q = new ArrayDeque<>();
		topo(n, forward, in, q, higher);
		topo(n, backward, out, q, lower);
		cnt = 0;
		for (i = 1; i <= n; i++) {
			if (Long.bitCount(higher[i][0]) + Long.bitCount(higher[i][1]) + Long.bitCount(lower[i][0]) + Long.bitCount(lower[i][1]) == n - 1) {
				cnt++;
			}
		}
		return cnt;
	}
}
