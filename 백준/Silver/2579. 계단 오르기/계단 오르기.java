import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamCorruptedException;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n, i;
		long[] score = new long[301];
		
		String str = br.readLine();
		n = Integer.parseInt(str);
		
		score[0] = 0;
		
		for (i = 1; i <= n; i++) {
			str = br.readLine();
			score[i] = Integer.parseInt(str); 
		}
		
		long[] maxScore = new long[301];
		
		maxScore[0] = 0;
		maxScore[1] = score[1];
		maxScore[2] = score[1] + score[2];
		
		for(i = 3; i <= n; i++) {
			maxScore[i] = Math.max(maxScore[i - 2] + score[i], maxScore[i - 3] + score[i - 1] + score[i]);
		}
		
		System.out.println(maxScore[n]);
	}

} 
