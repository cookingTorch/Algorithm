import java.util.Arrays;

class Solution {
	private static final int MIN = Integer.MIN_VALUE;
	
	public int solution(int[][] routes) {
		int i;
		int len;
		int cnt;
		int camera;

		Arrays.sort(routes, (o1, o2) -> o1[1] - o2[1]);
		len = routes.length;
		camera = MIN;
		cnt = 0;
		for (i = 0; i < len; i++) {
			if (camera < routes[i][0]) {
				camera = routes[i][1];
				cnt++;
			}
		}
		return cnt;
	}
}
