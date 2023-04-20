import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	private static int[] parents;
	
	private static double CCW(int[] point1, int[] point2, int[] point3) {
		return ((point1[0] * point2[1]) + (point2[0] * point3[1]) + (point3[0] * point1[1])) - ((point2[0] * point1[1]) + (point3[0] * point2[1]) + (point1[0] * point3[1]));
	}
	
	private static boolean isIntersect(int[][] segment1, int[][] segment2) {
		
		if (CCW(segment1[0], segment1[1], segment2[0])*CCW(segment1[0], segment1[1], segment2[1]) == 0 && CCW(segment2[0], segment2[1], segment1[0])*CCW(segment2[0], segment2[1], segment1[1]) == 0) {
			
			if ((Math.min(segment1[0][0], segment1[1][0]) > Math.max(segment2[0][0], segment2[1][0]) || Math.max(segment1[0][0], segment1[1][0]) < Math.min(segment2[0][0], segment2[1][0])) || (Math.min(segment1[0][1], segment1[1][1]) > Math.max(segment2[0][1], segment2[1][1]) || Math.max(segment1[0][1], segment1[1][1]) < Math.min(segment2[0][1], segment2[1][1]))) {
				return false;
			}
			else {
				return true;
			}
			
		}
		else if (CCW(segment1[0], segment1[1], segment2[0])*CCW(segment1[0], segment1[1], segment2[1]) <= 0 && CCW(segment2[0], segment2[1], segment1[0])*CCW(segment2[0], segment2[1], segment1[1]) <= 0) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	private static int root(int i) {
		if (parents[i] == i) {
			return i;
		}
		else {
			parents[i] = root(parents[i]);
			return parents[i];
		}
	}
	
	private static void union(int i, int j) {
		
		if (root(i) != root(j)) {
			parents[root(i)] = Math.min(root(i), root(j));
			parents[root(j)] = Math.min(root(i), root(j));
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		StringTokenizer st;
		
		int n, i, j, cnt = 0, max = 0;
		int[] ans;
		int[][][] segments;
		
		str = br.readLine();
		n = Integer.parseInt(str);
		segments = new int[n][2][2];
		parents = new int[n];
		ans = new int[n];
		
		for (i = 0; i < n; i++) {
			str = br.readLine();
			st = new StringTokenizer(str);
			segments[i][0][0] = Integer.parseInt(st.nextToken());
			segments[i][0][1] = Integer.parseInt(st.nextToken());
			segments[i][1][0] = Integer.parseInt(st.nextToken());
			segments[i][1][1] = Integer.parseInt(st.nextToken());
		}
		
		for (i = 0; i < n; i++) {
			parents[i] = i;
		}
		
		for (i = 0; i < n; i++) {
			for (j = i + 1; j < n; j++) {
				if (isIntersect(segments[i], segments[j])) {
					union(i, j);
				}
			}
		}
		
		for (i = 0; i < n; i++) {
			ans[root(i)]++;
		}
		
		for (i = 0; i < n; i++) {
			if (ans[i] > 0) {
				cnt++;
				if (ans[i] > max) {
					max = ans[i];
				}
			}
		}
		
		bw.write(Integer.toString(cnt) + "\n");
		bw.write(Integer.toString(max));
		
		bw.flush();
		bw.close();

	}

}