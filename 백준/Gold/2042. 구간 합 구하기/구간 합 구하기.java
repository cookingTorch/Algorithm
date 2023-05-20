import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	private static long sum;
	private static long[] segmentTree;
	
	private static void update(int idx, int start, int end, int numIdx, long change) {
		if (numIdx >= start && numIdx <= end) {
			segmentTree[idx] += change;
			if (end > start) {
				int mid = (start + end) / 2;
				update(idx * 2, start, mid, numIdx, change);
				update(idx * 2 + 1, mid + 1, end, numIdx, change);
			}
		}
	}
	
	private static void findSum(int idx, int start, int end, int rangeStart, int rangeEnd) {
		if (start >= rangeStart && end <= rangeEnd) {
			sum += segmentTree[idx];
		}
		else if (start <= rangeEnd && end >= rangeStart) {
			int mid = (start + end) / 2;
			findSum(idx * 2, start, mid, rangeStart, rangeEnd);
			findSum(idx * 2 + 1, mid + 1, end, rangeStart, rangeEnd);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str;
		StringTokenizer st;
		
		int n, m, k, i, a, b;
		long c, change;
		long[] numbers;
		
		str = br.readLine();
		st = new StringTokenizer(str);
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		numbers = new long[n + 1];
		segmentTree = new long[4 * n];
		
		for (i = 1; i <= n; i++) {
			str = br.readLine();
			numbers[i] = Long.parseLong(str);
			update(1, 1, n, i, numbers[i]);
		}
		
		for (i = 0; i < m + k; i++) {
			str = br.readLine();
			st = new StringTokenizer(str);
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Long.parseLong(st.nextToken());
			if (a == 1) {
				change = c - numbers[b];
				numbers[b] = c;
				update(1, 1, n, b, change);
			}
			else {
				sum = 0;
				findSum(1, 1, n, b, (int) c);
				sb.append(sum).append("\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}