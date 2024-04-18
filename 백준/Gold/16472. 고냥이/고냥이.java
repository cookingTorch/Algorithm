import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int SIZE = 'z' + 1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n, left, right, num, max, len;
		int[] cnt;
		char[] str;
		
		n = Integer.parseInt(br.readLine());
		str = br.readLine().toCharArray();
		len = str.length;
		cnt = new int[SIZE];
		cnt[str[0]]++;
		num = 1;
		max = 1;
		for (left = 0, right = 0;;) {
			while (num <= n) {
				if (++right == len) {
					System.out.print(max);
					return;
				}
				if (cnt[str[right]]++ == 0) {
					num++;
				}
				if (num <= n) {
					max = Math.max(max, right - left + 1);
				}
			}
			if (--cnt[str[left++]] == 0) {
				num--;
			}
		}
	}
}
