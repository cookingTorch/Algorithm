import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	private static final long MAX = Long.MAX_VALUE;
	private static final double INF = Double.POSITIVE_INFINITY;
	
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
			stack.push(points.get(i));
		}
		return new ArrayList<>(stack);
	}
	
	private static double getSize(ArrayList<Point> hull) {
		int i, j;
		double min, max, a, b, c;
		Point p1, p2, p3;
		
		min = INF;
		for (i = 0; i < hull.size(); i++) {
			p1 = hull.get(i);
			p2 = hull.get((i + 1) % hull.size());
			a = p2.y - p1.y;
			b = p1.x - p2.x;
			c = p2.x * p1.y - p1.x * p2.y;
			max = 0;
			for (j = (i + 2) % hull.size(); j != i; j = (j + 1) % hull.size()) {
				p3 = hull.get(j);
				max = Math.max(max, Math.abs(a * p3.x + b * p3.y + c) / Math.sqrt(a * a + b * b));
			}
			min = Math.min(min, max);
		}
		return min;
	}
	
	private static String solution(BufferedReader br, StringTokenizer st) throws IOException {
		int i;
		Point point;
		ArrayList<Point> points;
		
		points = new ArrayList<>();
		base = new Point(MAX, MAX);
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			point = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
			points.add(point);
			if (point.y < base.y || (point.y == base.y && point.x < base.x)) {
				base = point;
			}
		}
		return String.format("%.2f", Math.ceil(getSize(convexHull(points)) * 100) / 100);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int testCase;
		
		for (n = Integer.parseInt(br.readLine()), testCase = 1; n != 0; n = Integer.parseInt(br.readLine()), testCase++) {
			sb.append("Case ").append(testCase).append(": ").append(solution(br, st)).append('\n');
		}
		System.out.print(sb);
	}
}
