import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	private static int min, max;
	private static int[][] segmentTree;
	
	private static void update(int idx, int start, int end, int numIdx, int newNum) {
		if (segmentTree[idx][0] == 0) {
			segmentTree[idx][0] = newNum;
		}
		else {
			segmentTree[idx][0] = Math.min(segmentTree[idx][0], newNum);
		}
		segmentTree[idx][1] = Math.max(segmentTree[idx][1], newNum);
		if (start < end) {
			int mid = (start + end) / 2;
			if (numIdx <= mid) {
				update(idx * 2, start, mid, numIdx, newNum);
			}
			else {
				update(idx * 2 + 1, mid + 1, end, numIdx, newNum);
			}
		}
	}
	
	private static void findMinMax(int idx, int start, int end, int rangeStart, int rangeEnd) {
		if (start >= rangeStart && end <= rangeEnd) {
			min = Math.min(min, segmentTree[idx][0]);
			max = Math.max(max, segmentTree[idx][1]);
		}
		else if (!(start > rangeEnd || end < rangeStart)) {
			int mid = (start + end) / 2;
			findMinMax(idx * 2, start, mid, rangeStart, rangeEnd);
			findMinMax(idx * 2 + 1, mid + 1, end, rangeStart, rangeEnd);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str;
		StringTokenizer st;
		
		int n, m, i, a, b;
		
		str = br.readLine();
		st = new StringTokenizer(str);
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		segmentTree = new int[4 * n][2];
		
		for (i = 1; i <= n; i++) {
			str = br.readLine();
			update(1, 1, n, i, Integer.parseInt(str));
		}
		
		for (i = 0; i < m; i++) {
			str = br.readLine();
			st = new StringTokenizer(str);
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			min = 1000000001;
			max = 0;
			findMinMax(1, 1, n, a, b);
			sb.append(min).append(" ").append(max).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}