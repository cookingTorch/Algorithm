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
	
	private static class Point implements Comparable<Point> {
		long x, y;
		boolean deleted;
		
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
		}
		stack = new Stack<>();
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
		StringTokenizer st;
		
		int n, i;
		Point point, prison;
		ArrayList<Point> points, hull, temp;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		prison = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		base = BASE;
		points = new ArrayList<>(n + 1);
		points.add(prison);
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			point = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
			points.add(point);
			if (point.y < base.y || (point.y == base.y && point.x < base.x)) {
				base = point;
			}
		}
		for (i = 0;; i++) {
			hull = convexHull(points);
			for (Point hullPoint : hull) {
				if (hullPoint == prison) {
					System.out.print(i);
					return;
				}
				hullPoint.deleted = true;
			}
			temp = new ArrayList<>();
			base = BASE;
			for (Point notHull : points) {
				if (notHull.deleted) {
					continue;
				}
				temp.add(notHull);
				if (notHull.y < base.y || (notHull.y == base.y && notHull.x < base.x)) {
					base = notHull;
				}
			}
			points = temp;
		}
	}
}
