import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution {
	private static final int INF = Integer.MAX_VALUE;
	private static final int MAX_PEOPLE = 10;
	private static final int MAX_STAIRS = 2;
	
	private static int peopleSize, stairsSize;
	private static int[] stairLen;
	private static int[][] people, stairs, dist;
	private static Deque<Integer> dq0, dq1;
	private static PriorityQueue<Integer> pq0, pq1;
	
	private static int calc(int bit) {
		int time, cnt, i;
		
		for (i = 0; i < peopleSize; i++, bit >>= 1) {
			if ((bit & 1) == 0) {
				pq0.add(dist[i][0] + 1);
			} else {
				pq1.add(dist[i][1] + 1);
			}
		}
		for (time = 0, cnt = 0; cnt < peopleSize; time++) {
			while (!dq0.isEmpty() && dq0.peek() == time) {
				dq0.poll();
				cnt++;
			}
			while (!dq1.isEmpty() && dq1.peek() == time) {
				dq1.poll();
				cnt++;
			}
			while (!pq0.isEmpty() && pq0.peek() <= time && dq0.size() < 3) {
				pq0.poll();
				dq0.add(time + stairLen[0]);
			}
			while (!pq1.isEmpty() && pq1.peek() <= time && dq1.size() < 3) {
				pq1.poll();
				dq1.add(time + stairLen[1]);
			}
		}
		return time - 1;
	}
	
	private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		int n, max, num, bit, ans, i, j;
		
		n = Integer.parseInt(br.readLine());
		peopleSize = 0;
		stairsSize = 0;
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < n; j++) {
				num = Integer.parseInt(st.nextToken());
				if (num != 0) {
					if (num == 1) {
						people[peopleSize][0] = i;
						people[peopleSize++][1] = j;
					} else {
						stairs[stairsSize][0] = i;
						stairs[stairsSize][1] = j;
						stairLen[stairsSize++] = num;
					}
				}
			}
		}
		for (i = 0; i < peopleSize; i++) {
			for (j = 0; j < stairsSize; j++) {
				dist[i][j] = Math.abs(people[i][0] - stairs[j][0]) + Math.abs(people[i][1] - stairs[j][1]);
			}
		}
		ans = INF;
		max = 1 << peopleSize;
		for (bit = 0; bit < max; bit++) {
			ans = Math.min(ans, calc(bit));
		}
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		
		people = new int[MAX_PEOPLE][2];
		stairs = new int[MAX_STAIRS][2];
		stairLen = new int[MAX_STAIRS];
		dist = new int[MAX_PEOPLE][MAX_STAIRS];
		dq0 = new ArrayDeque<>();
		dq1 = new ArrayDeque<>();
		pq0 = new PriorityQueue<>();
		pq1 = new PriorityQueue<>();
		t = Integer.parseInt(br.readLine());
		for (testCase = 1; testCase <= t; testCase++) {
			sb.append('#').append(testCase).append(' ').append(solution(br, st)).append('\n');
		}
		System.out.print(sb);
	}
}
