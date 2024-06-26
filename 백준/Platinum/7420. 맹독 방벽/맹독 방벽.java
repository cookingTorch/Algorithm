import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	private static final long INF = Long.MAX_VALUE;
	
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
	
	private static ArrayList<Point> convexHull(ArrayList<Point> points) {
		int i;
		Stack<Point> stack;
		
		Collections.sort(points);
		stack = new Stack<>();
		stack.add(base);
		for (i = 0; i < n; i++) {
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
		
		int i;
		double l, length;
		Point point;
		ArrayList<Point> points, hull;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Double.parseDouble(st.nextToken());
		base = new Point(INF, INF);
		points = new ArrayList<>();
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			point = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
			points.add(point);
			if (point.y < base.y || (point.y == base.y && point.x < base.x)) {
				base = point;
			}
		}
		hull = convexHull(points);
		length = Math.sqrt(dist(hull.get(0), hull.get(hull.size() - 1)));
		for (i = 1; i < hull.size(); i++) {
			length += Math.sqrt(dist(hull.get(i - 1), hull.get(i)));
		}
		length += 2 * l * Math.PI;
		System.out.print(Math.round(length));
	}
}
