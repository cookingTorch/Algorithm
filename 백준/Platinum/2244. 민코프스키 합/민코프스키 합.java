import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	private static final long INF = Integer.MAX_VALUE;
	private static final Point BASE = new Point(INF, INF);
	
	private static final class Point implements Comparable<Point> {
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
	
	private static Point base;
	
	private static final long ccw(Point p1, Point p2, Point p3) {
		return (p2.x - p1.x) * (p3.y - p2.y) - (p2.y - p1.y) * (p3.x - p2.x);
	}
	
	private static final long dist(Point p1, Point p2) {
		return (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
	}
	
	private static final ArrayList<Point> convexHull(ArrayList<Point> points) {
		int i;
		Stack<Point> stack;
		
		Collections.sort(points);
		if (points.size() < 3) {
			return new ArrayList<>(points);
		}
		stack = new Stack<>();
		if (ccw(points.get(points.size() - 1), points.get(0), points.get(1)) == 0) {
			stack.add(points.get(0));
			stack.add(points.get(points.size() - 1));
			return new ArrayList<>(stack);
		}
		for (i = 0; i < points.size(); i++) {
			while (stack.size() > 1 && ccw(stack.get(stack.size() - 2), stack.get(stack.size() - 1), points.get(i)) <= 0) {
				stack.pop();
			}
			stack.add(points.get(i));
		}
		return new ArrayList<>(stack);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, i;
		long x, y;
		long[][] a;
		Point point;
		ArrayList<Point> points, hull;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		a = new long[n][2];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			a[i][0] = Long.parseLong(st.nextToken());
			a[i][1] = Long.parseLong(st.nextToken());
		}
		points = new ArrayList<>(n + m);
		base = BASE;
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			x = Long.parseLong(st.nextToken());
			y = Long.parseLong(st.nextToken());
			for (long[] xy : a) {
				point = new Point(x + xy[0], y + xy[1]);
				points.add(point);
				if (point.x < base.x || (point.x == base.x && point.y < base.y)) {
					base = point;
				}
			}
		}
		hull = convexHull(points);
		sb.append(hull.size());
		for (Point hullPoint : hull) {
			sb.append('\n').append(hullPoint.x).append(' ').append(hullPoint.y);
		}
		System.out.print(sb);
	}
}
