import java.util.ArrayDeque;

class Solution {
	private static final int FAIL = -1;

	public int solution(int[] queue1, int[] queue2) {
		int i;
		int len;
		int num;
		long sum;
		long dest;
		ArrayDeque<Integer> q1;
		ArrayDeque<Integer> q2;

		len = queue1.length;
		q1 = new ArrayDeque<>();
		dest = 0L;
		for (i = 0; i < len; i++) {
			dest += queue1[i];
			q1.addLast(queue1[i]);
		}
		sum = dest;
		q2 = new ArrayDeque<>();
		for (i = 0; i < len; i++) {
			dest += queue2[i];
			q2.addLast(queue2[i]);
		}
		if ((dest & 1) != 0) {
			return FAIL;
		}
		len *= 3;
		dest >>>= 1;
		for (i = 0; i <= len; i++) {
			if (sum > dest) {
				num = q1.pollFirst();
				q2.addLast(num);
				sum -= num;
			} else if (sum < dest) {
				num = q2.pollFirst();
				q1.addLast(num);
				sum += num;
			} else {
				break;
			}
		}
		return i > len ? FAIL : i;
	}
}
