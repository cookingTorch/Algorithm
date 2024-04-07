import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int x, y, x1, x2, y1, y2;
		
		st = new StringTokenizer(br.readLine());
		x1 = Integer.parseInt(st.nextToken());
		y1 = Integer.parseInt(st.nextToken());
		x2 = 0;
		y2 = 0;
		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		if (x == x1) {
			x1 = 0;
		} else {
			x2 = x;
		}
		if (y == y1) {
			y1 = 0;
		} else {
			y2 = y;
		}
		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		if (x == x1) {
			x1 = 0;
		} else if (x == x2) {
			x2 = 0;
		} else {
			x1 = x;
		}
		if (y == y1) {
			y1 = 0;
		} else if (y == y2) {
			y2 = 0;
		} else {
			y1 = y;
		}
		if (x1 == 0) {
			sb.append(x2);
		} else {
			sb.append(x1);
		}
		sb.append(' ');
		if (y1 == 0) {
			sb.append(y2);
		} else {
			sb.append(y1);
		}
		System.out.print(sb);
	}
}
