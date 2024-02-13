import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, l, num, i;
		ArrayList<Integer> h;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		h = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			num = Integer.parseInt(st.nextToken());
			if (num <= l) {
				l++;
			} else {
				h.add(num);
			}
		}
		Collections.sort(h);
		for (int fruit : h) {
			if (fruit <= l) {
				l++;
			} else {
				break;
			}
		}
		System.out.print(l);
	}
}
