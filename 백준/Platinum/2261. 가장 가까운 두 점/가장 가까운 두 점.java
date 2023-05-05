import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;
 
public class Main {
	
	private static Comparator<Point> compX = new Comparator<Point>() {
		@Override
		public int compare(Point o1, Point o2) {
			if(o1.x == o2.x) {
				return o1.y - o2.y;
			}
			return o1.x - o2.x;
		}
	};
	private static Comparator<Point> compY = new Comparator<Point>() {
		@Override
		public int compare(Point o1, Point o2) {
			if(o1.y == o2.y) {
				return o1.x - o2.x;
			}
			return o1.y - o2.y;
		}
	};
	
	// 좌표 class
	private static class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
 
	// 두 점 사이 거리
	private static int distance(Point o1, Point o2) {
		return (o1.x - o2.x) * (o1.x - o2.x) + (o1.y - o2.y) * (o1.y - o2.y);
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str;
		StringTokenizer st;
		
		int n, min, i, startIdx = 0, deltaX, minY, maxY;
		boolean hasDuplicate = false;
		Point[] points;
		TreeSet<Point> setX = new TreeSet<Point>(compY);
		
		// 입력
		str = br.readLine();
		n = Integer.parseInt(str);
		points = new Point[n];
		for(i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(points, compX);
		
		// 중복 탐색
		for (i = 0; i < n - 1; i++) {
			if (points[i].x == points[i + 1].x && points[i].y == points[i + 1].y) {
				hasDuplicate = true;
				break;
			}
		}
		if (hasDuplicate) {
			sb.append(0);
		}
		
		// 스위핑
		else {
			min = distance(points[0], points[1]);
			setX.add(points[0]);
			setX.add(points[1]);
			for(i = 2; i < n; i++) {
				
				// X 범위 설정
				while(startIdx < i) {
					Point startPoint = points[startIdx];
					deltaX = points[i].x - startPoint.x;
					if (deltaX * deltaX > min) {
						setX.remove(startPoint);
						startIdx++;
					}
					else {
						break;
					}
				}
				
				// 최소 거리 갱신
				minY = (int) Math.ceil(points[i].y - Math.sqrt(min));
				maxY = (int) Math.floor(points[i].y + Math.sqrt(min));
				TreeSet<Point> setY = (TreeSet<Point>) setX.subSet(new Point(-100000, minY), new Point(100000, maxY));
				for (Point p : setY) {
					min = Math.min(min, distance(points[i], p));
				}
				setX.add(points[i]);
			}
			
			// 출력
			sb.append(min);
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		
	}
 
}