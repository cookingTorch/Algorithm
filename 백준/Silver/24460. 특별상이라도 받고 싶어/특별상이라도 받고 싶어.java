import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	private static int pickSecond(int[][] chair, int n) {
		int i, j, cnt = 0;
		int[] arr = new int[4];
		for (i = 0; i < 2; i++) {
			for (j = 0; j < 2; j++) {
				
				if (n > 2) {
					int k, l;
					int[][] chair2 = new int[n / 2][n / 2];
					
					for (k = 0; k < n / 2; k++) {
						for (l = 0; l < n / 2; l++) {
							chair2[k][l] = chair[(n / 2) * i + k][(n / 2) * j + l];
						}
					}
					
					chair[i][j] = pickSecond(chair2, n / 2);
				}
				
				arr[cnt] = chair[i][j];
				cnt++;
			}
		}
		Arrays.sort(arr);
		return arr[1];
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		StringTokenizer st;
		
		int n, i, j;
		int[][] chair;
		
		str = br.readLine();
		n = Integer.parseInt(str);
		
		chair = new int[n][n];
		
		for (i = 0; i < n; i++) {
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			for (j = 0; j < n; j++) {
				chair[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		if (n == 1) {
			System.out.println(chair[0][0]);
		}
		else {
			System.out.println(pickSecond(chair, n));
		}
		
	}

}
