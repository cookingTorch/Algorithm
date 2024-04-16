import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	private static final long INF = Long.MAX_VALUE;
	private static final String YES = "YES\n";
	private static final String NO = "NO\n";
	private static final Point BASE = new Point(INF, INF);
	
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
	
	private static Point base;
	
	private static long ccw(Point p1, Point p2, Point p3) {
		return (p2.x - p1.x) * (p3.y - p2.y) - (p3.x - p2.x) * (p2.y - p1.y);
	}
	
	private static long dist(Point p1, Point p2) {
		return (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
	}
	
	private static ArrayList<Point> convexHull(ArrayList<Point> points) {
		int i;
		Stack<Point> stack;
		
		Collections.sort(points);
		if (points.size() < 3) {
			return new ArrayList<>(points);
		} else if (ccw(points.get(points.size() - 1), points.get(0), points.get(1)) == 0) {
			stack = new Stack<>();
			stack.add(points.get(0));
			stack.add(points.get(points.size() - 1));
			return new ArrayList<>(stack);
		}
		stack = new Stack<>();
		stack.add(points.get(0));
		for (i = 0; i < points.size(); i++) {
			while (stack.size() > 1 && ccw(stack.get(stack.size() - 2), stack.get(stack.size() - 1), points.get(i)) <= 0) {
				stack.pop();
			}
			stack.add(points.get(i));
		}
		return new ArrayList<>(stack);
	}
	
	private static boolean crossed(Point a, Point b, Point c, Point d) {
		long c1, c2, c3, c4;
		
		if ((c1 = ccw(a, b, c)) * (c2 = ccw(a, b, d)) <= 0 && (c3 = ccw(c, d, a)) * (c4 = ccw(c, d, b)) <= 0) {
			if (c1 == 0 && c2 == 0 && c3 == 0 && c4 == 0) {
				if (Math.max(a.x, b.x) < Math.min(c.x, d.x) || Math.max(c.x, d.x) < Math.min(a.x, b.x)) {
					return false;
				}
				if (Math.max(a.y, b.y) < Math.min(c.y, d.y) || Math.max(c.y, d.y) < Math.min(a.y, b.y)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	private static boolean solution(BufferedReader br, StringTokenizer st) throws IOException {
		int n, m, i, j;
		Point point;
		ArrayList<Point> blackPoints, blackHull, whitePoints, whiteHull, points, hull;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		base = BASE;
		blackPoints = new ArrayList<>(n);
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			point = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
			blackPoints.add(point);
			if (point.y < base.y || (point.y == base.y && point.x < base.x)) {
				base = point;
			}
		}
		blackHull = convexHull(blackPoints);
		base = BASE;
		whitePoints = new ArrayList<>(m);
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			point = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
			whitePoints.add(point);
			if (point.y < base.y || (point.y == base.y && point.x < base.x)) {
				base = point;
			}
		}
		whiteHull = convexHull(whitePoints);
		for (i = 0; i < blackHull.size(); i++) {
			for (j = 0; j < whiteHull.size(); j++) {
				if (crossed(blackHull.get(i), blackHull.get((i + 1) % blackHull.size()), whiteHull.get(j), whiteHull.get((j + 1) % whiteHull.size()))) {
					return false;
				}
			}
		}
		base = BASE;
		points = new ArrayList<>(blackHull.size() + whiteHull.size());
		for (Point black : blackHull) {
			points.add(black);
			if (black.y < base.y || (black.y == base.y && black.x < base.x)) {
				base = black;
			}
		}
		for (Point white : whiteHull) {
			points.add(white);
			if (white.y < base.y || (white.y == base.y && white.x < base.x)) {
				base = white;
			}
		}
		hull = convexHull(points);
		if (hull.size() == blackHull.size()) {
			for (i = 0; i < hull.size(); i++) {
				if (hull.get(i) != blackHull.get(i)) {
					break;
				}
			}
			if (i == hull.size()) {
				return false;
			}
		}
		if (hull.size() == whiteHull.size()) {
			for (i = 0; i < hull.size(); i++) {
				if (hull.get(i) != whiteHull.get(i)) {
					break;
				}
			}
			if (i == hull.size()) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		
		t = Integer.parseInt(br.readLine());
		for (testCase = 0; testCase < t; testCase++) {
			sb.append(solution(br, st) ? YES : NO);
		}
		System.out.print(sb);
	}
}
