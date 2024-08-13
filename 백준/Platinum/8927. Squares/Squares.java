import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int CAP = 400_000;
    private static final int INF = Integer.MAX_VALUE >> 1;
    private static final char LINE_BREAK = '\n';
    private static final Point BASE = new Point(INF, INF);

    private static final class Point implements Comparable<Point> {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            int val;

            val = ccw(base, this, o);
            if (val == 0) {
                return Integer.compare(dist(base, this), dist(base, o));
            }
            return val > 0 ? -1 : 1;
        }
    }

    private static final class PointArr {
        int size;
        Point[] arr;

        PointArr(int cap) {
            int i;

            arr = new Point[cap];
            for (i = 0; i < cap; i++) {
                arr[i] = new Point(0, 0);
            }
        }

        int size() {
            return size;
        }

        void sort() {
            Arrays.sort(arr, 0, size);
        }

        Point get(int idx) {
            return arr[idx];
        }

        void pop() {
            size--;
        }

        void add(int x, int y) {
            arr[size].x = x;
            arr[size++].y = y;
        }

        void push(Point point) {
            add(point.x, point.y);
        }

        void clear() {
            size = 0;
        }
    }

    private static Point base;
    private static PointArr hull;
    private static PointArr stack;
    private static PointArr points;

    private static int ccw(Point p1, Point p2, Point p3) {
        return (p2.x - p1.x) * (p3.y - p2.y) - (p2.y - p1.y) * (p3.x - p2.x);
    }

    private static int ccw(Point p1, Point p2, Point p3, Point p4) {
        return (p2.x - p1.x) * (p4.y - p3.y) - (p2.y - p1.y) * (p4.x - p3.x);
    }

    private static int dist(Point p1, Point p2) {
        return (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
    }

    private static PointArr convexHull(PointArr points) {
        int i;

        points.sort();
        stack.clear();
        for (i = 0; i < points.size(); i++) {
            while (stack.size() > 1 && ccw(stack.get(stack.size() - 2), stack.get(stack.size() - 1), points.get(i)) <= 0) {
                stack.pop();
            }
            stack.push(points.get(i));
        }
        hull.clear();
        for (i = 0; i < stack.size(); i++) {
            hull.push(stack.get(i));
        }
        return hull;
    }

    private static int rotatingCalipers(PointArr hull) {
        int i;
        int j;
        int ni;
        int nj;
        int max;

        max = 0;
        for (i = 0, j = 1; i < hull.size(); i++) {
            ni = (i + 1) % hull.size();
            for (;;) {
                nj = (j + 1) % hull.size();
                if (ccw(hull.get(i), hull.get(ni), hull.get(j), hull.get(nj)) > 0) {
                    j = nj;
                } else {
                    break;
                }
            }
            max = Math.max(max, dist(hull.get(i), hull.get(j)));
        }
        return max;
    }

    private static int solution(BufferedReader br) throws IOException {
        int n;
        int x;
        int y;
        int i;
        int len;
        Point point;
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        points.clear();
        base = BASE;
        for (i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            len = Integer.parseInt(st.nextToken());
            point = new Point(x, y);
            points.add(x, y);
            if (point.x < base.x || (point.x == base.x && point.y < base.y)) {
                base = point;
            }
            points.add(x + len, y);
            points.add(x, y + len);
            points.add(x + len, y + len);
        }
        return rotatingCalipers(convexHull(points));
    }

    public static void main(String[] args) throws IOException {
        int t;
        int testCase;
        StringBuilder sb;
        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(System.in));
        points = new PointArr(CAP);
        hull = new PointArr(CAP);
        stack = new PointArr(CAP);
        t = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        for (testCase = 0; testCase < t; testCase++) {
            sb.append(solution(br)).append(LINE_BREAK);
        }
        System.out.print(sb);
    }
}
