import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final char SPACE = ' ';

	public static void main(String[] args) throws IOException {
		int n;
		int max;
		int left;
		int right;
		int grade;
		int lenLeft;
		int lenRight;
		int prevLeft;
		int prevRight;
		int prevLenLeft;
		int prevLenRight;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ", false);
		prevLeft = Integer.parseInt(st.nextToken());
		prevRight = Integer.parseInt(st.nextToken());
		grade = Math.min(prevLeft, prevRight);
		for (prevLenLeft = prevLenRight = max = 1; --n > 0; prevLenLeft = lenLeft, prevLenRight = lenRight) {
			st = new StringTokenizer(br.readLine(), " ", false);
			left = Integer.parseInt(st.nextToken());
			right = Integer.parseInt(st.nextToken());
			if (left == prevLeft) {
				lenLeft = prevLenLeft + 1;
			} else if (left == prevRight) {
				lenLeft = prevLenRight + 1;
			} else {
				lenLeft = 1;
			}
			if (right == prevRight) {
				lenRight = prevLenRight + 1;
			} else if (right == prevLeft) {
				lenRight = prevLenLeft + 1;
			} else {
				lenRight = 1;
			}
			if (lenLeft > max) {
				grade = left;
				max = lenLeft;
			} else if (lenLeft == max) {
				grade = Math.min(grade, left);
			}
			if (lenRight > max) {
				grade = right;
				max = lenRight;
			} else if (lenRight == max) {
				grade = Math.min(grade, right);
			}
			prevLeft = left;
			prevRight = right;
		}
		sb = new StringBuilder();
		System.out.print(sb.append(max).append(SPACE).append(grade).toString());
	}
}
