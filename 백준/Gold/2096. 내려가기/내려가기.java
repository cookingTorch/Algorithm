import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		int n;
		int i;
		int[] prev;
		int[] curr;
		int[] temp;
		int[][] score;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		score = new int[n][];
		st = new StringTokenizer(br.readLine());
		score[0] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		prev = new int[3];
		curr = score[0].clone();
		for (i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			score[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			temp = prev;
			prev = curr;
			curr = temp;
			curr[0] = Math.max(prev[0], prev[1]) + score[i][0];
			curr[1] = Math.max(prev[0], Math.max(prev[1], prev[2])) + score[i][1];
			curr[2] = Math.max(prev[1], prev[2]) + score[i][2];
		}
		sb = new StringBuilder();
		sb.append(Math.max(curr[0], Math.max(curr[1], curr[2]))).append(' ');
		curr = score[0].clone();
		for (i = 1; i < n; i++) {
			temp = prev;
			prev = curr;
			curr = temp;
			curr[0] = Math.min(prev[0], prev[1]) + score[i][0];
			curr[1] = Math.min(prev[0], Math.min(prev[1], prev[2])) + score[i][1];
			curr[2] = Math.min(prev[1], prev[2]) + score[i][2];
		}sb.append(Math.min(curr[0], Math.min(curr[1], curr[2])));
		System.out.print(sb);
	}
}
