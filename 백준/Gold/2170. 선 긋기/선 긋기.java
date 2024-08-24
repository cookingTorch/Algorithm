import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	private static final int MIN = Integer.MIN_VALUE >> 1;

	private static final class Line implements Comparable<Line> {
		int start;
		int end;

		Line(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Line o) {
			return Integer.compare(this.start, o.start);
		}
	}

	public static void main(String[] args) throws IOException {
		int n;
		int start;
		int end;
		int ans;
		ArrayList<Line> lines;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		lines = new ArrayList<>(n);
		while (n-- > 0) {
			st = new StringTokenizer(br.readLine());
			lines.add(new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(lines);
		start = MIN;
		end = MIN;
		ans = 0;
		for (Line line : lines) {
			if (line.start > end) {
				ans += end - start;
				start = line.start;
			}
			end = Math.max(line.end, end);
		}
		System.out.print(ans + end - start);
	}
}
