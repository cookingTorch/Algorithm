import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	private static int n;
	private static int[][] event, leftDist;
	private static boolean[][] visited;
	
	// 거리 계산
	private static int findDist(int[] point1, int[] point2) {
		return Math.abs(point1[0] - point2[0]) + Math.abs(point1[1] - point2[1]);
	}
	
	// DP
	private static void findLeftDist(int police1, int police2) {
		int next, dist1, dist2;
		next = Math.max(police1, police2) + 1;
		
		// 경찰차 거리
		dist1 = findDist(event[police1], event[next]);
		if (police2 == 0) {
			dist2 = findDist(new int[] {n, n}, event[next]);
		}
		else {
			dist2 = findDist(event[police2], event[next]);
		}
		
		// 재귀
		if (!visited[next][police2]) {
			findLeftDist(next, police2);
		}
		if (!visited[police1][next]) {
			findLeftDist(police1, next);
		}
		
		// 남은 거리
		leftDist[police1][police2] = Math.min(leftDist[next][police2] + dist1, leftDist[police1][next] + dist2);
		visited[police1][police2] = true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str;
		StringTokenizer st;
		
		int w, i, police1 = 0, police2 = 0;
		
		// 사건 입력
		str = br.readLine();
		n = Integer.parseInt(str);
		str = br.readLine();
		w = Integer.parseInt(str);
		event = new int[w + 1][2];
		event[0] = new int[] {1, 1};
		for (i = 1; i <= w; i++) {
			str = br.readLine();
			st = new StringTokenizer(str);
			event[i][0] = Integer.parseInt(st.nextToken());
			event[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 최소 거리
		leftDist = new int[w + 1][w + 1];
		visited = new boolean[w + 1][w + 1];
		for (i = 0; i <= w; i++) {
			visited[w][i] = true;
			visited[i][w] = true;
		}
		findLeftDist(0, 0);
		sb.append(leftDist[0][0]).append("\n");
		
		// 최소 경로
		for (i = 1; i <= w; i++) {
			if (leftDist[i][police2] + findDist(event[police1], event[i]) == leftDist[police1][police2]) {
				sb.append("1").append("\n");
				police1 = i;
			}
			else {
				sb.append("2").append("\n");
				police2 = i;
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}