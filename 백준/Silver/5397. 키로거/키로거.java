import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static int[] left, right;
	
	private static void solution(BufferedReader br, StringBuilder sb) throws IOException {
		int len, curr, prev, next, i;
		char[] str;

		str = br.readLine().toCharArray();
		len = str.length;
		left = new int[len + 1];
		right = new int[len + 1];
		left[len] = len;
		right[len] = len;
		curr = len;
		for (i = 0; i < len; i++) {
			switch (str[i]) {
			case '-':
				if (curr != len) {
					prev = left[curr];
					next = right[curr];
					right[prev] = next;
					left[next] = prev;
					curr = prev;
				}
				break;
			case '<':
				if (curr != len) {
					curr = left[curr];
				}
				break;
			case '>':
				if (right[curr] != len) {
					curr = right[curr];
				}
				break;
			default:
				next = right[curr];
				right[curr] = i;
				left[i] = curr;
				right[i] = next;
				left[next] = i;
				curr = i;
			}
		}
		for (curr = right[len]; curr != len; curr = right[curr]) {
			sb.append(str[curr]);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int t, testCase;

		left = new int[1000001];
		right = new int[1000001];
		t = Integer.parseInt(br.readLine());
		for (testCase = 0; testCase < t; testCase++) {
			solution(br, sb);
			sb.append('\n');
		}
		System.out.print(sb);
	}
}
