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
			return val > 0 ? 1 : -1;
		}
	}
	
	private static int n;
	private static Point base;
	private static Point[] result;
	private static ArrayList<Point> points;
	
	private static long ccw(Point p1, Point p2, Point p3) {
		return (p2.x - p1.x) * (p3.y - p2.y) - (p3.x - p2.x) * (p2.y - p1.y);
	}
	
	private static long ccw(Point p1, Point p2, Point p3, Point p4) {
		return (p2.x - p1.x) * (p4.y - p3.y) - (p4.x - p3.x) * (p2.y - p1.y);
	}

    private static long dist(Point p1, Point p2) {
        return (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
    }
	
	private static ArrayList<Point> convexHull(ArrayList<Point> points) {
    	int size, i;
        Stack<Point> stack;

        Collections.sort(points);
        stack = new Stack<>();
        stack.add(base);
        size = 1;
        for (i = 1; i < n; i++) {
            while (size > 1 && (ccw(stack.get(size - 2), stack.get(size - 1), points.get(i)) >= 0)) {
                stack.pop();
                size--;
            }
            stack.add(points.get(i));
            size++;
        }
        return new ArrayList<>(stack);
    }

    private static void rotatingCalipers(ArrayList<Point> hull) {
    	int i, j, ni, nj;
    	long max, distance, val;

        max = 0;
        for (i = 0, j = 1; i < hull.size(); i++) {
            ni = (i + 1) % hull.size();
            for (;;) {
                nj = (j + 1) % hull.size();
                val = ccw(hull.get(i), hull.get(ni), hull.get(j), hull.get(nj));
                if (val < 0) {
                    j = nj;
                } else {
                    break;
                }
            }
            distance = dist(hull.get(i), hull.get(j));
            if (distance > max) {
                max = distance;
                result[0] = hull.get(i);
                result[1] = hull.get(j);
            }
        }
    }
    
    private static void solution(BufferedReader br, StringBuilder sb, StringTokenizer st) throws IOException {
    	int i;
    	long x, y;
    	Point point;
    	
    	st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        points = new ArrayList<>();
        base = new Point(INF, INF);
        for (i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            x = Long.parseLong(st.nextToken());
            y = Long.parseLong(st.nextToken());
            point = new Point(x, y);
            points.add(point);
            if (y < base.y) {
            	base = point;
            } else if (y == base.y && x < base.x) {
            	base = point;
            }
        }
        rotatingCalipers(convexHull(points));
        sb.append(result[0].x).append(' ').append(result[0].y).append(' ').append(result[1].x).append(' ').append(result[1].y).append('\n');
    }
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        int t, testCase;

        result = new Point[2];
        t = Integer.parseInt(br.readLine());
        for (testCase = 0; testCase < t; testCase++) {
        	solution(br, sb, st);
        }
        System.out.print(sb);
    }
}