import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;
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
	private static Point[] result;
	private static ArrayList<Point> points;

    private static Point[] rotatingCalipers(ArrayList<Point> hull) {
    	int i, j, ni, nj;
    	long max, distance, ix, iy, jx, jy;

        max = 0;
        for (i = 0, j = 1; i < hull.size(); i++) {
            ni = (i + 1) % hull.size();
            for (;;) {
                nj = (j + 1) % hull.size();
                ix = hull.get(ni).x - hull.get(i).x;
                iy = hull.get(ni).y - hull.get(i).y;
                jx = hull.get(nj).x - hull.get(j).x;
                jy = hull.get(nj).y - hull.get(j).y;
                long ccw = ccw(new Point(0, 0), new Point(ix, iy), new Point(jx, jy));
                if (ccw > 0) {
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
        return result;
    }

    private static ArrayList<Point> convexHull(ArrayList<Point> input) {
    	int i;
        Stack<Point> stack;

        base = new Point(INF, INF);
        for (i = 0; i < input.size(); i++) {
            if (input.get(i).x < base.x) {
                base = input.get(i);
            } else if (input.get(i).x == base.x) {
                if (input.get(i).y < base.y) {
                    base = input.get(i);
                }
            }
        }
        input.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                long ccw = ccw(base, o1, o2);
                
                if (ccw > 0) {
                    return -1;
                } else if (ccw < 0) {
                    return 1;
                } else {
                    return Long.compare(dist(base, o1), dist(base, o2));
                }
            }
        });
        stack = new Stack<>();
        stack.add(base);
        for (i = 1; i < input.size(); i++) {
            while (stack.size() > 1 && (ccw(stack.get(stack.size() - 2), stack.get(stack.size() - 1), input.get(i)) <= 0)) {
                stack.pop();
            }
            stack.add(input.get(i));
        }
        return new ArrayList<>(stack);
    }

    private static long ccw(Point p1, Point p2, Point p3) {
        return (p1.x * p2.y + p2.x * p3.y + p3.x * p1.y) - (p1.y * p2.x + p2.y * p3.x + p3.y * p1.x);
    }

    private static long dist(Point p1, Point p2) {
        return (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
    }
    
    private static void solution(BufferedReader br, StringBuilder sb, StringTokenizer st) throws IOException {
    	int n, i;
    	Point[] result;
    	
    	st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        points = new ArrayList<>();
        for (i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            points.add(new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken())));
        }
        result = rotatingCalipers(convexHull(points));
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
        	sb.append('\n');
        }
        System.out.print(sb);
    }
}