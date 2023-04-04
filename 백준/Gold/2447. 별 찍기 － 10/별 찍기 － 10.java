import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static String[][] square;
	
	private static void star(int x, int y, int n) throws IOException {
		if (n > 1) {
			int i, j, k, l;
			for (i = 0; i < 3; i++) {
				for (j = 0; j < 3; j++) {
					
					if (i == 1 && j == 1) {
						for (k = 0; k < n / 3; k++) {
							for (l = 0; l < n / 3; l++) {
								square[x + (n / 3) + k][y + (n / 3) + l] = " ";
							}
						}
					}
					else {
						star(x + ((n / 3) * i), y + ((n / 3) * j), n / 3);
					}
					
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		
		int n, i, j;;
		
		str = br.readLine();
		n = Integer.parseInt(str);
		
		square = new String[n][n];
		
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				square[i][j] = "*"; 
			}
		}
		
		star(0, 0, n);
		
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				bw.write(square[i][j]);
			}
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		
	}

}