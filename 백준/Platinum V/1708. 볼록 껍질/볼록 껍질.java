import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	private static final long INF = Long.MAX_VALUE;
	
	private static class Point {
		long x, y;
		
		Point(long x, long y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static Point base;
	
	private static long ccw(Point p1, Point p2, Point p3) {
		return p1.x * p2.y + p2.x * p3.y + p3.x * p1.y - p2.x * p1.y - p3.x * p2.y - p1.x * p3.y;
	}
	
	private static long distance(Point p1, Point p2) {
		return (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
	}
	
	private static ArrayList<Point> convexHull(Point[] points) {
		int size, i;
		Point p1, p2, p3;
		Deque<Point> stack;
		
		size = points.length;
		stack = new ArrayDeque<>();
		p1 = points[0];
		p2 = points[1];
		stack.addLast(p1);
		for (i = 2; i <= size; i++) {
			p3 = points[i % size];
			while (!stack.isEmpty() && ccw(p1, p2, p3) <= 0) {
				p2 = stack.pollLast();
				p1 = stack.peekLast();
			}
			stack.addLast(p2);
			p1 = p2;
			p2 = p3;
		}
		return new ArrayList<>(stack);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, i;
		Point[] points;
		
		n = Integer.parseInt(br.readLine());
		points = new Point[n];
		base = new Point(INF, INF);
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			points[i] = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
			if (points[i].y < base.y || (points[i].y == base.y && points[i].x < base.x)) {
				base = points[i];
			}
		}
		Arrays.sort(points, new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				long dir = ccw(base, o1, o2);
				
				if (dir == 0) {
					return Long.compare(distance(base, o1), distance(base, o2));
				}
				return dir > 0 ? -1 : 1;
			}
		});
		System.out.print(convexHull(points).size());
	}
}
