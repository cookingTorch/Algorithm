import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	private static final int MAG_NUM = 4;
	private static final int SIZE = 8;
	private static final int FIRST = 1 << (SIZE - 1);
	private static final int LAST = 1;
	private static final int LEFT = 1 << 1;
	private static final int RIGHT = 1 << 5;
	private static final int N = 0;
	private static final int S = 1;
	
	private static class Magnet {
		int poles, first, last;
		boolean left, right;
		Magnet leftMag, rightMag;
		
		Magnet(StringTokenizer st) {
			int i;
			
			for (i = 0; i < SIZE; i++) {
				poles <<= 1;
				poles += Integer.parseInt(st.nextToken());
			}
			first = (poles & FIRST) == 0 ? N : S;
			last = (poles & LAST) == 0 ? N : S;
			left = (poles & LEFT) == 0;
			right = (poles & RIGHT) == 0;
		}
		
		void rotate(int dir, boolean toLeft, boolean toRight) {
			if (toLeft && leftMag != null && left != leftMag.right) {
				leftMag.rotate(-dir, true, false);
			}
			if (toRight && rightMag != null && right != rightMag.left) {
				rightMag.rotate(-dir, false, true);
			}
			if (dir == 1) {
				poles >>= 1;
				poles |= last << (SIZE - 1);
			} else {
				poles <<= 1;
				poles |= first;
			}
			first = (poles & FIRST) == 0 ? N : S;
			last = (poles & LAST) == 0 ? N : S;
			left = (poles & LEFT) == 0;
			right = (poles & RIGHT) == 0;
		}
	}
	
	private static Magnet[] magnets;
	
	private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		int k, score, i;
		Magnet prev;
		
		k = Integer.parseInt(br.readLine());
		prev = magnets[0] = new Magnet(new StringTokenizer(br.readLine()));
		for (i = 1; i < MAG_NUM; i++) {
			magnets[i] = new Magnet(new StringTokenizer(br.readLine()));
			prev.rightMag = magnets[i];
			magnets[i].leftMag = prev;
			prev = magnets[i];
		}
		for (i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			magnets[Integer.parseInt(st.nextToken()) - 1].rotate(Integer.parseInt(st.nextToken()), true, true);
		}
		score = 0;
		for (i = MAG_NUM - 1; i >= 0; i--) {
			score <<= 1;
			score += magnets[i].first;
		}
		return score;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		
		magnets = new Magnet[MAG_NUM];
		t = Integer.parseInt(br.readLine());
		for (testCase = 1; testCase <= t; testCase++) {
			sb.append('#').append(testCase).append(' ').append(solution(br, st)).append('\n');
		}
		System.out.print(sb);
	}
}
