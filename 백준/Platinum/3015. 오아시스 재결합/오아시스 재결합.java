import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str;
		
		int n, i, now, last;
		long cnt = 0;
		ArrayList<int[]> heights = new ArrayList<>();
		
		str = br.readLine();
		n = Integer.parseInt(str);
		str = br.readLine();
		now = Integer.parseInt(str);
		heights.add(new int[] {now, 1});
		for (i = 1; i < n; i++) {
			str = br.readLine();
			now = Integer.parseInt(str);
			while (true) {
				last = heights.size() - 1;
				if (heights.get(last)[0] > now) {
					cnt++;
					heights.add(new int[] {now, 1});
					break;
				}
				else if (heights.get(last)[0] < now) {
					cnt += heights.get(last)[1];
					heights.remove(last);
					if (heights.isEmpty()) {
						heights.add(new int[] {now, 1});
						break;
					}
				}
				else {
					cnt += heights.get(last)[1]++;
					if (last > 0) {
						cnt++;
					}
					break;
				}
			}
		}
		sb.append(cnt);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}