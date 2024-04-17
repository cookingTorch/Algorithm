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
		double x, y;
		
		Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			double val;
			
			val = ccw(base, this, o);
			if (val == 0) {
				return Double.compare(dist(base, this), dist(base, o));
			}
			return val > 0 ? -1 : 1;
		}
	}
	
	private static Point base;
	
	private static final double ccw(Point p1, Point p2, Point p3) {
		return (p2.x - p1.x) * (p3.y - p2.y) - (p2.y - p1.y) * (p3.x - p2.x);
	}
	
	private static final double ccw(Point p1, Point p2, Point p3, Point p4) {
		return (p2.x - p1.x) * (p4.y - p3.y) - (p2.y - p1.y) * (p4.x - p3.x);
	}
	
	private static final double dist(Point p1, Point p2) {
		return (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
	}
	
	private static final double dist(Point p1, Point p2, Point p3) {
		double a, b, c;
		
		a = p2.y - p1.y;
		b = p1.x - p2.x;
		c = p2.x * p1.y - p1.x * p2.y;
		return Math.abs(a * p3.x + b * p3.y + c) / Math.sqrt(a * a + b * b);
	}
	
	private static final Point getFoot(Point p1, Point p2, Point p3) {
		double a, b, c, x, y;
		
		a = p2.y - p1.y;
		b = p1.x - p2.x;
		c = p2.x * p1.y - p1.x * p2.y;
		x = (b * b * p3.x - a * b * p3.y - a * c) / (a * a + b * b);
		y = (a * a * p3.y - a * b * p3.x - b * c) / (a * a + b * b);
		return new Point(x, y);
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
		int i, j, ni, nj, l, r, nl, nr;
		double min;
		Point foot;
		
		min = POS_INF;
		for (i = 0, j = 1; i < hull.size(); i++) {
			ni = (i + 1) % hull.size();
			for (;;) {
				nj = (j + 1) % hull.size();
				if (ccw(hull.get(i), hull.get(ni), hull.get(j), hull.get(nj)) > 0) {
					j = nj;
				} else {
					break;
				}
			}
			foot = getFoot(hull.get(i), hull.get(ni), hull.get(j));
			l = j;
			for (;;) {
				nl = (l + 1) % hull.size();
				if (ccw(foot, hull.get(j), hull.get(l), hull.get(nl)) > 0) {
					l = nl;
				} else {
					break;
				}
			}
			r = j;
			for (;;) {
				nr = (r + hull.size() - 1) % hull.size();
				if (ccw(foot, hull.get(j), hull.get(r), hull.get(nr)) < 0) {
					r = nr;
				} else {
					break;
				}
			}
			min = Math.min(min, 2 * (dist(hull.get(i), hull.get(ni), hull.get(j)) + dist(foot, hull.get(j), hull.get(l)) + dist(foot, hull.get(j), hull.get(r))));
		}
		return min;
	}
	
	private static final double solution(BufferedReader br, StringTokenizer st, int n) throws IOException {
		int i;
		Point point;
		ArrayList<Point> points;
		
		points = new ArrayList<>(n);
		base = BASE;
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			point = new Point(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
			points.add(point);
			if (point.x < base.x || (point.x == base.x && point.y < base.y)) {
				base = point;
			}
		}
		return rotatingCalipers(convexHull(points));
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		String str;
		
		while ((str = br.readLine()) != null) {
			sb.append(solution(br, st, Integer.parseInt(str))).append('\n');
		}
		System.out.print(sb);
	}
}
