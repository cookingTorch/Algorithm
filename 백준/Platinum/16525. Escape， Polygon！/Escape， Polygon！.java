import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static final class Point {
		long x;
		long y;
		
		Point(long x, long y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static BufferedReader br;
	
	private static final long ccw(Point p1, Point p2, Point p3, Point p4) {
		return (p2.x - p1.x) * (p4.y - p3.y) - (p2.y - p1.y) * (p4.x - p3.x);
	}
	
	private static ArrayList<Point> inputPoints() throws IOException {
		int n;
		int i;
		Point point;
		ArrayList<Point> points;
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		points = new ArrayList<>(n);
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			point = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
			points.add(point);
		}
		return points;
	}
	
	private static final long rotatingCalipers(ArrayList<Point> hull) {
		int i;
		int j;
		int ni;
		int nj;
		int pj;
		long cnt;
		long dist;
		
		cnt = 0;
		for (i = 0, j = 1; i < hull.size(); i++) {
			ni = (i + 1) % hull.size();
			for (;;) {
				nj = (j + 1) % hull.size();
				if (ccw(hull.get(i), hull.get(ni), hull.get(j), hull.get(nj)) >= 0) {
					j = nj;
				} else {
					break;
				}
			}
			pj = (j + hull.size() - 1) % hull.size();
			if (pj >= ni) {
				dist = pj - ni;
			} else {
				dist = hull.size() - (ni - pj);
			}
			cnt += (dist + 1) * dist >> 1;
		}
		return (long) hull.size() * (hull.size() - 1) * (hull.size() - 2) / 6 - cnt;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print(rotatingCalipers(inputPoints()));
	}
}
