import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int SIX = 6;
	private static final int NINE = 9;
	private static final char DIFF = '0';

	public static void main(String[] args) throws IOException {
		int i;
		int num;
		int len;
		int max;
		int[] cnt;
		String str;
		BufferedReader br;

		br = new BufferedReader(new InputStreamReader(System.in));
		cnt = new int[NINE];
		str = br.readLine();
		len = str.length();
		max = 0;
		for (i = 0; i < len; i++) {
			if ((num = str.charAt(i) - DIFF) == NINE || num == SIX) {
				cnt[num = SIX]++;
			} else {
				cnt[num] += 2;
			}
			max = Math.max(max, cnt[num]);
		}
		System.out.print((max + 1) >> 1);
	}
}
