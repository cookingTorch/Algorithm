import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		StringTokenizer st;
		
		int n, m, num, i, left, right, mid, lowerBound, upperBound, ans;
		ArrayList<Integer> cards = new ArrayList<>();
		
		str = br.readLine();
		n = Integer.parseInt(str);
		
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		for (i = 0; i < n; i++) {
			cards.add(Integer.parseInt(st.nextToken()));
		}
		
		Collections.sort(cards);
		
		str = br.readLine();
		m = Integer.parseInt(str);
		
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		for (i = 0; i < m; i++) {
			
			num = Integer.parseInt(st.nextToken());
			
			left = 0;
			right = n;
			while (left < right) {
				mid = (left + right) / 2;
				if (cards.get(mid) < num) {
					left = mid + 1;
				}
				else {
					right = mid;
				}
			}
			
			if (right < n && cards.get(right) == num) {
				lowerBound = right;
				
				left = 0;
				right = n;
				while (left < right) {
					mid = (left + right) / 2;
					if (cards.get(mid) <= num) {
						left = mid + 1;
					}
					else {
						right = mid;
					}
				}
				upperBound = right;
				
				ans = upperBound - lowerBound;
			}
			else {
				ans = 0;
			}
			
			bw.write(Integer.toString(ans));
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		
	}

}