import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n, i, j;
		int[] a, max;
		
		String str = br.readLine();
		n = Integer.parseInt(str);
		
		a = new int[n];
		max = new int[n];
		
		str = br.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		for (i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken()); 
		}
		
		max[0] = 1;
		for (i = 1; i < n; i++) {
			max[i]= 1; 
			for (j = i - 1; j >= 0; j--) {
				if (max[j] >= max[i] && a[j] < a[i]) {
					max[i] = max[j] + 1;
				}
			}
		}
		
		Arrays.sort(max);
		System.out.println(max[n - 1]);
	}

}