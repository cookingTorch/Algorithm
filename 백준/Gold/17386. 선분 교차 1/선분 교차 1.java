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
		if (ccw(a, b, c) * ccw(a, b, d) <= 0 && ccw(c, d, a) * ccw(c, d, b) <= 0) {
			System.out.print('1');
		} else {
			System.out.print('0');
		}
	}
}
