import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, i, j, start, curr, prev, next;
		int[] left, right;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		left = new int[1000001];
		right = new int[1000001];
		st = new StringTokenizer(br.readLine());
		curr = Integer.parseInt(st.nextToken());
		start = curr;
		prev = curr;
		for (i = 1; i < n; i++) {
			curr = Integer.parseInt(st.nextToken());
			left[curr] = prev;
			right[prev] = curr;
			prev = curr;
		}
		right[curr] = start;
		left[start] = curr;
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			switch (st.nextToken()) {
			case "BN":
				curr = Integer.parseInt(st.nextToken());
				j = Integer.parseInt(st.nextToken());
				sb.append(next = right[curr]).append('\n');
				right[curr] = j;
				left[j] = curr;
				right[j] = next;
				left[next] = j;
				break;
			case "BP":
				curr = Integer.parseInt(st.nextToken());
				j = Integer.parseInt(st.nextToken());
				sb.append(prev = left[curr]).append('\n');
				left[curr] = j;
				left[j] = prev;
				right[j] = curr;
				right[prev] = j;
				break;
			case "CN":
				curr = Integer.parseInt(st.nextToken());
				sb.append(next = right[curr]).append('\n');
				next = right[next];
				left[next] = curr;
				right[curr] = next;
				break;
			case "CP":
				curr = Integer.parseInt(st.nextToken());
				sb.append(prev = left[curr]).append('\n');
				prev = left[prev];
				right[prev] = curr;
				left[curr] = prev;
			}
		}
		System.out.print(sb);
	}
}
