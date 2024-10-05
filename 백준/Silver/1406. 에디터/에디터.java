import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int NIL = 0;
	private static final int MAX_LEN = 600_001;
	private static final int LINE_BREAK = '\n';
	private static final int L = 'L';
	private static final int D = 'D';
	private static final int B = 'B';
	private static final int P = 'P';

	public static void main(String[] args) throws IOException {
		int m;
		int idx;
		int pos;
		int size;
		int[] left;
		int[] right;
		char[] str;
		char[] ans;
		BufferedReader br;

		br = new BufferedReader(new InputStreamReader(System.in));
		str = new char[MAX_LEN];
		left = new int[MAX_LEN];
		right = new int[MAX_LEN];
		pos = NIL;
		while ((str[++pos] = (char) br.read()) != LINE_BREAK);
		size = --pos;
		for (idx = NIL; idx < pos; idx++) {
			left[idx + 1] = idx;
			right[idx] = idx + 1;
		}
		left[NIL] = pos;
		right[pos] = NIL;
		m = Integer.parseInt(br.readLine());
		while (m-- > 0) {
			switch (br.read()) {
				case L:
					if (pos != NIL) {
						pos = left[pos];
					}
					break;
				case D:
					if ((pos = right[pos]) == NIL) {
						pos = left[NIL];
					}
					break;
				case B:
					if (pos != NIL) {
						left[right[pos]] = left[pos];
						right[left[pos]] = right[pos];
						pos = left[pos];
						size--;
					}
					break;
				case P:
					br.read();
					str[++idx] = (char) br.read();
					left[right[pos]] = idx;
					right[idx] = right[pos];
					left[idx] = pos;
					right[pos] = idx;
					pos = idx;
					size++;
					break;
			}
			br.read();
		}
		idx = 0;
		ans = new char[size];
		for (pos = right[NIL]; pos != NIL; pos = right[pos]) {
			ans[idx++] = str[pos];
		}
		System.out.print(ans);
	}
}
