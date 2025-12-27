import java.util.ArrayDeque;

class Solution {
	private static final int FAIL = -1;

	private static int end;
	private static boolean[] visited;
	private static ArrayDeque<Integer> q;

	private static boolean calc(int next) {
		if (next < end || visited[next]) {
			return false;
		}
		if (next == end) {
			return true;
		}
		q.addLast(next);
		visited[next] = true;
		return false;
	}

	public int solution(int x, int y, int n) {
		int cur;
		int dist;
		int size;

		if (x == y) {
			return 0;
		}
		end = x;
		q = new ArrayDeque<>();
		visited = new boolean[y + 1];
		dist = 0;
		q.addLast(y);
		while (!q.isEmpty()) {
			dist++;
			size = q.size();
			while (size-- > 0) {
				cur = q.pollFirst();
				if (calc(cur - n) || ((cur & 1) == 0 && calc(cur >> 1)) || ((cur % 3) == 0 && calc(cur / 3))) {
					return dist;
				}
			}
		}
		return FAIL;
	}
}
