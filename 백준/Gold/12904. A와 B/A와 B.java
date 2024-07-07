import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final char A = 'A';
	private static final char B = 'B';
	private static final char[] ONE = {'1'};
	private static final char[] ZERO = {'0'};
	
	private static boolean flag;
	
	public static void main(String[] args) throws IOException {
		int i;
		int sLen;
		int tLen;
		int left;
		int right;
		String s;
		String t;
		BufferedReader br;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		s = br.readLine();
		t = br.readLine();
		sLen = s.length();
		tLen = t.length();
		if (tLen < sLen) {
			System.out.print(ZERO);
			return;
		}
		left = 0;
		right = tLen - 1;
		flag = true;
		for (; tLen > sLen; tLen--) {
			if (flag) {
				if (t.charAt(right) == A) {
				} else if (t.charAt(right) == B) {
					flag = false;
				} else {
					System.out.print(ZERO);
					return;
				}
				right--;
			} else {
				if (t.charAt(left) == A) {
				} else if (t.charAt(left) == B) {
					flag = true;
				} else {
					System.out.print(ZERO);
					return;
				}
				left++;
			}
		}
		if (flag) {
			for (i = 0; i != sLen; i++) {
				if (s.charAt(i) != t.charAt(left + i)) {
					System.out.print(ZERO);
					return;
				}
			}
			System.out.print(ONE);
		} else {
			for (i = 0; i != sLen; i++) {
				if (s.charAt(i) != t.charAt(right - i)) {
					System.out.print(ZERO);
					return;
				}
			}
			System.out.print(ONE);
		}
	}
}
