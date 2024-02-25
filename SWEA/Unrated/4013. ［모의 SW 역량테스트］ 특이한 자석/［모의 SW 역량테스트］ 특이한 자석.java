import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	private static final int MAG_NUM = 4;
	private static final int SIZE =  8;
	private static final int START = 0;
	private static final int LEFT = 6;
	private static final int RIGHT = 2;
	
	private static class Magnet {
		int idx, start, left, right;
		int[] poles;
		Magnet leftMag, rightMag;
		
		Magnet(StringTokenizer st) {
			int i;
			
			poles = new int[SIZE];
			for (i = 0; i < SIZE; i++) {
				poles[i] = Integer.parseInt(st.nextToken());
			}
			start = poles[START];
			left = poles[LEFT];
			right = poles[RIGHT];
		}
		
		void rotate(int dir, boolean toLeft, boolean toRight) {
			if (toLeft && leftMag != null && left != leftMag.right) {
				leftMag.rotate(-dir, true, false);
			}
			if (toRight && rightMag != null && right != rightMag.left) {
				rightMag.rotate(-dir, false, true);
			}
			idx = ((idx + SIZE) - dir) % SIZE;
			start = poles[idx];
			left = poles[(idx + LEFT) % SIZE];
			right = poles[(idx + RIGHT) % SIZE];
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
			score += magnets[i].start;
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
