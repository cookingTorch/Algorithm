import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		StringTokenizer st;
		
		long n, sejunX, sejunY, mineX, mineY, x, y, cnt, i, flag = 0;
		long[] RU = new long[2];
		
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		n = Long.parseLong(st.nextToken());
		sejunX = Long.parseLong(st.nextToken());
		sejunY = Long.parseLong(st.nextToken());
		mineX = Long.parseLong(st.nextToken());
		mineY = Long.parseLong(st.nextToken());
		
		x = mineX - sejunX;
		y = mineY - sejunY;
		
		if (x < 0 || y < 0) {
			bw.write("-1");
		}
		else {
			
			if (x + y >= n) {
				
				cnt = (x + y) / n;
				
				for (i = 0; i <= n; i++) {
					RU[0] = i;
					RU[1] = n - i;
					if (RU[0] * cnt <= x && RU[1] * cnt <= y) {
						if (x - (RU[0] * cnt) <= RU[0] && y - (RU[1] * cnt) <= RU[1]) {
							flag++;
							break;
						}
					}
				}
				
				if (flag > 0) {
					for (i = 0; i < x - (RU[0] * cnt); i++) {
						bw.write("R");
					}
					for (i = 0; i < y - (RU[1] * cnt); i++) {
						bw.write("U");
					}
					for (i = 0; i < RU[0] - (x - (RU[0] * cnt)); i++) {
						bw.write("R");
					}
					for (i = 0; i < RU[1] - (y - (RU[1] * cnt)); i++) {
						bw.write("U");
					}
				}
				else {
					bw.write("-1");
				}
				
			}
			else {
				
				for (i = 0; i < x; i++) {
					bw.write("R");
				}
				for (i = 0; i < y; i++) {
					bw.write("U");
				}
				for (i = 0; i < n - x - y; i++) {
					bw.write("R");
				}
				
			}
			
		}
		
		bw.flush();
		bw.close();
		
	}

}