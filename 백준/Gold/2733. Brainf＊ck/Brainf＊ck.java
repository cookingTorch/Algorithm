import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
	private static final int MAX = 255;
	private static final int ZERO = 0;
	private static final int DIFF = 1 << 17;
	private static final int BUFF_SIZE = 32_768;
	private static final int INPUT_SIZE = 128_001;
	private static final int RIGHT = '>';
	private static final int LEFT = '<';
	private static final int PLUS = '+';
	private static final int MINUS = '-';
	private static final int PRINT = '.';
	private static final int OPEN = '[';
	private static final int CLOSE = ']';
	private static final int ANNOTATION = '%';
	private static final char NULL = 0;
	private static final char LINE_BREAK = '\n';
	private static final char[] PREFIX = "PROGRAM #".toCharArray();
	private static final char[] SUFFIX = {':', '\n'};
	private static final char[] COMPILE_ERROR = "COMPILE ERROR".toCharArray();
	private static final String END = "end";

	private static int[] input;
	private static char[] buff;
	private static ArrayDeque<Integer> stack;
	private static StringBuilder sb;
	private static BufferedReader br;

	private static final void run(int len) {
		int i;
		int ch;
		int ptr;

		ptr = 0;
		for (i = 0; i < BUFF_SIZE; i++) {
			buff[i] = NULL;
		}
		for (i = 1; i <= len; i++) {
			ch = input[i];
			if (ch == PLUS) {
				if (buff[ptr]++ == MAX) {
					buff[ptr] = 0;
				}
			} else if (ch == MINUS) {
				if (buff[ptr]-- == 0) {
					buff[ptr] = MAX;
				}
			} else if (ch == RIGHT) {
				if (++ptr == BUFF_SIZE) {
					ptr = 0;
				}
			} else if (ch == LEFT) {
				if (ptr-- == 0) {
					ptr = BUFF_SIZE - 1;
				}
			} else if (ch == PRINT) {
				sb.append(buff[ptr]);
			} else if (ch < 0) {
				if (buff[ptr] == ZERO) {
					i = -ch;
				}
			} else if (ch >= DIFF) {
				if (buff[ptr] != ZERO) {
					i = ch - DIFF;
				}
			}
		}
	}

	private static final void compile() throws IOException {
		int i;
		int ch;
		int idx;
		int len;
		int last;
		String str;

		idx = 1;
		for (str = br.readLine(); !str.equals(END); str = br.readLine()) {
			len = str.length();
			for (i = 0; i < len; i++) {
				ch = str.charAt(i);
				if (ch == OPEN) {
					stack.addFirst(idx++);
				} else if (ch == CLOSE) {
					if (stack.isEmpty()) {
						sb.append(COMPILE_ERROR);
						return;
					}
					last = stack.pollFirst();
					input[last] = -idx;
					input[idx++] = last + DIFF;
				} else if (ch == ANNOTATION) {
					break;
				} else {
					input[idx++] = ch;
				}
			}
		}
		if (!stack.isEmpty()) {
			stack.clear();
			sb.append(COMPILE_ERROR);
			return;
		}
		run(idx);
	}

	public static void main(String[] args) throws IOException {
		int t;
		int i;

		stack = new ArrayDeque<>();
		input = new int[INPUT_SIZE];
		buff = new char[BUFF_SIZE];
		br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		sb.append(PREFIX).append(1).append(SUFFIX);
		compile();
		for (i = 2; i <= t; i++) {
			sb.append(LINE_BREAK).append(PREFIX).append(i).append(SUFFIX);
			compile();
		}
		System.out.print(sb.toString());
	}
}
