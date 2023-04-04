import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		
		String str1, str2;
		int i, j, max = 0;
		int[][] arr;
		
		str = br.readLine();
		str1 = str;
		str = br.readLine();
		str2 = str;
		
		arr = new int[str1.length() + 1][str2.length() + 1];

		for (i = 0; i <= str1.length(); i++) {
			for (j = 0; j <= str2.length(); j++) {
				
				if (i == 0 || j == 0) {
					arr[i][j] = 0; 
				}
				else {
					if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
						arr[i][j] = arr[i - 1][j - 1] + 1;
						max = Math.max(max, arr[i][j]);
					}
					else {
						arr[i][j] = Math.max(arr[i - 1][j], arr[i][j - 1]);
					}
				}
				
			}
		}
		
		System.out.println(max);
		
	}

}