import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		StringTokenizer st;
		
		int n, m, i, j, flag = 0, small, large, current, ans, close;
		String button, number, goTo;
		Character[] broken;
		
		str = br.readLine();
		n = Integer.parseInt(str);
		str = br.readLine();
		m = Integer.parseInt(str);
		
		broken = new Character[m];
		
		if (m < 10) {
			if (m > 0) {
				str = br.readLine();
				st = new StringTokenizer(str, " ");
				for (i = 0; i < m; i++) {
					button = st.nextToken();
					broken[i] = button.charAt(0);
				}
			}
			else {
				for (i = 0; i < m; i++) {
					broken[i] = 10; 
				}
			}
			
			for (i = n; i >= 0; i--) {
				number = Integer.toString(i);
				flag = 1;
				for (j = 0; j < number.length(); j++) {
					if (Arrays.asList(broken).contains(number.charAt(j))) {
						flag = 0;
						break;
					}
				}
				if (flag == 1) {
					break;
				}
			}
			if (flag == 1) {
				small = n - i;
				
				for (i = n + 1; i - n < small; i++) {
					number = Integer.toString(i);
					flag = 1;
					for (j = 0; j < number.length(); j++) {
						if (Arrays.asList(broken).contains(number.charAt(j))) {
							flag = 0;
							break;
						}
					}
					if (flag == 1) {
						break;
					}
				}
				large = i - n;
				
				if (small <= large) {
					current = n - small;
					goTo = Integer.toString(current);
					ans = goTo.length() + small;
				}
				else {
					current = n + large;
					goTo = Integer.toString(current);
					ans = goTo.length() + large;
				}
			}
			else {
				for (i = n + 1; ; i++) {
					number = Integer.toString(i);
					flag = 1;
					for (j = 0; j < number.length(); j++) {
						if (Arrays.asList(broken).contains(number.charAt(j))) {
							flag = 0;
							break;
						}
					}
					if (flag == 1) {
						break;
					}
				}
				large = i - n;
				
				current = n + large;
				goTo = Integer.toString(current);
				ans = goTo.length() + large;
			}
			
			close = Math.abs(n - 100);
		}
		else {
			close = Math.abs(n - 100);
			ans = close + 1;
		}
		
		bw.write(Integer.toString(Math.min(ans, close)));
		
		bw.flush();
		bw.close();

	}

}