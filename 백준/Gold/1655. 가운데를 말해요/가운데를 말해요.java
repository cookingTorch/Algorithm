import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		
		int n, i, j, number, left, right, mid;
		int[] arr = new int[20001];
		
		str = br.readLine();
		n = Integer.parseInt(str);
		
		for (i = 1; i <= n; i++) {
			
			str = br.readLine();
			number = Integer.parseInt(str);
			
			for (j = number; j <= 10000; j++) {
				arr[j + 10000] += 1;
			}
			
			left = 0;
			right = 20001;
			while (left < right) {
				mid = (left + right) / 2;
				if (arr[mid] < (i + 1) / 2) {
					left = mid + 1;
				}
				else {
					right = mid;
				}
			}
			bw.write(Integer.toString(right - 10000));
			bw.newLine();
			
		}
		
		bw.flush();
		bw.close();

	}

}