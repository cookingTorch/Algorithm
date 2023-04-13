import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		StringTokenizer st;
		
		int t, n, command, cnt, i, j, k, start, end;
		String function;
		ArrayList<String> nums = new ArrayList<>();
		
		str = br.readLine();
		t = Integer.parseInt(str);
		
		for (i = 0; i < t; i++) {
			
			nums.clear();
			str = br.readLine();
			function = str;
			command = function.length();
			cnt = 1;
			str = br.readLine();
			n = Integer.parseInt(str);
			start = 0;
			end = n - 1;
			
			for (j = 0; j < command; j++) {
				
				if (function.charAt(j) == 'R') {
					cnt = cnt * -1;
				}
				else if (function.charAt(j) == 'D') {
					if (end >= start) {
						if (cnt > 0) {
							start++;
						}
						else {
							end--;
						}
					}
					else {
						bw.write("error\n");
						str = br.readLine();
						break;
					}
				}
				
				if (j == command - 1) {
					
					str = br.readLine();
					st = new StringTokenizer(str, "[,]");
					
					for (k = 0; k < n; k++) {
						nums.add(st.nextToken());
					}
					
					bw.write("[");
					if (cnt > 0) {
						for (k = start; k <= end; k++) {
							if (k != start) {
								bw.write(",");
							}
							bw.write(nums.get(k));
						}
					}
					else {
						for (k = end; k >= start; k--) {
							if (k != end) {
								bw.write(",");
							}
							bw.write(nums.get(k));
						}
					}
					bw.write("]\n");
				}
				
			}
			
		}
		
		bw.flush();
		bw.close();
		
	}

}