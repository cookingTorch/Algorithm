import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		int i;
		int j;
		int h;
		int w;
		int idx;
		int cnt;
		int[] arr;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		arr = new int[w];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < w; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		cnt = 0;
		for (i = 0; i < w; i++) {
			for (j = arr[i]; j < h; j++) {
				for (idx = i; idx >= 0; idx--) {
					if (arr[idx] > j) {
						break;
					}
				}
				if (idx < 0) {
					break;
				}
				for (idx = i; idx < w; idx++) {
					if (arr[idx] > j) {
						break;
					}
				}
				if (idx >= w) {
					break;
				}
				cnt++;
			}
		}
		System.out.print(cnt);
	}
}
