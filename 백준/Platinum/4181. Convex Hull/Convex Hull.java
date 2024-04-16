import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	private static final long INF = Long.MAX_VALUE;
	private static final char N = 'N';
	
	private static class Point implements Comparable<Point> {
		long x, y;
		
		Point(long x, long y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int compareTo(Point o) {
			long val;
			
			val = ccw(base, this, o);
			if (val == 0) {
				return Long.compare(dist(base, this), dist(base, o));
			}
			return val > 0 ? -1 : 1;
		}
	}
	
	private static int n;
	private static Point base;
	
	private static long ccw(Point p1, Point p2, Point p3) {
		return (p2.x - p1.x) * (p3.y - p2.y) - (p3.x - p2.x) * (p2.y - p1.y);
	}
	
	private static long dist(Point p1, Point p2) {
		return (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int end, i;
		Point point;
		ArrayList<Point> points;
		
		n = Integer.parseInt(br.readLine());
		base = new Point(INF, INF);
		points = new ArrayList<>();
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			point = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
			if (st.nextToken().charAt(0) == N) {
				continue;
			}
			points.add(point);
			if (point.x < base.x || (point.x == base.x && point.y < base.y)) {
				base = point;
			}
		}
		Collections.sort(points);
		sb.append(points.size());
		for (end = points.size() - 2; ccw(base, points.get(points.size() - 1), points.get(end)) == 0; end--);
		for (i = 0; i <= end; i++) {
			sb.append('\n').append(points.get(i).x).append(' ').append(points.get(i).y);
		}
		for (i = points.size() - 1; i > end; i--) {
			sb.append('\n').append(points.get(i).x).append(' ').append(points.get(i).y);
		}
		System.out.print(sb);
	}
}
