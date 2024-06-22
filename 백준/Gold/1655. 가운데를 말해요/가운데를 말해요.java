import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int DIFF = 10_001;
	private static final int RANGE = 20_001;
	private static final char LINE_BREAK = '\n';
	
	private static int root;
	private static int[] ft;
	
	private static final void insert(int num) {
		int i;
		
		for (i = num; i <= root; i += i & -i) {
			ft[i]++;
		}
	}
	
	private static final int query(int mid) {
		int i;
		int idx;
		int prefix;
		
		idx = 0;
		prefix = 0;
		for (i = root; i > 0; i >>= 1) {
			if (prefix + ft[idx + i] < mid) {
				prefix += ft[idx += i];
			}
		}
		return idx + 1;
	}
	
	public static void main(String[] args) throws IOException {
		int n;
		int i;
		StringBuilder sb;
		BufferedReader br;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		for (root = 1; root < RANGE; root <<= 1);
		ft = new int[root + 1];
		sb = new StringBuilder();
		for (i = 1; i <= n; i++) {
			insert(Integer.parseInt(br.readLine()) + DIFF);
			sb.append(query((i + 1) >> 1) - DIFF).append(LINE_BREAK);
		}
		System.out.print(sb);
	}
}
