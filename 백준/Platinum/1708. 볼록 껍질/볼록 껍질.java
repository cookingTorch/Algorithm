import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	private static long CCW(int[] a, int[] b, int[] c) {
		long[] point1 = new long[2], point2 = new long[2], point3 = new long[2];
		point1[0] = a[0];
		point1[1] = a[1];
		point2[0] = b[0];
		point2[1] = b[1];
		point3[0] = c[0];
		point3[1] = c[1];
		return ((point1[0] * point2[1]) + (point2[0] * point3[1]) + (point3[0] * point1[1])) - ((point2[0] * point1[1]) + (point3[0] * point2[1]) + (point1[0] * point3[1]));
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str;
		StringTokenizer st;
		
		int n, i, flag;
		int[] start = new int[2];
		int[][] points;
		long resultCCW;
		ArrayList<int[]> convexHull = new ArrayList<>();
		
		// 점 입력
		str = br.readLine();
		n = Integer.parseInt(str);
		points = new int[n][2];
		for (i = 0; i < n; i++) {
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			points[i][0] = Integer.parseInt(st.nextToken());
			points[i][1] = Integer.parseInt(st.nextToken());
			if (i == 0 || points[i][1] < start[1] || (points[i][1] == start[1]) && points[i][0] > start[0]) {
				start[0] = points[i][0];
				start[1] = points[i][1];
			}
		}
		
		// 각을 기준으로 오름차순 정렬
		Arrays.sort(points, (a, b) -> {
		    if (a[0] == start[0] && a[1] == start[1]) {
		        return -1;
		    } else if (b[0] == start[0] && b[1] == start[1]) {
		        return 1;
		    } else {
		        long ccwValue = CCW(start, a, b);
		        if (ccwValue > 0) {
		            return -1;
		        } else if (ccwValue < 0) {
		            return 1;
		        } else {
		            long distA = (long) (Math.pow(start[0] - a[0], 2) + Math.pow(start[1] - a[1], 2));
		            long distB = (long) (Math.pow(start[0] - b[0], 2) + Math.pow(start[1] - b[1], 2));
		            return Long.compare(distA, distB);
		        }
		    }
		});

		
		// 볼록 껍질
		for (i = 0; i <= n; i++) {
			if (i < n) {
				convexHull.add(0, points[i]);
			}
			else {
				convexHull.add(0, points[0]);
			}
			if (i >= 2) {
				flag = 0;
				while (flag == 0 && convexHull.size() >= 3) {
					resultCCW = CCW(convexHull.get(2), convexHull.get(1), convexHull.get(0));
					if (resultCCW == 0) {
						if ((convexHull.get(2)[0] - convexHull.get(1)[0]) * (convexHull.get(2)[0] - convexHull.get(0)[0]) < 0 || (convexHull.get(2)[1] - convexHull.get(1)[1]) * (convexHull.get(2)[1] - convexHull.get(0)[1]) < 0) {
							convexHull.remove(0);
						}
						else {
							convexHull.remove(1);
						}
					}
					else if (resultCCW < 0) {
						convexHull.remove(1);
					}
					else {
						flag = 1;
					}
				}
			}
		}
		if (convexHull.get(0)[0] == start[0] && convexHull.get(0)[1] == start[1]) {
			convexHull.remove(0);
		}
		
		// 출력
		sb.append(convexHull.size());
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}

}