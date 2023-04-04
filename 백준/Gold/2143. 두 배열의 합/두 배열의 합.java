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
		
		int t, n, m, i, j, k, p, q, cnt, left, right, mid, flag, lowerBound, upperBound = 500500;
		int[] a, b, aSum, bSum;
		long ans = 0;
		
		str = br.readLine();
		t = Integer.parseInt(str);
		
		str = br.readLine();
		n = Integer.parseInt(str);
		a = new int[n];
		
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		for (i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken()); 
		}
		
		str = br.readLine();
		m = Integer.parseInt(str);
		b = new int[m];
		
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		for (i = 0; i < m; i++) {
			b[i] = Integer.parseInt(st.nextToken()); 
		}
		
		p = (n * (n + 1)) / 2;
		aSum = new int[p];
		cnt = 0;
		for (i = 0; i < n; i++) {
			for (j = i; j < n; j++) {
				for (k = i; k <= j; k++) {
					aSum[cnt] += a[k];
				}
				cnt++;
			}
		}
		
		q = (m * (m + 1)) / 2;
		bSum = new int[q];
		cnt = 0;
		for (i = 0; i < m; i++) {
			for (j = i; j < m; j++) {
				for (k = i; k <= j; k++) {
					bSum[cnt] += b[k];
				}
				cnt++;
			}
		}
		
		Arrays.sort(bSum);
		
		for (i = 0; i < p; i++) {
			
			left = 0;
			right = q;
			while (left < right) {
				mid = (left + right) / 2;
				
				if (bSum[mid] <= t - aSum[i]) {
					left = mid + 1;
				}
				else {
					right = mid;
				}
			}
			upperBound = right;
			
			left = 0;
			right = q;
			while (left < right) {
				mid = (left + right) / 2;
				
				if (bSum[mid] >= t - aSum[i]) {
					right = mid;
				}
				else {
					left = mid + 1;
				}
			}
			lowerBound = right;
			
			ans += upperBound - lowerBound;
		}
		
		bw.write(Long.toString(ans));
		
		bw.flush();
		bw.close();
		
	}

}