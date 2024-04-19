import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	private static final long INF = Integer.MAX_VALUE;
	private static final double POS_INF = Double.POSITIVE_INFINITY;
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
		return (p2.x - p1.x) * (p3.y - p2.y) - (p3.x - p2.x) * (p2.y - p1.y);
	}
	
	private static final long ccw(Point p1, Point p2, Point p3, Point p4) {
		return (p2.x - p1.x) * (p4.y - p3.y) - (p4.x - p3.x) * (p2.y - p1.y);
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
	
	private static final double rotatingCalipers(ArrayList<Point> hull) {
		int i, j, ni, nj;
		double len, min;
		
		if (hull.size() < 3) {
			return Math.sqrt(dist(hull.get(0), hull.get(hull.size() - 1)));
		}
		len = Math.sqrt(dist(hull.get(0), hull.get(1)));
		min = POS_INF;
		for (i = 0, j = 1; i < hull.size(); i++) {
			ni = (i + 1) % hull.size();
			len -= Math.sqrt(dist(hull.get(i), hull.get(ni)));
			for (;;) {
				nj = (j + 1) % hull.size();
				if (ccw(hull.get(i), hull.get(ni), hull.get(j), hull.get(nj)) >= 0) {
					len += Math.sqrt(dist(hull.get(j), hull.get(nj)));
					j = nj;
				} else {
					break;
				}
			}
			min = Math.min(min, len);
		}
		return min;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, i;
		Point point;
		ArrayList<Point> points;
		
		n = Integer.parseInt(br.readLine());
		base = BASE;
		points = new ArrayList<>();
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			point = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			points.add(point);
			if (point.x < base.x || (point.x == base.x && point.y < base.y)) {
				base = point;
			}
		}
		System.out.print(rotatingCalipers(convexHull(points)));
	}
}
