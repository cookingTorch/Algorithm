import java.util.PriorityQueue;

class Solution {
	public int solution(int n, int k, int[] enemy) {
		int i;
		int len;
		PriorityQueue<Integer> pq;

		len = enemy.length;
		pq = new PriorityQueue<>(len, (o1, o2) -> o2 - o1);
		for (i = 0; i < len; i++) {
			pq.offer(enemy[i]);
			if ((n -= enemy[i]) < 0) {
				if (--k >= 0) {
					n += pq.poll();
				} else {
					break;
				}
			}
		}
		return i;
	}
}
