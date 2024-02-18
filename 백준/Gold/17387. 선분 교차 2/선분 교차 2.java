import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static class Point {
		long x, y;
		
		Point(long x, long y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static long ccw(Point p1, Point p2, Point p3) {
		return Long.compare((p2.x - p1.x) * (p3.y - p2.y) - (p3.x - p2.x) * (p2.y - p1.y), 0);
	}
	
	private static int cross(Point a, Point b, Point c, Point d) {
		long c1, c2, c3, c4;
		
		if ((c1 = ccw(a, b, c)) * (c2 = ccw(a, b, d)) <= 0 && (c3 = ccw(c, d, a)) * (c4 = ccw(c, d, b)) <= 0) {
			if (c1 == 0 && c2 == 0 && c3 == 0 && c4 == 0) {
				if (Math.max(a.x, b.x) < Math.min(c.x, d.x) || Math.max(c.x, d.x) < Math.min(a.x, b.x)) {
					return 0;
				} else if (Math.max(a.y, b.y) < Math.min(c.y, d.y) || Math.max(c.y, d.y) < Math.min(a.y, b.y)) {
					return 0;
				}
			}
			return 1;
		} else {
			return 0;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		Point a, b, c, d;
		
		st = new StringTokenizer(br.readLine());
		a = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
		b = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
		st = new StringTokenizer(br.readLine());
		c = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
		d = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
		System.out.print(cross(a, b, c, d));
	}
}
