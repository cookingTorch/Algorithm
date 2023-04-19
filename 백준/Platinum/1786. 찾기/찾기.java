import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String t, p;
		
		int i, j, tLength, pLength, cnt, next, flag, num;
		int[] k;
		ArrayList<Integer> ans = new ArrayList<>();
		
		t = "0" + br.readLine();
		p = "0" + br.readLine();
		tLength = t.length() - 1;
		pLength = p.length() - 1;
		k = new int[p.length() + 1];
		cnt = 0;
		next = 0;
		flag = 0;
		num = 0;
		
		j = 1;
		for (i = 2; i <= pLength; i++) {
			while (j > 1 && p.charAt(i) != p.charAt(j)) {
				j = k[j] + 1;
			}
			if (p.charAt(i) == p.charAt(j)) {
				j++;
				k[i + 1] = j - 1;
			}
		}
		
		for (i = 1; i <= tLength; i++) {
			for (j = next + 1; j <= pLength; j++) {
				if (j <= tLength - i + 1 && t.charAt(i + j - 1) != p.charAt(j)) {
					if (flag == 1) {
						next = k[j];
						i = i - 1 + j - next - 1;
					}
					break;
				}
				else {
					flag = 1;
				}
			}
			if (j == pLength + 1 && i <= tLength - pLength + 1) {
				ans.add(i);
				num++;
				next = k[j];
				i = i - 1 + j - next - 1;
			}
			if (flag == 0) {
				next = 0;
			}
			flag = 0;
		}
		
		bw.write(Integer.toString(num));
		bw.newLine();
		for (i = 0; i < ans.size(); i++) {
			bw.write(Integer.toString(ans.get(i)) + " ");
		}
		
		bw.flush();
		bw.close();
		
	}

}